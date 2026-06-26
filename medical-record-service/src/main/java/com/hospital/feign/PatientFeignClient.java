package com.hospital.feign;

import com.hospital.entity.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service")
public interface PatientFeignClient {
    @GetMapping("/api/patients/{id}")
    Patient getPatient(@PathVariable("id") Long id);
}