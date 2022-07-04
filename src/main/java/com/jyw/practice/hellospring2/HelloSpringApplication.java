package com.jyw.practice.hellospring2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 스프링은 com.jyw.practice.hellospring2 패키지 및 하위 패키지만 컴포넌트 스캔을 해서 스프링빈으로 등록한다.
 * 스프링빈으로 등록할때는 기본적으로 싱글톤으로 등록한다.
 */
@SpringBootApplication
public class HelloSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringApplication.class, args);
    }
}
