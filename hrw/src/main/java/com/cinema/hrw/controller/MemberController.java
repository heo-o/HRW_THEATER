package com.cinema.hrw.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.cinema.hrw.dto.MemberDTO;
import com.cinema.hrw.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
    // ��� ���� ������ ����
	private final MemberService memberService;
	
	// ȸ�� ���� ������ ��� ��û
	@GetMapping("/member/join")
	public String joinForm() {
		return "/member/join";
	}
	
	// ȸ�� ���� ó��
	@PostMapping("/member/join")
	public String join(@ModelAttribute MemberDTO memberDTO) {
		memberService.join(memberDTO);
		return "/member/login";
	}
	
	// �α��� ������ ��� ��û
	@GetMapping("/member/login")
	public String loginForm() {
		return "/member/login";
	}
	
	// �α��� ó��
	@PostMapping("/member/login")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
	    MemberDTO loginResult = memberService.login(memberDTO);
	    
	    // �α��� ������, ���ǿ� ID ����
	    if (loginResult != null) {
	        session.setAttribute("loginId", loginResult.getUserName()); 
	        return "main";
	    } else {
	        // �α��� ���н�
	        return "/member/login";
	    }
	}
	
	// ��й�ȣ ã�� ������ ��� ��û
	@GetMapping("/member/findPassword")
	public String findPasswordForm() {
		return "/member/find_password";
	}
	
	// ��й�ȣ ã�� ó�� (���� ���� �����Ǿ� ����)
	@PostMapping("/member/findPassword")
	public String findPassword(@ModelAttribute MemberDTO memberDTO) {
		MemberDTO foundMember = memberService.searchPw(memberDTO);
		if (foundMember != null) {
			// ȸ�� ������ ���� ��� ��� ������	
			return "/member/find_password_ok";
		} else {
			return "/member/find_password_ng";
		}
		
	}
	
	// ID ã�� ������ ��� ��û
	@GetMapping("/member/findId")
	public String findIdForm() {
		return "/member/find_id";
	}
	
	// ID ã�� ó�� (���� ���� �����Ǿ� ����)
	@PostMapping("/member/findId")
	public String findId(@ModelAttribute MemberDTO memberDTO) {
	    MemberDTO foundMember = memberService.searchId(memberDTO);

	    if (foundMember != null) {
	        // ȸ�� ������ ���� ��� ��� ������
	        return "/member/find_id_ok";
	    } else {
	        // ȸ�� ������ ���� ��� ��� ������
	        return "/member/find_id_ng";
	    }
	}

	
	// �α׾ƿ� ó�� (���� ����)
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "main";
	}
	
	// ����� ���� ������ ��� ��û
	@GetMapping("/member/userInfo")
	public String userInfoForm() {
		return "/member/userInfo";
	}
	
	// ����� ������ ȸ�� ���� ��û
	@PostMapping("/member/update")
	public String userUpdate() {
		return null;
	}
	
	// ����� Ż�� ��û
	@PostMapping("/mmeber/delete")
	public String userDelete() {
		return null;
	}

}
