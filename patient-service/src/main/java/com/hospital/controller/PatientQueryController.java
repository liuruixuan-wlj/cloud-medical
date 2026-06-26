package com.hospital.controller;

import com.hospital.entity.Patient;
import com.hospital.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "02-患者查询", description = "各种查询接口")
@RestController
@RequestMapping("/api/patients")
public class PatientQueryController {

    @Autowired
    private PatientService service;

    @Operation(summary = "10. 按年龄查询")
    @GetMapping("/age/{age}")
    public List<Patient> findByAge(@PathVariable Integer age) {
        List<Patient> result = new java.util.ArrayList<>();
        for (Patient p : service.getAllPatients()) {
            if (age.equals(p.getAge())) result.add(p);
        }
        return result;
    }

    @Operation(summary = "11. 年龄大于")
    @GetMapping("/age/gt/{age}")
    public List<Patient> findByAgeGreaterThan(@PathVariable Integer age) {
        List<Patient> result = new java.util.ArrayList<>();
        for (Patient p : service.getAllPatients()) {
            if (p.getAge() > age) result.add(p);
        }
        return result;
    }

    @Operation(summary = "12. 年龄小于")
    @GetMapping("/age/lt/{age}")
    public List<Patient> findByAgeLessThan(@PathVariable Integer age) {
        List<Patient> result = new java.util.ArrayList<>();
        for (Patient p : service.getAllPatients()) {
            if (p.getAge() < age) result.add(p);
        }
        return result;
    }

    @Operation(summary = "13. 按性别查询")
    @GetMapping("/gender/{gender}")
    public List<Patient> findByGender(@PathVariable String gender) {
        List<Patient> result = new java.util.ArrayList<>();
        for (Patient p : service.getAllPatients()) {
            if (gender.equals(p.getGender())) result.add(p);
        }
        return result;
    }

    @Operation(summary = "14. 获取所有男性")
    @GetMapping("/male")
    public List<Patient> getMale() {
        return findByGender("男");
    }

    @Operation(summary = "15. 获取所有女性")
    @GetMapping("/female")
    public List<Patient> getFemale() {
        return findByGender("女");
    }

    @Operation(summary = "16. 查询成年人")
    @GetMapping("/adult")
    public List<Patient> getAdults() {
        return findByAgeGreaterThan(17);
    }

    @Operation(summary = "17. 查询未成年人")
    @GetMapping("/child")
    public List<Patient> getChildren() {
        return findByAgeLessThan(18);
    }

    @Operation(summary = "18. 分页查询")
    @GetMapping("/page")
    public List<Patient> getPage(@RequestParam int page, @RequestParam int size) {
        List<Patient> all = service.getAllPatients();
        int start = page * size;
        int end = Math.min(start + size, all.size());
        if (start >= all.size()) return new java.util.ArrayList<>();
        return all.subList(start, end);
    }

    @Operation(summary = "19. 按姓名模糊查询")
    @GetMapping("/name/{keyword}")
    public List<Patient> findByNameLike(@PathVariable String keyword) {
        List<Patient> result = new java.util.ArrayList<>();
        for (Patient p : service.getAllPatients()) {
            if (p.getName().contains(keyword)) result.add(p);
        }
        return result;
    }

    @Operation(summary = "20. 获取第一个")
    @GetMapping("/first")
    public Patient getFirst() {
        List<Patient> all = service.getAllPatients();
        return all.isEmpty() ? null : all.get(0);
    }

    @Operation(summary = "21. 获取最后一个")
    @GetMapping("/last")
    public Patient getLast() {
        List<Patient> all = service.getAllPatients();
        return all.isEmpty() ? null : all.get(all.size() - 1);
    }
}