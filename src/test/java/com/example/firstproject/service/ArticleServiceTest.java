package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleServiceTest {
@Autowired
ArticleService articleService;
    @Test
    void index() {
        //1. 예상 데이터
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c)); //abc 합치기
        //2. 실제 데이터
        List<Article> articles = articleService.index();
        //3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
        
    }
    @Test
    void show_실패_존재하지_않는_id_입력(){
        //1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        //2. 실제 데이터
        Article article = articleService.show(id);
        //3. 비교 및 검증
        assertEquals(expected, article);
    }
@Transactional
    @Test
    void creat_성공_title과_content만_있는_dto_입력() {
        //1. 예상 데이터 입력
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title,content);
        Article expected = new Article(4L, title, content);
        //2. 실제 데이터
        Article article = articleService.creat(dto);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }
    @Transactional
    @Test
    void creat_실패_id가_포함된_dto_입력() {
        //1. 예상 데이터
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title,content);
        Article expected = null;
        //2. 실제 데이터
        Article article = articleService.creat(dto);
        //3. 비교 및 검증
        assertEquals(expected, article);
    }
    @Transactional
    @Test
    void update_성공_존재하는_id와_title_content가_있는_dto_입력(){
        //1.예상 데이터
        Long id = 1L;
        String title = "니뇨니뇨";
        String content = "니리루라루리리";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);
        //2. 실제 데이터
        Article article = articleService.update(id,dto);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());

    }
    @Transactional
    @Test
    void update_성공_존재하는_id와_title만_있는_dto_입력(){
        Long id = 1L;
        String title = "아이고 사장님";
        String content = null;
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(1L, "아이고 사장님", null);

        Article article = articleService.update(id,dto);
        assertEquals(article.toString(),expected.toString());

    }
    @Transactional
    @Test
    void update_실패_존재하지_않는_id의_dto_입력(){
        Long id = -1L;
        String title = "가나다라";
        String content = "마바사용";
        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = null;

        Article article = articleService.update(id, dto);
        assertEquals(article, expected);

    }
    @Transactional
    @Test
    void delete_성공_존재하는_id_입력(){
        Long id = 1L;
        String title = "가가가가";
        String content = "1111";

        Article expected = new Article(id,title,content);

        Article article = articleService.delete(id);
        assertEquals(expected.toString(), article.toString());
    }
    @Transactional
    @Test
    void delete_실패_존재하지_않는_id_입력(){
        Long id = 4L;
        //1.예상데이터 담기
        Article expected = null;
        //2.실제 데이터
        Article article = articleService.delete(id);
        //3.비교 및 검증
        assertEquals(expected, article);
    }
}