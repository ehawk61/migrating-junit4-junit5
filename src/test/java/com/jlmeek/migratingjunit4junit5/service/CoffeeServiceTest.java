package com.jlmeek.migratingjunit4junit5.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jlmeek.migratingjunit4junit5.model.Coffee;
import com.jlmeek.migratingjunit4junit5.repository.CoffeeRepo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeServiceTest {

    @Mock
    CoffeeRepo coffeeRepo; 

    @InjectMocks
    CoffeeService coffeeService; 


    @Test
    public void shouldReturnAllCoffees(){
        List<Coffee> mockedCoffees = new ArrayList<Coffee>();  
        when(coffeeRepo.findAll()).thenReturn(mockedCoffees); 
    }
    
    
}
`