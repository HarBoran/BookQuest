package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Wishlist;

@Repository
public interface WishlistRepository extends PagingAndSortingRepository<Wishlist, Integer> {

}
