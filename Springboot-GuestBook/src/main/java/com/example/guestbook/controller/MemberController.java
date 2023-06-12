package com.example.guestbook.controller;

import com.example.guestbook.model.dto.LoginDto;
import com.example.guestbook.model.dto.MemberDto;
import com.example.guestbook.model.entity.Member;
import com.example.guestbook.repository.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequestMapping("members")
@RequiredArgsConstructor
@Controller
public class MemberController {

	private final MemberMapper memberMapper;

	// 회원가입 페이지 이동
	@GetMapping("joinForm")
	public String joinForm(Model model) {
		model.addAttribute("member", new MemberDto());
		return "members/join";
	}

	// 회원가입
	@PostMapping("join")
	public String joinMember(@Validated @ModelAttribute("member") MemberDto memberDto, BindingResult result) {
		if (result.hasErrors()) {
			return "members/join";
		}
		memberMapper.addMember(memberDto.toMember(memberDto));
		return "redirect:/";
	}

	// 로그인 페이지 이동
	@GetMapping("login")
	public String loginMemberForm(@RequestParam(defaultValue = "/") String redirectURL, Model model) {
		model.addAttribute("member", new LoginDto());
		model.addAttribute("redirectURL", redirectURL);
		return "members/login";
	}

	// 로그인
	@PostMapping("login")
	public String loginMember(@Validated @ModelAttribute("member") LoginDto loginDto, BindingResult result,
			HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectURL) {
		if (result.hasErrors()) {
			return "members/login";
		}
		Member findMember = memberMapper.findMember(loginDto.getMember_id());
		if (findMember != null && findMember.getPassword().equals(loginDto.getPassword())) {
			HttpSession session = request.getSession();
			session.setAttribute("login", findMember);
		} else {
			result.reject("loginFail", "아이디 또는 패스워드가 틀립니다.");
			return "members/login";
		}

		return "redirect:" + redirectURL;
	}

	// 로그아웃
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}

}
