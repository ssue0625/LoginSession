package com.example.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.domain.User;
import com.example.project.service.UserManageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserManageController {

	private final UserManageService userManageService;

	@GetMapping("/user/sign-out")
	public String signOut(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}

	@PostMapping("/user/sign-in")
	public String login(User user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		User findUser = userManageService.findByLoginIdAndPassword(user);
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", findUser);
		if (findUser == null) {
			redirectAttributes.addFlashAttribute("message", "아이디 또는 비밀번호 확인해주세요.");
			return "redirect:/user/sign-in";
		}
		return "redirect:/";
	}

	@GetMapping("/user/sign-in")
	public String signIn(User user) {
		return "user/sign-in";
	}

	@GetMapping("/")
	public String index(@SessionAttribute(name = "loginUser", required = false) User user, Model model) {
		if (user == null) {
			return "redirect:/user/sign-in";
		}
		model.addAttribute("user", user);
		return "index";
	}

	@PostMapping("/user/sign-up")
	public String singUp(User user) {
		userManageService.save(user);
		return "user/sign-in";
	}

	@GetMapping("/user/sign-up")
	public String signUp(@ModelAttribute("user") User user) {
		return "user/sign-up";
	}

}
