package com.igavr.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProducerApplication {

	@RequestMapping("/api/get")
	public String get() {
		double randomFailure = Math.random();
		String hostname = System.getenv().getOrDefault("HOSTNAME", "Unknown");
		
		if (randomFailure > 0.7) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "Producer at "+hostname;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}
}
