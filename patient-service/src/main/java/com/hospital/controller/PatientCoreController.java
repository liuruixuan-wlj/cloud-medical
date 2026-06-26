package com.hospital.controller;

import com.hospital.entity.Patient;
import com.hospital.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "01-患者核心操作", description = "基础增删改查")
@RestController
@RequestMapping("/api/patients")
public class PatientCoreController {

    @Autowired
    private PatientService service;

    @Operation(summary = "1. 新增患者")
    @PostMapping
    public Patient add(@RequestBody Patient patient) {
        return service.addPatient(patient);
    }

    @Operation(summary = "2. 根据ID查询（演示Redis缓存）")
    @GetMapping("/{id}")
    public Patient get(@PathVariable Long id) {
        return service.getPatient(id);
    }

    @Operation(summary = "3. 查询所有")
    @GetMapping
    public List<Patient> list() {
        return service.getAllPatients();
    }

    @Operation(summary = "4. 修改患者")
    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @RequestBody Patient patient) {
        patient.setId(id);
        return service.updatePatient(patient);
    }

    @Operation(summary = "5. 删除患者")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.deletePatient(id) ? "删除成功" : "删除失败";
    }

    @Operation(summary = "6. 统计数量")
    @GetMapping("/count")
    public int count() {
        return service.getCount();
    }

    @Operation(summary = "7. 检查是否存在")
    @GetMapping("/exists/{id}")
    public boolean exists(@PathVariable Long id) {
        return service.getPatient(id) != null;
    }

    @Operation(summary = "8. 删除所有")
    @DeleteMapping("/all")
    public String deleteAll() {
        for (Patient p : service.getAllPatients()) {
            service.deletePatient(p.getId());
        }
        return "已删除所有";
    }

    @Operation(summary = "9. 健康检查")
    @GetMapping("/health")
    public String health() {
        return "patient-service is running!";
    }
}