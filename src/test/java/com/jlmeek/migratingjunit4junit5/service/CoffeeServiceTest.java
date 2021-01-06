package com.jlmeek.migratingjunit4junit5.service;

import com.jlmeek.migratingjunit4junit5.model.Coffee;
import com.jlmeek.migratingjunit4junit5.repository.CoffeeRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CoffeeServiceTest {

    @Mock
    CoffeeRepo coffeeRepo; 

    @InjectMocks
    CoffeeService coffeeService; 

    static List<Coffee> mockedCoffees; 
    static List<Coffee> mockedCoffeesSortedAscending; 

    @BeforeAll
    public static void setup(){
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
    @DisplayName("Return All Coffees from findAll method")
    public void shouldReturnAllCoffees(){
      
        when(coffeeRepo.findAll()).thenReturn(mockedCoffees);

        List<Coffee> pulledCoffees = coffeeService.getAllCoffees();
        
        assertEquals(mockedCoffees.size(), pulledCoffees.size());
        
    }

    @Test
    @DisplayName("Return the highest rated coffee")
    public void shouldReturnACoffeeWithHighestRating(){
        when(coffeeRepo.findAllByOrderByCoffeeRatingDesc()).thenReturn(mockedCoffeesSortedAscending);
        
        Coffee pulledRatedCoffee = coffeeService.getHighestRatedCoffee();
        
        assertEquals("Cafe Ladero",pulledRatedCoffee.getCoffeeName()); 
    }

    
    @DisplayName("Returns a single coffee by a coffee's ID")
    @ParameterizedTest    
    @CsvFileSource(resources = "/test-data.csv")
    public void shouldReturnASingleCoffeeById(int index, String id, String expectedCoffeeName, String expectedCoffeeType, double expectedCoffeeRating){
        long longId = Long.parseLong(id);

        when(coffeeRepo.findById(longId)).thenReturn(Optional.of(mockedCoffees.get(index)));

        Coffee pulledSingleCoffee = coffeeService.getCoffeeById(longId);
        
        assertEquals(pulledSingleCoffee.getCoffeeName(), expectedCoffeeName); 
        assertEquals(pulledSingleCoffee.getCoffeeType(), expectedCoffeeType); 
        assertEquals(pulledSingleCoffee.getCoffeeRating(), expectedCoffeeRating, 0.0); 
    }

    @Test
    @DisplayName("Throws a NoSuchElementException when passed a coffee's ID that's not in the database")
    public void shouldThrowAnExceptionWhenPassedAnUnknownCoffeeId(){
        when(coffeeRepo.findById(4L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> coffeeService.getCoffeeById(4L));
    }


    
}
