package com.springboot.bookservice.Repository;

import com.springboot.bookservice.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRepo extends JpaRepository<Book, Integer>{

}
