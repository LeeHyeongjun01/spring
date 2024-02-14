package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class CoffeeForm {
    private Long id;
    private String name;
    private String price;

    //데이터를 잘 받았는지 확인하기 위한 toStirng메서드 생성

    public Coffee toEntity() {
        return new Coffee(id, name, price);
    }
}
