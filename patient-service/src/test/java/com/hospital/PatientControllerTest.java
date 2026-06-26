package com.hospital;

import com.hospital.controller.PatientCoreController;
import com.hospital.entity.Patient;
import com.hospital.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientCoreController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService service;

    @Test
    void testGetPatient() throws Exception {
        Patient patient = new Patient(1L, "测试患者", "男", 25);
        when(service.getPatient(1L)).thenReturn(patient);

        mockMvc.perform(get("/api/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("测试患者"));
        System.out.println("✅ testGetPatient 通过");
    }

    @Test
    void testAddPatient() throws Exception {
        Patient patient = new Patient(null, "新增患者", "女", 30);
        when(service.addPatient(any())).thenReturn(patient);

        mockMvc.perform(post("/api/patients")
                        .contentType("application/json")
                        .content("{\"name\":\"新增患者\",\"gender\":\"女\",\"age\":30}"))
                .andExpect(status().isOk());
        System.out.println("✅ testAddPatient 通过");
    }

    @Test
    void testDeletePatient() throws Exception {
        when(service.deletePatient(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/patients/1"))
                .andExpect(status().isOk());
        System.out.println("✅ testDeletePatient 通过");
    }
}