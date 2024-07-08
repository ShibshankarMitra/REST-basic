package com.springboot.bookservice.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.bookservice.Entities.Book;
import com.springboot.bookservice.Repository.BookPageRepository;
import com.springboot.bookservice.Repository.BookRepository;

@Service
public class GetBookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	BookPageRepository bookPageRepository;

	public List<Book> getAllBooks() {
		List<Book> allBooks = (List<Book>) this.bookRepository.findAll();
		return allBooks;
	}

	public Optional<Book> getBookById(int id) {
		return this.bookRepository.findById(id);

	}

	// To achieve pagination We will Call the findAll() again but with reurn type = page
	//and create a Pageable from pagerequest qwith page number and pagsize 
	public List<Book> getAllBooksByPage(int pageNumber, int pageSize) {

		// create a pageable with passing pageNumber and pageSize in the Pagerequest
		Pageable page = PageRequest.of(pageNumber, pageSize);

		List<Book> pageOfBooks = this.bookPageRepository.findAll(page).getContent();
		return pageOfBooks;
	}
	
	
	
	//Get Book by title and Author Using HQL Join
	//Calling the custom Repo method using @query Annotation to get the Data from DB
	public List<Book> getBookByTitleAndFirstName(String title, String firstname) {
		List<Book> booksByTitleAndFirstName = this.bookRepository.findByTitleAndFirstName(title, firstname);
		return booksByTitleAndFirstName;
	}
	
	
}
