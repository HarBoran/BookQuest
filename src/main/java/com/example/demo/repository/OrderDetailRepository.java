package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends PagingAndSortingRepository<OrderDetail, Integer> {

	@Query("SELECT o FROM OrderDetail o WHERE o.order =:order")
	public List<OrderDetail> findOrderDetailsByOrder(@Param("order") Order order);

	@Query("SELECT o.book FROM OrderDetail o GROUP BY o.book ORDER BY COUNT(o.book) DESC")
	public List<Object> bestseller();

}
