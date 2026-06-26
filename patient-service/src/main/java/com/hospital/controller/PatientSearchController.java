package com.hospital.controller;

import com.hospital.entity.Patient;
import com.hospital.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@Tag(name = "05-患者搜索", description = "高级搜索接口")
@RestController
@RequestMapping("/api/patients")
public class PatientSearchController {

    @Autowired
    private PatientService service;

    @Operation(summary = "32. 综合搜索（姓名+性别）")
    @GetMapping("/search")
    public List<Patient> search(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String gender) {
        List<Patient> result = new ArrayList<>();
        for (Patient p : service.getAllPatients()) {
            boolean match = true;
            if (name != null && !p.getName().contains(name)) match = false;
            if (gender != null && !gender.equals(p.getGender())) match = false;
            if (match) result.add(p);
        }
        return result;
    }

    @Operation(summary = "33. 按姓名搜索")
    @GetMapping("/search/name")
    public List<Patient> searchByName(@RequestParam String keyword) {
        List<Patient> result = new ArrayList<>();
        for (Patient p : service.getAllPatients()) {
            if (p.getName().contains(keyword)) result.add(p);
        }
        return result;
    }

    @Operation(summary = "34. 按性别搜索")
    @GetMapping("/search/gender")
    public List<Patient> searchByGender(@RequestParam String gender) {
        List<Patient> result = new ArrayList<>();
        for (Patient p : service.getAllPatients()) {
            if (gender.equals(p.getGender())) result.add(p);
        }
        return result;
    }

    @Operation(summary = "35. 按年龄范围搜索")
    @GetMapping("/search/age")
    public List<Patient> searchByAge(@RequestParam(required = false) Integer min,
                                     @RequestParam(required = false) Integer max) {
        List<Patient> result = new ArrayList<>();
        for (Patient p : service.getAllPatients()) {
            if (min != null && p.getAge() < min) continue;
            if (max != null && p.getAge() > max) continue;
            result.add(p);
        }
        return result;
    }

    @Operation(summary = "36. 多条件组合搜索")
    @GetMapping("/search/combo")
    public List<Patient> comboSearch(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String gender,
                                     @RequestParam(required = false) Integer minAge,
                                     @RequestParam(required = false) Integer maxAge) {
        List<Patient> result = new ArrayList<>();
        for (Patient p : service.getAllPatients()) {
            boolean match = true;
            if (name != null && !p.getName().contains(name)) match = false;
            if (gender != null && !gender.equals(p.getGender())) match = false;
            if (minAge != null && p.getAge() < minAge) match = false;
            if (maxAge != null && p.getAge() > maxAge) match = false;
            if (match) result.add(p);
        }
        return result;
    }
}