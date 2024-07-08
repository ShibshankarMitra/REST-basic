package com.springboot.bookservice.Controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.springboot.bookservice.Entities.Book;
import com.springboot.bookservice.Services.CreateBookService;
import com.springboot.bookservice.Services.DeleteBookService;
import com.springboot.bookservice.Services.GetBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

//@Controller
//Used when using MVC
@RestController 
// use this when creating controller for rest APIs
//==@Controller+@ResponseBody
@RequestMapping("books")
public class BookController {

	//Example of @value annotation
	
	
	@Value("$server.port")
	private String port;
	
	
	@Autowired
	private GetBookService getBookService;

	@Autowired
	private CreateBookService createBookService;

	@Autowired
	private DeleteBookService deletebookService;
	
	@Value("${server.port}")
	private String portnumber;
	
	                                                 /* FOR GET OPERATIONS */
                                            	

//	@RequestMapping(value = "/getAllbooks", method = RequestMethod.GET)
//	@ResponseBody//this Annotates that the Handler method returns direct response instead of a view name
//	//when using @restController @responseBody annotation is not required
//	public List<Book> getAllBooks() {
//		
//		List<Book> allBoks = this.getBookService.getAllBoks();
//		 return allBoks;
//		
//	}
	//We will use the below Instead

	
	
	// Custom Handler which sends HTTP Status response along with the Entity(book)
	// we use ResponseEntity as Handler return type
	@GetMapping("/getAllbooks")
	public ResponseEntity<?> getAllBooksWithStatus() {

		
		List<Book> allBooks = this.getBookService.getAllBooks();
		CompletableFuture.runAsync(this::asyncTask);
		if (allBooks.size() <= 0) {
			System.out.println("No Books found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Currently there are no books");		
		}

		else {
			System.out.println("Books found in the databse");
			return ResponseEntity.of(Optional.of(allBooks));
		}

	}
	
	private void asyncTask() {
        // Simulate some asynchronous work
        try {
            Thread.sleep(5000); // Simulate a time-consuming task
            System.out.println("Async task completed.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
	
	//Get books After pagination
	@GetMapping("/getAllbooksByPage")
	public ResponseEntity<?> getAllBooksWithPage(@RequestParam(value="pageNumber" ,required = false) int pageNumber,@RequestParam("pageSize") int pageSize) {

		List<Book> allBooks = this.getBookService.getAllBooksByPage(pageNumber, pageSize);
		if (allBooks.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Currently there are no books, Or page number is exceeded records");
		}

		else {

			return ResponseEntity.of(Optional.of(allBooks));
		}

	}
	

	@GetMapping("/getbooksById/{id}")
	public ResponseEntity<?> getBookByID(@PathVariable("id") int id) {
		Optional<Book> optional = this.getBookService.getBookById(id);

		if (optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No User with Given ID found.");

		}

		else {

			return ResponseEntity.of(optional);
		}

	}
	
	
	//Get Book by title and Author Using HQL Join
	@GetMapping("/getBooksByTitleAndFirstname/{title}/{firstName}")
	public ResponseEntity<?> getAllBooksByTitleAndFirstName( @PathVariable("title") String title , @PathVariable("firstName") String firstName) {

		List<Book> bookByTitleAndFirstName = this.getBookService.getBookByTitleAndFirstName(title, firstName);
		if (bookByTitleAndFirstName.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Currently there are no books with the given title and first name");
		}

		else {

			return ResponseEntity.of(Optional.of(bookByTitleAndFirstName));
		}

	}
	
	
	
	
	                                             /* FOR DELETE OPERATIONS */
	
	
	@DeleteMapping("/delete/{id}")

	public ResponseEntity<?> deleteBookbyId(@PathVariable("id") int id) {

		
		try {
			this.deletebookService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	
	@DeleteMapping("/deleteByTitle/{title}")

	public ResponseEntity<String> deleteBookByTitle(@PathVariable("title") String title) {

		
		String deleteOneBookByTitle = this.deletebookService.deleteOneBookByTitle(title);
		return ResponseEntity.status(HttpStatus.OK).body(deleteOneBookByTitle);

	}
	
	
	

	
	
	                                     /* FOR POST OPERATOINS */
	

	@PostMapping("/createBooks")
	// When parsing as Request Parameters
	// public Book createBook(@RequestParam("id") int id,@RequestParam("title")
	// String title,@RequestParam("author") String author ) {
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) throws ConstraintViolationException {

//		Book book= new Book(id,title,author); (when taking as @requestparam setting the Book object with Constructor)
		Book createdNewBook = this.createBookService.createNewBook(book);// (Passing Book directly when taking
																			// @RequestBody)
		System.out.println("Book created");
//		return book.toString()+"Book created" ;
		return ResponseEntity.of(Optional.of(createdNewBook));

	}

	
	
	
	
	
	                                                             /* ADDITIONAL */
	
	
	//for Automatic Inserting of large number of values
//	@PostMapping("createAll")
//	public void createAllBooks(){
//		
//
//	this.createBookService.createAllBooks();
//		
//	}

}
