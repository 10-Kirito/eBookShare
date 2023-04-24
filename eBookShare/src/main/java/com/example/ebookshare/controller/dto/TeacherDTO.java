package com.example.ebookshare.controller.dto;

import lombok.Data;

@Data
public class TeacherDTO {
    private Integer tnumber;
    private String username;
    private String password;
    private String name;
    private String avatarurl;
    private String token;
}
