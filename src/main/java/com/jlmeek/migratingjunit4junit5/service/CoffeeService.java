package com.jlmeek.migratingjunit4junit5.service;

import com.jlmeek.migratingjunit4junit5.model.Coffee;
import com.jlmeek.migratingjunit4junit5.repository.CoffeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {

    CoffeeRepo coffeeRepo;

    CoffeeService(CoffeeRepo coffeeRepo){
        this.coffeeRepo = coffeeRepo;
    }

    public List<Coffee> getAllCoffees(){
        return (List<Coffee>) coffeeRepo.findAll();
    }

    public Coffee getHighestRatedCoffee(){
        return coffeeRepo.findAllByOrderByCoffeeRatingDesc().get(0);
    }

    public Coffee getCoffeeById(Long id){
        return coffeeRepo.findById(id).orElseThrow();
    }

}
