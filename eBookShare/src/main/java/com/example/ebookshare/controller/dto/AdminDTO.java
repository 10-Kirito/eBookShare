package com.example.ebookshare.controller.dto;


import lombok.Data;

//接受前端登录请求的参数
@Data
public class AdminDTO {
    private String username;
    private String password;
    private String email;
    private String id;
    private String token;
    private String avatarurl;
}
