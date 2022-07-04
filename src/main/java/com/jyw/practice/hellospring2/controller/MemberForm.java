package com.jyw.practice.hellospring2.controller;

public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    /**
     * 스프링이 form 에서 post 로 넘어온 값을  setter 를 사용해서 초기화한다.
     */
    public void setName(String name) {
        this.name = name;
    }
}
