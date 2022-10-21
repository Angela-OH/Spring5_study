package spring;

import org.springframework.transaction.annotation.Transactional;

// 2개 이상의 query를 한 작업으로 실행해야 할 때 Transaction 사용 -> (이메일 변경 + 이메일 인증 상태 변경)
// query 중 하나라도 실패하면 전체 실패로 간주 -> DB를 기존 상태로 Rollback
// 모든 query가 성공하면 -> DB에 commit
// 스프링은 Transaction 범위를 쉽게 지정 가능 
// (1. PlatformTransactionManager 빈 설정 2. 필요한 메소드에 @Transactional annotation 활성화)
@Transactional
public class ChangePasswordService {

	private MemberDao memberDao;
	
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException();
		
		member.changePassword(oldPwd, newPwd);
		
		memberDao.update(member);
	}
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
}
