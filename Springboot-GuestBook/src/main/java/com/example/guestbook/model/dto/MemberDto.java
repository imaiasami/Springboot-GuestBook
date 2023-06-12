package com.example.guestbook.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.guestbook.model.entity.Member;



@Data
public class MemberDto {
    @Size(min = 4, max = 20)
    private String member_id;

    @Size(min = 4, max = 20)
    private String password;

    @NotBlank
    private String name;
    
    public Member toMember(MemberDto memberDto) {
        return new Member(member_id, password, name);
    }
}
