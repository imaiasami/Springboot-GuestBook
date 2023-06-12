package com.example.guestbook.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.guestbook.model.entity.GuestBook;
import com.example.guestbook.repository.GuestBookMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

	private final GuestBookMapper guestBookMapper;

	@GetMapping("/")
	public String home(Model model) {
		List<GuestBook> guestBooks = guestBookMapper.findAllGuestBooks();
		model.addAttribute("guestBooks", guestBooks);
		return "guestBooks/list";
	}
}
