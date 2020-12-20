package com.jlmeek.migratingjunit4junit5.service;

import com.jlmeek.migratingjunit4junit5.model.Coffee;
import com.jlmeek.migratingjunit4junit5.repository.CoffeeRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeServiceTest {

    @Mock
    CoffeeRepo coffeeRepo; 

    @InjectMocks
    CoffeeService coffeeService; 

    List<Coffee> mockedCoffees; 
    List<Coffee> mockedCoffeesSortedAscending; 

    @Before
    public void setup(){
        mockedCoffees = Arrays.asList(
            new Coffee("Charbucks Burnt Roast","Dark Roast",2.0), 
            new Coffee("Blumpkin' Dounts Dark Roast", "Dark Roast", 4.0), 
            new Coffee("Cafe Ladero","Medium Roast", 5.0));
        mockedCoffeesSortedAscending = Arrays.asList(
            new Coffee("Cafe Ladero","Medium Roast", 5.0), 
            new Coffee("Blumpkin' Dounts Dark Roast", "Dark Roast", 4.0),
            new Coffee("Charbucks Burnt Roast","Dark Roast",2.0) 
        );
    }    

    @Test
    public void shouldReturnAllCoffees(){
      
        when(coffeeRepo.findAll()).thenReturn(mockedCoffees);

        List<Coffee> pulledCoffees = coffeeService.getAllCoffees();
        assertEquals(pulledCoffees.size(),mockedCoffees.size()); 
    }

    @Test
    public void shouldReturnACoffeeWithHighestRating(){
        when(coffeeRepo.findAllByOrderByCoffeeRatingDesc()).thenReturn(mockedCoffeesSortedAscending);
        Coffee pulledRatedCoffee = coffeeService.getHighestRatedCoffee();
        assertEquals(pulledRatedCoffee.getCoffeeName(), "Cafe Ladero"); 
    }

    @Test
    public void shouldReturnASingleCoffeeById(){

        when(coffeeRepo.findById(1L)).thenReturn(Optional.of(mockedCoffees.get(0)));

        Coffee pulledSingleCoffee = coffeeService.getCoffeeById(1L);
        
        assertEquals(pulledSingleCoffee.getCoffeeName(), "Charbucks Burnt Roast"); 
        assertEquals(pulledSingleCoffee.getCoffeeType(), "Dark Roast"); 
        assertEquals(pulledSingleCoffee.getCoffeeRating(), 2.0, 0.0); 
    }

    @Test(expected = Exception.class)
    public void shouldThrowAnExceptionWhenPassedAnUnknownCoffeeId(){
        when(coffeeRepo.findById(4L)).thenReturn(Optional.empty());

        Coffee pulledUnkownCoffee = coffeeService.getCoffeeById(4L);
        
    }


    
}
