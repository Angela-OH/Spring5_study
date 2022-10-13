package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;
import spring.MemberPrinter;
import spring.MemberListPrinter;
import spring.MemberInfoPrinter;
import spring.MemberSummaryPrinter;

@Configuration
@ComponentScan(basePackages = {"spring"})
public class AppCtx {

	/*
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }
    
    @Bean
    public MemberRegisterService memberRegSvc() {
        return new MemberRegisterService(memberDao());
    }
    
    @Bean
    public ChangePasswordService changePwdSvc() {
        return new ChangePasswordService();
    }
    */
	
    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }
    
    /*
    @Bean
    public MemberListPrinter listPrinter() {
        return new MemberListPrinter();
    }
    
    @Bean
    public MemberInfoPrinter infoPrinter() {
    	MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
    	infoPrinter.setPrinter(memberPrinter2());
    	return infoPrinter;
    }
    */
    
    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1() {
    	return new MemberPrinter();
    }
    
    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberPrinter2() {
    	return new MemberSummaryPrinter();
    }
}
