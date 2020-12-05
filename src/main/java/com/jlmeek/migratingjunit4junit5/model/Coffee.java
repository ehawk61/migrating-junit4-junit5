package com.jlmeek.migratingjunit4junit5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Coffee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long coffeeId; 
    private String coffeeName; 
    private String coffeeType; 
    private double coffeeRating; 
    
    protected Coffee(){}

    public Coffee(String coffeeName, String coffeeType, double coffeeRating){
        this.coffeeName = coffeeName; 
        this.coffeeType = coffeeType; 
        this.coffeeRating = coffeeRating; 
    }

}
