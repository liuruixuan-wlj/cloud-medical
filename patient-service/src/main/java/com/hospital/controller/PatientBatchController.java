package com.hospital.controller;

import com.hospital.entity.Patient;
import com.hospital.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "04-患者批量操作", description = "批量增删改查")
@RestController
@RequestMapping("/api/patients")
public class PatientBatchController {

    @Autowired
    private PatientService service;

    @Operation(summary = "27. 批量新增")
    @PostMapping("/batch")
    public List<Patient> addBatch(@RequestBody List<Patient> patients) {
        for (Patient p : patients) {
            service.addPatient(p);
        }
        return patients;
    }

    @Operation(summary = "28. 批量删除")
    @DeleteMapping("/batch")
    public String deleteBatch(@RequestBody List<Long> ids) {
        int count = 0;
        for (Long id : ids) {
            if (service.deletePatient(id)) count++;
        }
        return "成功删除 " + count + " 位患者";
    }

    @Operation(summary = "29. 批量查询")
    @GetMapping("/batch")
    public List<Patient> getBatch(@RequestParam List<Long> ids) {
        List<Patient> result = new java.util.ArrayList<>();
        for (Long id : ids) {
            Patient p = service.getPatient(id);
            if (p != null) result.add(p);
        }
        return result;
    }

    @Operation(summary = "30. 批量修改年龄")
    @PutMapping("/batch/age")
    public String batchUpdateAge(@RequestParam Integer oldAge, @RequestParam Integer newAge) {
        int count = 0;
        for (Patient p : service.getAllPatients()) {
            if (p.getAge().equals(oldAge)) {
                p.setAge(newAge);
                service.updatePatient(p);
                count++;
            }
        }
        return "更新了 " + count + " 位患者的年龄";
    }

    @Operation(summary = "31. 批量修改性别")
    @PutMapping("/batch/gender")
    public String batchUpdateGender(@RequestParam String oldGender, @RequestParam String newGender) {
        int count = 0;
        for (Patient p : service.getAllPatients()) {
            if (oldGender.equals(p.getGender())) {
                p.setGender(newGender);
                service.updatePatient(p);
                count++;
            }
        }
        return "更新了 " + count + " 位患者的性别";
    }
}