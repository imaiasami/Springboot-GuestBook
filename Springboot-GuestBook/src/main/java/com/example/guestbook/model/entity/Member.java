package com.example.guestbook.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
    private String member_id;
    private String password;
    private String name;

}
