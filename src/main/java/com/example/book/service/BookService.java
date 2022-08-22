package com.example.book.service;

import com.example.book.domain.Book;
import com.example.book.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Supplier;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//기능 정의, 트랜잭션 관리
@RequiredArgsConstructor //자동으로 final 생성자 생성
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Transactional //서비스 함수가 종료될 때 commit할지 rollback할지 트랙잭션 관리
    public Book 저장하기(Book book) {
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true) //변경감지 비활성화, update시 정합성 유지, insert의 유령데이터현상(팬텀현상) 못막음
    public Book 한건가져오기(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalIdentifierException("id를 확인해주세요!!"));
    }

    @Transactional(readOnly = true)
    public List<Book> 모두가져오기() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book 수정하기(Long id, Book book) {
        //Dirty Checking update
        Book bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalIdentifierException("id를 확인해주세요!!")); //영속화 (book Object) -> 영속성 컨텍스트에 보관
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        return bookEntity;
    } //함수 종료 > 트랜쟉션 종료 > 영속화 되어있는 데이터를 DB로 갱신(flush > commit ----> Dirty Checking)

    @Transactional
    public String 삭제하기(Long id) {
        bookRepository.deleteById(id); //오류가 발생하면 Exception
        return "ok";
    }
}
