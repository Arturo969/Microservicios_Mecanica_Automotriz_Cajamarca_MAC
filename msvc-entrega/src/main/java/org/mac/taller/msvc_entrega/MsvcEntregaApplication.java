package org.mac.taller.msvc_entrega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcEntregaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcEntregaApplication.class, args);
	}

}