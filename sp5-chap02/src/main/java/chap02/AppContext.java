package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // AppContext 클래스를 스프링 설정 클래스로 지정
public class AppContext {

	@Bean // 스프링이 생성하는 객체 => Bean
	public Greeter greeter() {
		Greeter g = new Greeter();
		g.setFormat("%s, 안녕하세요!");
		return g;
	}
}
