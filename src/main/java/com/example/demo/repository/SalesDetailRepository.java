package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SalesDetail;

@Repository
public interface SalesDetailRepository extends PagingAndSortingRepository<SalesDetail, Integer> {

	

}
