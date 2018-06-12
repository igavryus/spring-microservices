package com.igavr.consumer;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "producer", url="producer:8080")
public interface ProducerService {
	@RequestMapping("/api/get")
	String get();
}
