package com.REST_API_book.java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.REST_API_book.java.Entities.Book;

@Repository
public interface JpaRepo extends JpaRepository<Book, Integer>{

}
