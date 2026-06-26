package com.hospital.service;

import com.hospital.entity.Patient;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PatientService {

    private final ConcurrentHashMap<Long, Patient> database = new ConcurrentHashMap<>();
    private Long idCounter = 1L;

    public PatientService() {
        // 初始化测试数据
        database.put(1L, new Patient(1L, "张三", "男", 25));
        database.put(2L, new Patient(2L, "李四", "女", 30));
        database.put(3L, new Patient(3L, "王五", "男", 28));
        idCounter = 4L;
    }

    @Cacheable(value = "patients", key = "#id")
    public Patient getPatient(Long id) {
        System.out.println("🔴 [数据库查询] 患者ID: " + id);
        return database.get(id);
    }

    public Patient addPatient(Patient patient) {
        patient.setId(idCounter++);
        database.put(patient.getId(), patient);
        System.out.println("✅ [新增患者] " + patient.getName());
        return patient;
    }

    @CacheEvict(value = "patients", key = "#patient.id")
    public Patient updatePatient(Patient patient) {
        if (database.containsKey(patient.getId())) {
            database.put(patient.getId(), patient);
            System.out.println("🔄 [更新患者] " + patient.getName());
            return patient;
        }
        return null;
    }

    @CacheEvict(value = "patients", key = "#id")
    public boolean deletePatient(Long id) {
        System.out.println("🗑️ [删除患者] ID: " + id);
        return database.remove(id) != null;
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(database.values());
    }

    public int getCount() {
        return database.size();
    }
}