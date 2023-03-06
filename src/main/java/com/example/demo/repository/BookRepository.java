package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

	@Query("SELECT b FROM Book b WHERE b.category = :category or b.category.parent =:category")
	public List<Book> findByCategoryId(@Param("category") Category category);

	@Query("SELECT b FROM Book b WHERE CONCAT(b.title,' ',b.author, ' ', b.publisher) LIKE %?1%")
	public List<Book> findAll(String keyword);
	
	@Query("SELECT b FROM Book b WHERE CONCAT(b.title, ' ', b.author, ' ', b.publisher) LIKE %?1%")
	public Page<Book> findAll(String keyword, Pageable pageable);

	@Query("SELECT b FROM Book b ORDER BY b.publicationDate ASC")
	public List<Book> finByNewBook(Category category);

	@Query("SELECT b FROM Book b ORDER BY RAND()") //LIMIT 5는 안됨
	public List<Book> findRandomBooks();

	@Query("SELECT COUNT(*) FROM Book") 
	public Long countTotlaBooks();

}