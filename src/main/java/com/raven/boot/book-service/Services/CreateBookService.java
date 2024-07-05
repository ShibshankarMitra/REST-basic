package com.REST_API_book.java.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.REST_API_book.java.Entities.Book;
import com.REST_API_book.java.Repository.BookRepository;

@Service
public class CreateBookService {

	
	@Autowired
	private BookRepository bookRepository;

	
	public Book createNewBook(Book book) {
		Book savedBook = this.bookRepository.save(book);
		return savedBook;
	}
	
//	public void createAllBooks(){
//		
//		List<Book> allBooks = new ArrayList<>();
//		
//		for (int i=1; i<=50; i++) {
//			
//			Author author= new Author();
//			author.setFirstname("Firstname"+i);
//			author.setLastname("Lastname"+i);
//			Book book= new Book();
//			book.setTitle("Title"+i);
//			book.setAuthor(author);
//			Book savedBook = this.bookRepository.save(book);
//			allBooks.add(savedBook);
//		}
//		System.out.println(allBooks.size());
//	}
}
