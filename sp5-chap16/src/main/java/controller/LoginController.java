package controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;

import spring.AuthInfo;
import spring.AuthService;
import spring.WrongIdPasswordException;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
	private AuthService authService;
	
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	@GetMapping
	public String form(LoginCommand loginCommand,
			@CookieValue(value="REMEMBER", required=false) Cookie rCookie) {
		if (rCookie != null) {
			loginCommand.setEmail(rCookie.getValue());
			loginCommand.setRememberEmail(true);
		}
		return "login/loginForm";
	}
	
	@PostMapping
	public String summit(LoginCommand loginCommand, Errors errors, HttpSession session,
			HttpServletResponse response) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if (errors.hasErrors()) {
			return "login/loginForm";
		}
		try {
			AuthInfo authInfo = authService.authenticate(
					loginCommand.getEmail(),
					loginCommand.getPassword());
			
			session.setAttribute("authInfo", authInfo);
			
			// 로그인 시 쿠키 생성
			Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
			rememberCookie.setPath("/");
			if (loginCommand.isRememberEmail()) {
				rememberCookie.setMaxAge(60*60*24*30); // 30일간 유지
			} else { 
				rememberCookie.setMaxAge(0); // 이메일 기억하기가 하기 싫은 경우 바로 쿠키 삭제
			}
			response.addCookie(rememberCookie);
			
			return "login/loginSuccess";
			
		} catch (WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	}

}
