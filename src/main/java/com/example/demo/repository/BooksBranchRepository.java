package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BooksBranch;

@Repository
public interface BooksBranchRepository extends PagingAndSortingRepository<BooksBranch, Integer> {

}
