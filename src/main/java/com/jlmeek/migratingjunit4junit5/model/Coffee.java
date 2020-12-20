package com.jlmeek.migratingjunit4junit5.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
public class Coffee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long coffeeId; 
    
    private String coffeeName; 
    private String coffeeType; 
    private double coffeeRating; 

    public Coffee(String coffeeName, String coffeeType, double coffeeRating){
        this.coffeeName = coffeeName; 
        this.coffeeType = coffeeType; 
        this.coffeeRating = coffeeRating; 
    }

}
