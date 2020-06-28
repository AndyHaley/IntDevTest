/*
 * ********************************************************************
 * Simple Inventory API
 * 
 * Copyright (C) 2019 QUT
 * Developed by: Andrew Haley
 * 
 * ********************************************************************
 */
package au.edu.qut.SimpleInventoryApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import au.edu.qut.SimpleInventoryApi.exception.BadRequestException;
import au.edu.qut.SimpleInventoryApi.exception.NotFoundException;
import au.edu.qut.SimpleInventoryApi.model.Manufacturer;
import au.edu.qut.SimpleInventoryApi.repository.ManufacturerRepository;

import java.util.Collection;

import javax.validation.Valid;

@RestController
public class ManufacturerController {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @GetMapping("/manufacturerOrig")
    public Page<Manufacturer> getManufacturersOrig(Pageable pageable) {
        return manufacturerRepository.findAll(pageable);
    }

    @RequestMapping(value = "/manufacturer")
    public ResponseEntity<?> getManufacturers(Pageable pageable) {
    	try {
    		Page<Manufacturer> response = manufacturerRepository.findAll(pageable);     		
            return new ResponseEntity<>(response, HttpStatus.OK);
    	} catch (RuntimeException rex) {
        	return new ResponseEntity<>("bad input parameter", HttpStatus.BAD_REQUEST);        	
        }
    }
    
    @GetMapping("/manufacturer/{name}")
    public ResponseEntity<?> getManufacturer(@PathVariable String name) {
        return manufacturerRepository.findById(name)
                .map(manufacturer -> {
                	return new ResponseEntity<>(manufacturer.toJSON(), HttpStatus.OK);
                }).orElseThrow(() -> new NotFoundException("not found"));
        
    }

    @PostMapping("/manufacturer")
    public ResponseEntity<?> createManufacturer(@Valid @RequestBody Manufacturer newManufacturer) {
    	try {
		    manufacturerRepository.findById(newManufacturer.getName())
		    .map(manufacturer -> {
		    	return new ResponseEntity<>("an existing item already exists", HttpStatus.CONFLICT);
		    	
		    });    	
			Manufacturer manufacturerResponse = manufacturerRepository.save(newManufacturer);
		    return new ResponseEntity<>("item created", HttpStatus.CREATED);
    	} catch (RuntimeException rex) {
		    return new ResponseEntity<>("invalid input, object invalid", HttpStatus.BAD_REQUEST); 
    	}
    }

}