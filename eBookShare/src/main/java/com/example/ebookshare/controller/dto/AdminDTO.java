package com.example.ebookshare.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//接受前端登录请求的参数
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private String username;
    private String password;
    private String email;
    private String id;
    private String phone;
    private String token;
    private String avatarurl;
}
