package com.jyw.practice.hellospring2.repository;

import com.jyw.practice.hellospring2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JpaRepository 를 상속받은 인터페이스는 스프링 데이터가 자동으로 구현체를 만들어주고 스프링 빈에 등록해준다.
 * 거의 모든 부분이 공통화되어있다.
 */

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    /**
     * 공통화가 불가능한 영역은 인터페이스를 규칙대로 작성해서 해결한다.
     * @param name
     * @return
     */
    @Override
    Optional<Member> findByName(String name);
}
