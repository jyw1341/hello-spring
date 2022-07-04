package com.jyw.practice.hellospring2.service;

import com.jyw.practice.hellospring2.domain.Member;
import com.jyw.practice.hellospring2.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service 어노테이션을 사용하면 스프링이 이 클래스를 생성하여 컨테이너에 서비스객체로 등록한다
 */
//@Service

/**
 * JPA 를 사용하려면 서비스 계층에 transactional 필요
 */
@Transactional
public class MemberService {

    /**
     * 서비스클래스에서는 비즈니스 로직을 실행하기때문에 비즈니스에 가까운 용어를 사용할것.
     */
    private final MemberRepository memberRepository;

    /**
     * Autowired 어노테이션을 사용하여 저장소 객체와의 결합
     */
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        /**
         * 메서드의 실행시간을 측정하는 로직이다
         * 이걸 몇백개에 적용하고 관리해야한다고 생각해보자 끔찍하다..
         *  그래서 나온게 AOP다.
         */
//        long start = System.currentTimeMillis();
//
//        try {
//            validateDuplicateMember(member);
//            memberRepository.save(member);
//            return member.getId();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join = " + timeMs + "ms");
//        }

        validateDuplicateMember(member);       //같은 이름이 있는 중복 회원 허용 x
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(member1 -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
