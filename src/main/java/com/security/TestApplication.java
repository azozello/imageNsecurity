package com.security;

import com.security.model.Image;
import com.security.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.security.service.ImageService.allImages;

@SpringBootApplication
public class TestApplication {

	@Autowired
	private ImageService imageService;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@PostConstruct
	public void initIt(){
		allImages = imageService.loadImageNames();
	}

	@PreDestroy
	public void save(){
		imageService.saveImageNames(allImages);
	}
}
