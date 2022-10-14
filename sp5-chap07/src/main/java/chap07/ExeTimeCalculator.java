package chap07;

// proxy: 핵심 기능의 실행 (factorial)은 다른 객체에 위임하고 부가적인 기능 (시간 측정)을 제공하는 객체
// AOP -> 공통 기능과 핵심 기능을 분리
public class ExeTimeCalculator implements Calculator {
	private Calculator delegate;
	
	public ExeTimeCalculator(Calculator delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public long factorial(long num) {
		long start = System.nanoTime();
		long result = delegate.factorial(num);
		long end = System.nanoTime();
		System.out.printf("%s.factorial(%d) 실행 시간 = %d\n",
				delegate.getClass().getSimpleName(),
				num, (end - start));
		return result;
	}
}
