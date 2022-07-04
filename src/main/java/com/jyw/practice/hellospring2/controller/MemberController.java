package com.jyw.practice.hellospring2.controller;


import com.jyw.practice.hellospring2.domain.Member;
import com.jyw.practice.hellospring2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 멤버 객체, 저장소, 그리고 간단한 회원가입 서비스 로직의 구현은 완료가 되었다.
 * 그렇다면 이제는 구현된 기능을 실제 화면에서 보여줘야할 차례이다.
 * 화면을 출력하기 위해서는 외부 요청을 받는 Controller 객체가 필요하다. 그리고 이 Controller 는 비즈니스 로직을 구현한 Service 객체를 의존하고 있다.
 */

/**
 * 스프링 컨테이너 생성 -> 컨트롤러 어노테이션이 붙은 객체 생성 -> 스프링 컨테이너에 보관
 * 이것을 스프링 컨테이너에서 스프링 빈이 관리된다고 표현
 */
@Controller
public class MemberController {

    /**
     * memberService 객체에 대해서 생각해보자. 이 객체는 여러 컨트롤러에서 사용할 가능성이 있지만 실제로 별다른 기능이 있지는 않다.
     * 즉 컨트롤러 객체마다 인스턴스를 생성해서 쓸 필요가 없다는 뜻이다. 하나의 인스턴스를 생성하고 컨트롤러들이 공용으로 사용하는것이 바람직하다.
     * 그러기 위해서는 스프링 컨테이너에 등록을 해야한다.
     */
    private final MemberService memberService;

    /**
     * 스프링컨테이너가 컨트롤러 객체를 생성해서 보관할때 컨트롤러 객체의 생성자에 Autowired 어노테이션이 있다면
     * 스프링이 컨테이너에 있는 서비스 객체를 사용해서 컨트롤러와 결합시킨다.
     * Autowired 기능은 스프링 컨테이너에 올라간 객체에서만 동작한다. 즉 객체를 새로 생성할때는 동작하지 않는다.
     */
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    /**
     * form 에서 post 로 넘어온 값을 얻을 수 있다.
     */
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
