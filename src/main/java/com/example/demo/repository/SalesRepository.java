package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Sales;

@Repository
public interface SalesRepository extends PagingAndSortingRepository<Sales, Integer> {


}