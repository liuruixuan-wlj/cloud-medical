package com.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MedicalRecordApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicalRecordApplication.class, args);
        System.out.println("✅ 病历服务启动: http://localhost:8083");
    }
}