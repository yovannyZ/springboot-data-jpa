package com.example.spingboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.spingboot.app.models.services.IUploadFileService;

@SpringBootApplication
public class SpringbootDataJpaApplication implements CommandLineRunner{

	@Autowired
	private IUploadFileService uploadService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootDataJpaApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		uploadService.deleteAll();
		uploadService.init();
		
	}
}
