package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@ToString
@Entity //이를 부여받은 클래스를 기반으로 DB에 저장된다.
@Getter
@NoArgsConstructor // 생성자를 따로 설정할 필요 없음.

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// DB가 id 자동 생성
    private Long id;
    @Column //DTO필드를 작성할 때와 마찬가질 title 필드를 생성해주는데 @컬럼을 붙여 DB에서 인식할 수 있게 해준다.
    private String email;
    @Column // title과 content 두 필드가 DB테이블의 각 열과 연결된다.
    private String password;


}
