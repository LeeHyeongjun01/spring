package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/signup")
    //회원가입 페이지에 들어간다.
    public String SignUpPage() {
        return "articles/newsignup";
    }
    @PostMapping("/join")
    //회원가입 페이지에 아이디랑 비번 입력시 /join으로 들어가지만 페이지 에러가 뜸.
    public String createMember(MemberForm form){
        Member member = form.toEntitiy();
        log.info(member.toString());
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/member/" + saved.getId2();
    }
    @GetMapping("/member/{id2}")//데이터 조회 요청 접수
    public String show(@PathVariable Long id2, Model model){
        log.info("id = " + id2); //id 를 잘 받았는지 로그 확인.
        //1.id를 조회해 데이터 가져오기
        Member memberEntity = memberRepository.findById(id2).orElse(null);
        //2. 모델에 데이터 등록하기
        model.addAttribute("member",memberEntity);
        //3. 뷰 페이지 설정하기
        return "member/show";
    }
    @GetMapping("/member")
    public String index(Model model){
        //1. 모든 데이터 가져오기
        ArrayList<Member> memberEntityList =  memberRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("memberList", memberEntityList);
        log.info(model.toString());
        //3. 뷰 페이지 설정하기
        return "member/index";
    }

}
