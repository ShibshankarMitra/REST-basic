package com.springboot.bookservice.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.springboot.bookservice.Entities.Book;
import jakarta.transaction.Transactional;

//Since it extends CrudRepository no need to annotate as @Repository
@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

	//@Modifying Should be used for Transactional Queries
	@Query("FROM Book b inner join b.author as a where b.title =:title and a.firstname =:firstname")
	public List<Book> findByTitleAndFirstName(@Param("title") String title, @Param("firstname") String firstname);
	
	@Transactional//Since it is delete operation
	@Modifying//Since this is Transactional we annotate it as @modifying
	@Query("DELETE FROM Book b where b.title =:title")
	public int deleteOneBookByTitle(@Param("title") String title);
	
	
}
