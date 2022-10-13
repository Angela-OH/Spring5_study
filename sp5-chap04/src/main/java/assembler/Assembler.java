package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

public class Assembler {
	// Assembler의 역할: Assembler가 제공하는 메서드를 이용해서 필요한 객체를 구함 + 그 객체를 사용
	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	
	public Assembler() {
		memberDao = new MemberDao();
		regSvc = new MemberRegisterService(memberDao);
		pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
	}
	
	public MemberDao getMemberDao() {
		return memberDao;
	}
	
	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}
	
	public ChangePasswordService getChangePasswordService() {
		return pwdSvc;
	}

}
