package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.Calculator;
import config.AppCtx;

public class MainAspect {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppCtx.class);
		
		Calculator cal = ctx.getBean("calculator", Calculator.class);
		long fiveFact = cal.factorial(5);
		System.out.println("cal.factorial(5) = " + fiveFact);
		// MainAspect -> $Proxy17 -> ExeTimeAspect -> ProceedingJoinPoint -> RecCalculator
		// 마지막에 return한 객체는 $Proxy17 (AOP를 적용했기 때문에)
		System.out.println(cal.getClass().getName()); 
		ctx.close();
	}
}
