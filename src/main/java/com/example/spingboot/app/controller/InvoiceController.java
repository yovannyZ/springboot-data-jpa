package com.example.spingboot.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spingboot.app.models.entity.Client;
import com.example.spingboot.app.models.entity.Invoice;
import com.example.spingboot.app.models.entity.Product;
import com.example.spingboot.app.models.services.IClientService;

@Controller
@RequestMapping("/invoice")
@SessionAttributes("invoice")
public class InvoiceController {
	
	@Autowired	
	private IClientService clientService;
	
	@GetMapping("/form/{client_id}")
	public String crear(@PathVariable(value = "client_id") Long clientId, Map<String, Object> model,
			RedirectAttributes flash) {
		
		Client client = clientService.findOne(clientId);
		
		if(client == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}
		
		Invoice invoice = new Invoice();
		invoice.setClient(client);
		
		model.put("invoice", invoice);
		model.put("title", "Crear factura");
		
		return "invoice/form";
	}
	
	@GetMapping(value="/load-products/{term}", produces= {"application/json"})
	public @ResponseBody List<Product> loadProduct(@PathVariable String term){
		return clientService.findByName(term);
	}
}
