package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.User;

@Repository
public interface OrderDetailRepository extends PagingAndSortingRepository<OrderDetail, Integer> {

	@Query("SELECT o FROM OrderDetail o WHERE o.order =:order")
	public List<OrderDetail> findOrderDetailsByOrder(@Param("order") Order order);

	//@Query("SELECT o.book FROM OrderDetail o GROUP BY o.book ORDER BY COUNT(o.book) DESC")
	//@Query("SELECT od.book.bookId, COUNT(od.book.bookId) as cnt FROM OrderDetail od GROUP BY od.book.bookId ORDER BY cnt DESC")
	@Query("SELECT b FROM Book b JOIN OrderDetail o ON b.bookId = o.book.bookId GROUP BY b.bookId ORDER BY COUNT(o.book.bookId ) DESC")
	public List<Book> bestseller();
	
	@Query("SELECT b FROM Book b JOIN OrderDetail o ON b.bookId = o.book.bookId GROUP BY b.bookId ORDER BY COUNT(o.book.bookId ) DESC")
	public Page<Book> bestseller(Pageable pageable);
	
	//@Query("SELECT o.book FROM OrderDetail o  GROUP BY o.book ORDER BY COUNT(o.book) DESC ")
//	@Query("SELECT o.book.bookId FROM OrderDetail o GROUP BY o.book.bookId ORDER BY COUNT(o.book.bookId) DESC")
	@Query("SELECT b FROM Book b JOIN OrderDetail o ON b.bookId = o.book.bookId WHERE CONCAT(b.title, ' ', b.author, ' ', b.publisher) LIKE %?1% GROUP BY b.bookId ORDER BY COUNT(o.book.bookId ) DESC")
	public Page<Book> bestseller(String keyword, Pageable pageable);

	@Query("SELECT od FROM OrderDetail od JOIN FETCH od.book WHERE od.order.user = :user")
	List<OrderDetail> findOrderDetailsByUser(@Param("user") User user);

	@Query("SELECT COUNT(*) FROM OrderDetail") 
	public Long countTotalBooks();
	

}
