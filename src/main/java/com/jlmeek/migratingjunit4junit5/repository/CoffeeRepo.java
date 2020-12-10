package com.jlmeek.migratingjunit4junit5.repository;

import java.util.List;

import com.jlmeek.migratingjunit4junit5.model.Coffee;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepo extends PagingAndSortingRepository<Coffee, Long>{ 
    List<Coffee> findAllOrderByCoffeeRatingDesc();
}
