package com.example.guestbook.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

@Data
public class GuestBook {
	private Long guestbook_id;
	private String contents;
	private String member_id;
	private LocalDateTime created_time;
}
