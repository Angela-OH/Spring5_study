package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;

@Configuration
@EnableAspectJAutoProxy  
// @Aspect annotation을 붙인 클래스를 Aspect로 적용하려면 해당 annotation을 설정 클래스에 붙여줘야함
// 그러면 spring은 @Aspect이 붙은 빈 객체를 찾아서 빈 객체의 @Pointcut(Aspect를 적용할 대상) 설정과 @Around(언제 Aspect을 적용할지) 설정을 사용함
public class AppCtx {
	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}
	
	@Bean
	public Calculator calculator() {
	// calculator 빈에 ExeTimeAspect에서 정의한 Aspect 기능인 measure()를 적용함 (public 메서드이기 때문에)
		return new RecCalculator();
	}

}
