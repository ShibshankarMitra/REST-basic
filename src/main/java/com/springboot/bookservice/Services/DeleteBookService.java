package com.springboot.bookservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bookservice.Repository.BookRepository;

@Service
public class DeleteBookService {

	@Autowired
	private BookRepository bookRepository;
	
	public void deleteById(int id) {
				
			this.bookRepository.deleteById(id);
		
	}
	
	public String deleteOneBookByTitle(String title) {
		
		int deleteOneBookByTitle = this.bookRepository.deleteOneBookByTitle(title);
		return "One book deleted with title : "+deleteOneBookByTitle;
	}

}
