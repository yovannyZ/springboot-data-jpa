package com.example.spingboot.app.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spingboot.app.models.entity.Client;
import com.example.spingboot.app.models.services.IClientService;
import com.example.spingboot.app.util.paginator.PageRender;


@Controller
@SessionAttributes("client")
public class ClientController {

	@Autowired
	private IClientService clienteService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping(value="/upload/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename){
		Path pathPhoto = Paths.get("upload").resolve(filename).toAbsolutePath();
		Resource recurso = null;
		try {
			recurso = new UrlResource(pathPhoto.toUri());
			if(!recurso.exists() && !recurso.isReadable()) {
				throw new RuntimeException("Error: no se puede cargar la imagen: " + pathPhoto.toString());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model) {
		
		Pageable pageRequest= new PageRequest(page, 5);
		Page<Client> clients = clienteService.findAll(pageRequest);
		
		PageRender<Client> pageRender = new PageRender<>("/listar", clients);
		
		model.addAttribute("title", "Listado de Clientes");
		model.addAttribute("clients",clients );
		model.addAttribute("page",pageRender );
		return "Listar";
		
	}
	
	@RequestMapping(value="/form")
	public String crear(Model model) {
		
		Client client = new Client();
		model.addAttribute("title","Formulario de Cliente");
		model.addAttribute("client", client);
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Client client, BindingResult result, Model model,@RequestParam("file")MultipartFile photo, RedirectAttributes flash, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("title","Formulario de Cliente");
			return "form";
		}
			
		if(!photo.isEmpty()) {
			
			String uniqueFileName = UUID.randomUUID().toString() + "_" + photo.getOriginalFilename();
			Path rootPath = Paths.get("upload").resolve(uniqueFileName);
			Path rootAbsolutePath = rootPath.toAbsolutePath();
			
			log.info("rootPath " + rootPath);
			log.info("rootAbsolutePath " + rootAbsolutePath);
			
			try {
				Files.copy(photo.getInputStream(),rootAbsolutePath);
				flash.addFlashAttribute("info", "Se ha subido correctamente " + uniqueFileName);
				client.setPhoto(uniqueFileName);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		clienteService.save(client);
		status.setComplete();
		flash.addFlashAttribute("success", "Cliente guardado con éxito");
		return "redirect:listar";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value ="id")Long id, Model model,RedirectAttributes flash) {
		
		Client client = null;
		
		if(id > 0) {
			client = clienteService.findOne(id);
			if(client == null) {
				flash.addFlashAttribute("success", "El cliente no existe");
				return "redirect:/listar";
			}
		}else {
			flash.addFlashAttribute("success", "El cliente no existe");
			return "redirect:/listar";
		}

		model.addAttribute("title","Editar Cliente");
		model.addAttribute("client", client);
		return "form";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id")Long id, RedirectAttributes flash) {
		if(id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito");
		}
		return "redirect:/listar";
	}
	
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id")Long id, Model model, RedirectAttributes flash) {
		Client client = clienteService.findOne(id);
		
		if(client == null) {
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/listar";
		}
		
		model.addAttribute("client", client);
		model.addAttribute("title", "Detalle del cliente " + client.getName());
		
		return "ver";
	}
}
