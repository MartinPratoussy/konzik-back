package com.konzik.Concert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@ComponentScan("com.konzik.Common")
@EntityScan(basePackageClasses = {com.konzik.Common.Concert.class})
public class ConcertApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcertApplication.class, args);
	}

	@RestController
	class ServiceInstanceRestController {

		@Autowired
		private DiscoveryClient discoveryClient;

		@RequestMapping("/service-instances/{applicationName}")
		public List<ServiceInstance> serviceInstancesByApplicationName(
				@PathVariable String applicationName) {
			return this.discoveryClient.getInstances(applicationName);
		}
	}
}
