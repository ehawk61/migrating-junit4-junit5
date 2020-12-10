package com.jlmeek.migratingjunit4junit5.service;

import java.util.List;

import com.jlmeek.migratingjunit4junit5.model.Coffee;
import com.jlmeek.migratingjunit4junit5.repository.CoffeeRepo;

import org.springframework.beans.factory.annotation.Autowired;

public class CoffeeService {
    
    @Autowired 
    CoffeeRepo coffeeRepo; 

    public List<Coffee> getAllCoffees(){
        return (List<Coffee>) coffeeRepo.findAll();
    }

    public Coffee getHighestRatedCoffee(){
        return coffeeRepo.findByCoffeesOrderByCoffeeRatingDesc().get(0);
    }

    public Coffee getCoffeeById(Long id){
        return coffeeRepo.findById(id).orElseThrow();
    }

}
