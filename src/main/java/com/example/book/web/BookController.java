package com.example.book.web;

import com.example.book.domain.Book;
import com.example.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor //자동으로 final 생성자 생성
@RestController
public class BookController {

    private final BookService bookService;
    //<?> 어떤타입인지 정해지지 않았다? 알아서 들어간다
    //return <>안에 타입 생략가능
    @PostMapping("/book") //@RequestBody를 선언하면 json으로 받는다.
    public ResponseEntity<?> save(@RequestBody Book book) {
        
        //<>안에 타입 생략가능
        return new ResponseEntity<>(bookService.저장하기(book), HttpStatus.CREATED);
    }

    @GetMapping("/book")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(bookService.모두가져오기(), HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.한건가져오기(id), HttpStatus.OK);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.수정하기(id, book), HttpStatus.OK);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.삭제하기(id), HttpStatus.OK);
    }
}
