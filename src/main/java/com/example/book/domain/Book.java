package com.example.book.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //서버 실행시 테이블 생성
public class Book {
    @Id //pk 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스 번호증가 전략 적용
    private Long id;

    private String title;
    private String author;
}
