package com.REST_API_book.java.Services;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.REST_API_book.java.Entities.Author;
import com.REST_API_book.java.Entities.Book;
import com.REST_API_book.java.Repository.BookRepository;


@SpringBootTest
class GetBookServiceTest {
	
	
	@InjectMocks
	GetBookService getBookService;
	
	@Mock
	Author author;
	
	@Mock
	Book book;
	
	@Mock
	BookRepository bookRepository;
	


}
