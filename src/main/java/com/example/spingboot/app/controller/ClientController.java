package com.example.spingboot.app.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
import com.example.spingboot.app.models.services.IUploadFileService;
import com.example.spingboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("client")
public class ClientController {

	@Autowired
	private IClientService clienteService;

	@Autowired
	private IUploadFileService uploadService;

	@GetMapping(value = "/upload/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;
		try {
			recurso = uploadService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = new PageRequest(page, 5);
		Page<Client> clients = clienteService.findAll(pageRequest);

		PageRender<Client> pageRender = new PageRender<>("/listar", clients);

		model.addAttribute("title", "Listado de Clientes");
		model.addAttribute("clients", clients);
		model.addAttribute("page", pageRender);
		return "Listar";

	}

	@RequestMapping(value = "/form")
	public String crear(Model model) {

		Client client = new Client();
		model.addAttribute("title", "Formulario de Cliente");
		model.addAttribute("client", client);
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Client client, BindingResult result, Model model,
			@RequestParam("file") MultipartFile photo, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("title", "Formulario de Cliente");
			return "form";
		}

		if (!photo.isEmpty()) {

			if (client.getId() != null && client.getId() > 0 && client.getPhoto() != null
					&& client.getPhoto().length() > 0) {

				uploadService.delete(client.getPhoto());

			}

			String uniqueFileName = null;
			try {
				uniqueFileName = uploadService.copy(photo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Se ha subido correctamente " + uniqueFileName);
			client.setPhoto(uniqueFileName);
		}

		clienteService.save(client);
		status.setComplete();
		flash.addFlashAttribute("success", "Cliente guardado con éxito");
		return "redirect:listar";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		Client client = null;

		if (id > 0) {
			client = clienteService.findOne(id);
			if (client == null) {
				flash.addFlashAttribute("success", "El cliente no existe");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("success", "El cliente no existe");
			return "redirect:/listar";
		}

		model.addAttribute("title", "Editar Cliente");
		model.addAttribute("client", client);
		return "form";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {

			Client client = clienteService.findOne(id);

			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito");

			if (uploadService.delete(client.getPhoto())) {
				flash.addFlashAttribute("info", "Photo " + client.getPhoto() + " eliminado");
			}

		}
		return "redirect:/listar";
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Client client = clienteService.findOne(id);

		if (client == null) {
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/listar";
		}

		model.addAttribute("client", client);
		model.addAttribute("title", "Detalle del cliente " + client.getName());

		return "ver";
	}
}
