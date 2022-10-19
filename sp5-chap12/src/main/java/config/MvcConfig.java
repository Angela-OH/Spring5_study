package config;

import org.springframework.validation.Validator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

import controller.RegisterRequestValidator;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	// 글로벌 범위 Validator 설정 -> @Valid annotation으로 사용 가능
	@Override
	public Validator getValidator() {
		return new RegisterRequestValidator();
	}
	
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	// ViewResolver 설정을 추가
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp");
	}
	
	// 요청 경로와 View 이름만 연결해주는 단순 연결에 사용 (특별한 logic x)
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main").setViewName("main");
	}
	
	// 이름을 반드시 messageSource로 설정해야함.
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms =
				new ResourceBundleMessageSource();
		ms.setBasenames("message.label");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
}
