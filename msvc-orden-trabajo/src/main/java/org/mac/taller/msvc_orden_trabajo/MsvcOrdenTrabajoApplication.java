package org.mac.taller.msvc_orden_trabajo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcOrdenTrabajoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsvcOrdenTrabajoApplication.class, args);
    }
}