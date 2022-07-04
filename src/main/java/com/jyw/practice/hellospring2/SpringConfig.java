package com.jyw.practice.hellospring2;

import com.jyw.practice.hellospring2.repository.MemberRepository;
import com.jyw.practice.hellospring2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 컴포넌트 스캔 방식이 아니라 자바 코드로 직접 스프링 빈 등록을 할수도 있다.
 * Configuration 어노테이션이 등록된 객체가 있으면 스프링은 설정을 읽고 스프링빈을 등록한다.
 * Controller 객체는 컴포넌트 스캔 방식으로만 가능.
 */
@Configuration
public class SpringConfig {

//    private final DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private final EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

    /**
     * 직접 설정파일을 작성하는 방식의 장점은 무엇일까?
     * 만약 MemberRepository 의 구현체인 MemoryMemberRepository 에서 다른 구현체로 교체를 해야하는 상황이라고 가정해보자
     * 직접 등록 방식을 사용하면 다른 코드는 손댈필요 없이 리턴값만 교체할 구현체로 바꿔주면 된다.
     */
//    @Bean
//    public MemberRepository memberRepository() {
//       return new MemoryMemberRepository();
//       return new JdbcMemberRepository(dataSource);
//       return new JdbcTemplateMemberRepository(dataSource);
//       return new JpaMemberRepository(em);
//    }

    /**
     * AOP 같은 특별한 객체는 컴포넌트 스캔보다 설정에 따로 명시하는게 알아보기 쉽다.
     */
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

}
