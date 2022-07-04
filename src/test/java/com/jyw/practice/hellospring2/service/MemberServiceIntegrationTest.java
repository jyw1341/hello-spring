package com.jyw.practice.hellospring2.service;

import com.jyw.practice.hellospring2.domain.Member;
import com.jyw.practice.hellospring2.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 스프링 컨테이너 + 자바코드 + DB 모두 테스트 하는것을 통합테스트
 * 테스트 코드로 테스트를 하면 직접 웹브라우저를 켜서 일일히 수동으로 회언가입 같은 작업을 진행 안해도 된다.
 */

/**
 * 테스트는 반복할수 있어야한다.
 */

/**
 * 단순 자바코드가 아니라 스프링 구동 테스트 하려면 어노테이션으로 세팅해야함
 * 스프링 컨테이너와 테스트를 함께 실행
 */
@SpringBootTest

/**
 * Transactional 어노테이션을 테스트케이스에 적용하면, 테스트를 실행할때 트랜잭션을 먼저 실행하고 테스트가 끝나면 롤백해줌.
 * 즉 DB 변경 확인하고 테스트 끝나면 원래대로
 */
@Transactional
class MemberServiceIntegrationTest {

    /**
     * 테스트할때는 편하게 필드기반 인젝션으로..
     */
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    /**
     * DB 테스트는 로컬 혹은 테스트용 DB 에서 실행
     */
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hbcvbc");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외_검증(){
        //given
        Member member1 = new Member();
        member1.setName("vxcvx");

        //when
        Member member2 = new Member();
        member2.setName("vxcvx");

        //then
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다.");
//        } catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}