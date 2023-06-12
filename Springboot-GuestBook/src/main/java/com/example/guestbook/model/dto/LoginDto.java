package com.example.guestbook.model.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LoginDto {
    @Size(min = 4, max = 20)
    private String member_id;

    @Size(min = 4, max = 20)
    private String password;
}
