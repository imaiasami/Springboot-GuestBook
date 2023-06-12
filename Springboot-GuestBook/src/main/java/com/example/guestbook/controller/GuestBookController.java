package com.example.guestbook.controller;

import com.example.guestbook.model.entity.GuestBook;
import com.example.guestbook.model.entity.Member;
import com.example.guestbook.repository.GuestBookMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("guestbooks")
@Controller
public class GuestBookController {

	private final GuestBookMapper guestBookMapper;

	// 방명록 전체 리스트
	@PostMapping("list")
	public String list(@SessionAttribute(name = "login", required = false) Member loginMember, Model model) {
		List<GuestBook> guestBooks = guestBookMapper.findAllGuestBooks();
		model.addAttribute("guestBooks", guestBooks);
		return "guestBooks/list";
	}

	// 방명록 쓰기 페이지 이동
	@PostMapping("writeForm")
	public String writeForm(@RequestParam("id") Long member_id, Model model) {
		return "guestBooks/write";
	}

	// 방명록 쓰기
	@PostMapping("write")
	public String write(@SessionAttribute(name = "login") Member loginMember, @ModelAttribute GuestBook guestBook) {

		// 로그인 사용자의 아이디 정보를 추가
		guestBook.setMember_id(loginMember.getMember_id());

		// 방명록 저장
		guestBookMapper.addGuestBook(guestBook);

		return "redirect:/guestBooks/list";
	}

	// 방명록 수정 페이지 이동
	@GetMapping("update/{guestbook_id}")
	public String updateForm(@SessionAttribute Member loginMember, @PathVariable Long guestbook_id, Model model) {

		GuestBook guestBook = guestBookMapper.findGuestBook(guestbook_id);

		// 작성자 아이디와 로그인 사용자의 아이디가 같은지 비교
		if (guestBook == null || !guestBook.getMember_id().equals(loginMember.getMember_id())) {
			return "redirect:/";
		}

		model.addAttribute("guestBook", guestBook);

		return "guestBooks/update";
	}

	// 방명록 수정
	@PostMapping("update/{guestbook_id}")
	public String update(@SessionAttribute Member loginMember, @ModelAttribute GuestBook updateGuestBook) {

		GuestBook findGuestBook = guestBookMapper.findGuestBook(updateGuestBook.getGuestbook_id());

		// 작성자 아이디와 로그인 사용자의 아이디가 같은지 비교
		if (findGuestBook == null || !findGuestBook.getMember_id().equals(loginMember.getMember_id())) {
			return "redirect:/";
		}

		guestBookMapper.updateGuestBook(updateGuestBook);

		return "redirect:/guestbooks/list";
	}

	// 방명록 삭제
	@GetMapping("remove/{guestbook_id}")
	public String remove(@SessionAttribute Member loginMember, @PathVariable Long guestbook_id) {

		GuestBook findGuestBook = guestBookMapper.findGuestBook(guestbook_id);

		// 작성자 아이디와 로그인 사용자의 아이디가 같은지 비교
		if (findGuestBook == null || !findGuestBook.getMember_id().equals(loginMember.getMember_id())) {
			return "redirect:/";
		}

		guestBookMapper.deleteGuestBook(guestbook_id);
		return "redirect:/guestbooks/list";
	}

}
