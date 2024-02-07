package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title;
    private String content;

    //데이터를 잘 받았는지 확인하기 위한 toStirng메서드 생성

    public Article toEntitiy() {
        return new Article(null, title, content);
    }
}
