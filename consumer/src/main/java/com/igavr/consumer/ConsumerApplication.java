package com.igavr.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

@SpringBootApplication
@RestController
@RequestMapping("/")
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
public class ConsumerApplication {
	@Autowired
	private ProducerService producer;
	@CrossOrigin
	@RequestMapping("/api/get")
	public String get() {
        String hostname = System.getenv().getOrDefault("HOSTNAME", "Unknown");
        
        String producerResponse = new CommandProducerGet().execute();
        
		String text="Consumer at "+hostname+" \n\r"+producerResponse;
		return text;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	public class CommandProducerGet extends HystrixCommand<String> {

	    public CommandProducerGet() {
	        super(HystrixCommandGroupKey.Factory.asKey("ConsumerGroup"));
	    }

	    @Override
	    protected String run() {
	        // a real example would do work like a network call here
	        return producer.get();
	    }
	}
}
