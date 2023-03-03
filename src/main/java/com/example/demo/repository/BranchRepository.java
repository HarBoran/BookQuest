package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Branchs;

@Repository
public interface BranchRepository extends PagingAndSortingRepository<Branchs, Integer> {

}
