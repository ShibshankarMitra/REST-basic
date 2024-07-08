package com.springboot.bookservice.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "First Name Can not be blank")
	@Size(min = 5, max = 20, message = "First Name should be between 5 to 20 charecters")
	private String firstname;
	
	@NotBlank(message = "Last Name Can not be blank")
	@Size(min = 5, max = 20, message = "Last Name should be between 5 to 20 charecters")
	private String lastname;
	
	@OneToMany(mappedBy = "author")
	@JsonBackReference//Annotates that this is a back reference to Book, not a new Embedded Class
	private List<Book> books;
	//This will create an infinite loop if we create Bi-directional Mapping

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Author(int id,
			@NotBlank(message = "First Name Can not be blank") @Size(min = 5, max = 20, message = "First Name should be between 5 to 20 charecters") String firstname,
			@NotBlank(message = "Last Name Can not be blank") @Size(min = 5, max = 20, message = "Last Name should be between 5 to 20 charecters") String lastname,
			List<Book> books) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.books = books;
	}

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", books=" + books + "]";
	}
	
	
	
}
