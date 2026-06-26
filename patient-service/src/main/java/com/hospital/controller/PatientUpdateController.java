package com.hospital.controller;

import com.hospital.entity.Patient;
import com.hospital.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "03-患者修改", description = "各种修改接口")
@RestController
@RequestMapping("/api/patients")
public class PatientUpdateController {

    @Autowired
    private PatientService service;

    @Operation(summary = "22. 修改姓名")
    @PutMapping("/{id}/name")
    public Patient updateName(@PathVariable Long id, @RequestParam String name) {
        Patient p = service.getPatient(id);
        if (p != null) {
            p.setName(name);
            return service.updatePatient(p);
        }
        return null;
    }

    @Operation(summary = "23. 修改年龄")
    @PutMapping("/{id}/age")
    public Patient updateAge(@PathVariable Long id, @RequestParam Integer age) {
        Patient p = service.getPatient(id);
        if (p != null) {
            p.setAge(age);
            return service.updatePatient(p);
        }
        return null;
    }

    @Operation(summary = "24. 修改性别")
    @PutMapping("/{id}/gender")
    public Patient updateGender(@PathVariable Long id, @RequestParam String gender) {
        Patient p = service.getPatient(id);
        if (p != null) {
            p.setGender(gender);
            return service.updatePatient(p);
        }
        return null;
    }

    @Operation(summary = "25. 修改电话")
    @PutMapping("/{id}/phone")
    public Patient updatePhone(@PathVariable Long id, @RequestParam String phone) {
        Patient p = service.getPatient(id);
        if (p != null) {
            p.setPhone(phone);
            return service.updatePatient(p);
        }
        return null;
    }

    @Operation(summary = "26. 修改地址")
    @PutMapping("/{id}/address")
    public Patient updateAddress(@PathVariable Long id, @RequestParam String address) {
        Patient p = service.getPatient(id);
        if (p != null) {
            p.setAddress(address);
            return service.updatePatient(p);
        }
        return null;
    }
}