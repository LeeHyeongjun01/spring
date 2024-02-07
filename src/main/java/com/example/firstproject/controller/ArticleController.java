package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create") //new mustache 에서 post 방식으로 form 데이터를 보냈기 때문에 GetMapping 이 아닌거.
    public String createArticle(ArticleForm form){

        //System.out.println(form.toString());
        // 1.DTO를 엔티티로 변환
        Article article = form.toEntitiy();
        log.info(article.toString());
        //System.out.println(article.toString());
        // 2.리파지터리로 엔티티를 DB에 저장.
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }
    @GetMapping("/articles/{id}")//데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id); //id 를 잘 받았는지 로그 확인.
        //1.id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //2. 모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);
        //3. 뷰 페이지 설정하기
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList =  articleRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        //3. 뷰 페이지 설정하기
        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);
        //뷰 페이지 설정하기.
        return "articles/edit";
    }
}
