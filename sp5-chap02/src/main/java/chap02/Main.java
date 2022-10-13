package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// AppContext에 정의한 @Bean 설정 정보를 읽어와 Greeter 객체를 생성하고 초기화
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppContext.class);
		// AnnotationConfigApplicationContext가 생성한 빈 객체를 검색
		Greeter g = ctx.getBean("greeter", Greeter.class); // greeter() 메서드가 생성한 Greeter 객체를 return
		String msg = g.greet("스프링");
		System.out.println(msg);
		ctx.close();
	}

}
