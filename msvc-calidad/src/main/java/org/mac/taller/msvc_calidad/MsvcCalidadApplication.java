package org.mac.taller.msvc_calidad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcCalidadApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcCalidadApplication.class, args);
	}

}