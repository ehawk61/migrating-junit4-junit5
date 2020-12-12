package com.jlmeek.migratingjunit4junit5.controller;

import com.jlmeek.migratingjunit4junit5.model.Coffee;
import com.jlmeek.migratingjunit4junit5.service.CoffeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoffeeController {

    protected CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService){
        this.coffeeService = coffeeService;
    }

    @GetMapping("/")
    public List<Coffee> greeting(){
        return coffeeService.getAllCoffees();
    }

    @GetMapping("/{id}")
    public String retriveGreeting(@PathVariable long id){
        return String.format("Hello JUnit #%s", id);
    }
}
