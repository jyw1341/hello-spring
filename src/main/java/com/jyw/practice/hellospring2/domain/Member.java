package com.jyw.practice.hellospring2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * JPA는 자바진영의 표준 인터페이스
 * 인터페이스의 구현은 여러 회사들이 함
 * 대표적으로 사용되는 구현체가 hibernate
 *
 * ORM Object Relational Database Mapping
 */

/**
 * Entity 어노테이션으로 객체와 DB 매핑
 * JPA가 관리하는 엔티티가 됨
 */
@Entity
public class Member {
    /**
     * Id = Identity
     * db에 값을 넣지 않아도 값을 자동으로 생성해줌. 이것을 아이덴티티 전략이라고 함
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //데이터를 구분하기 위해 시스템이 저장하는 아이디
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
