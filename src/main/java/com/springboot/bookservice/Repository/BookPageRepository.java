package com.springboot.bookservice.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.springboot.bookservice.Entities.Book;


//For Paging and Sorting
public interface BookPageRepository extends PagingAndSortingRepository<Book, Integer>{

}
