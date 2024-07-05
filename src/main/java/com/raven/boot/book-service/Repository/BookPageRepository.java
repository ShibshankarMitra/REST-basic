package com.REST_API_book.java.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.REST_API_book.java.Entities.Book;


//For Paging and Sorting
public interface BookPageRepository extends PagingAndSortingRepository<Book, Integer>{

}
