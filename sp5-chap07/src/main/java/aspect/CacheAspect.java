package aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CacheAspect {
	
	private Map<Long, Object> cache = new HashMap<>();
	
	// Pointcut을 다른 패키지/클래스로부터 가져와서 사용할 수 있음
	// 깔끔하게 코딩하려면 별도의 클래스에서 Pointcut을 정의하고 관리하면 좋음
	@Pointcut("execution(public * chap07..*(long))")
	public void cacheTarget() {
		
	}
	
	@Around("cacheTarget()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		Long num = (Long) joinPoint.getArgs()[0];
		
		// joinPoint.proceed()를 실행하지 않음 -> 대상 객체 실행 x -> ExeTimeAspect 실행 x
		if (cache.containsKey(num)) {
			System.out.printf("CacheAspect: Cache에서 구함[%d]\n", num);
			return cache.get(num);
		}
		
		Object result = joinPoint.proceed();
		cache.put(num, result);
		System.out.printf("CacheAspect: Cache에 추가[%d]\n", num);
		return result;
	}

}
