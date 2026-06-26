package com.hospital.controller;

import com.hospital.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Map<String, Object> response = new HashMap<>();

        // 演示账号: admin/123456, user/123456
        if (("admin".equals(username) || "user".equals(username)) && "123456".equals(password)) {
            String token = JwtUtil.generateToken(username);
            response.put("code", 200);
            response.put("success", true);
            response.put("token", token);
            response.put("username", username);
            response.put("message", "登录成功");
        } else {
            response.put("code", 401);
            response.put("success", false);
            response.put("message", "用户名或密码错误");
        }
        return response;
    }

    @GetMapping("/verify")
    public Map<String, Object> verify(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        Map<String, Object> response = new HashMap<>();

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.put("valid", false);
            response.put("message", "缺少Token");
            return response;
        }

        String token = authHeader.substring(7);
        boolean valid = JwtUtil.validateToken(token);
        response.put("valid", valid);
        response.put("message", valid ? "Token有效" : "Token无效");
        return response;
    }
}