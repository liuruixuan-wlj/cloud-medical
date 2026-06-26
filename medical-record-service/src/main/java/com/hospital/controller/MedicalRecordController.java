package com.hospital.controller;

import com.hospital.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService service;

    @GetMapping("/{recordId}/patient/{patientId}")
    public String getDetail(@PathVariable Long recordId, @PathVariable Long patientId) {
        return service.getRecordWithPatient(recordId, patientId);
    }
}