package com.hospital.service;

import com.hospital.entity.Patient;
import com.hospital.feign.PatientFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    @Autowired
    private PatientFeignClient patientClient;

    public String getRecordWithPatient(Long recordId, Long patientId) {
        System.out.println("🔵 [远程调用] 开始调用患者服务获取患者信息...");
        try {
            Patient patient = patientClient.getPatient(patientId);
            System.out.println("✅ [远程调用] 成功！患者: " + patient.getName());
            return "病历ID: " + recordId +
                    ", 患者: " + patient.getName() +
                    ", 性别: " + patient.getGender() +
                    ", 年龄: " + patient.getAge();
        } catch (Exception e) {
            System.err.println("❌ [远程调用] 失败: " + e.getMessage());
            return "远程调用失败: " + e.getMessage();
        }
    }
}