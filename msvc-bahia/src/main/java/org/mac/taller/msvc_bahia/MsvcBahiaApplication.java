package org.mac.taller.msvc_bahia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcBahiaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsvcBahiaApplication.class, args);
    }
}