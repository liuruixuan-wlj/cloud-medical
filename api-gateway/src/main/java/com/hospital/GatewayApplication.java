package com.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        System.out.println("✅ API网关启动: http://localhost:8888");
        System.out.println("📌 路由规则:");
        System.out.println("  /api/patients/** → patient-service");
        System.out.println("  /api/records/** → medical-record-service");
    }
}