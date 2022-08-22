package com.example.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository extends해서 @Repository 생략
//JpaRepository는 CRUD 함수를 포함하고있다.
public interface BookRepository extends JpaRepository<Book, Long> {
}
