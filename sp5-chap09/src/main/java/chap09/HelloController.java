package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Controller란 웹 요청을 처리하고 그 결과를 View에 전달하는 spring 빈 객체
@Controller
public class HelloController {

	// 경로는 서블릿 컨텍스트 경로를 기준으로 함
	// 참고로 여기서 컨텍스트 경로는 sp5-chap09
	@GetMapping("/hello") // 처리할 경로 지정
	public String hello(Model model,
			@RequestParam(value="name", required=false) String name) {
		model.addAttribute("greeting", "안녕하세요, " + name); // greeting이라는 모델 속성에 값을 저장
		return "hello"; // Controller 처리 결과를 보여줄 view 이름으로 'hello'를 사용
	}
}
