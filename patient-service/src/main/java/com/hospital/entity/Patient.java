package com.hospital.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

@Schema(description = "患者信息")
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "患者ID", example = "1")
    private Long id;

    @Schema(description = "姓名", example = "张三")
    private String name;

    @Schema(description = "性别", example = "男")
    private String gender;

    @Schema(description = "年龄", example = "25")
    private Integer age;

    @Schema(description = "电话", example = "13800138000")
    private String phone;

    @Schema(description = "地址", example = "杭州市西湖区")
    private String address;

    public Patient() {}

    public Patient(Long id, String name, String gender, Integer age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}