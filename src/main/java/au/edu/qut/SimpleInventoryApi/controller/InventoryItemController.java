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
import au.edu.qut.SimpleInventoryApi.model.InventoryItem;
import au.edu.qut.SimpleInventoryApi.model.Manufacturer;
import au.edu.qut.SimpleInventoryApi.repository.InventoryItemRepository;

import javax.validation.Valid;

@RestController
public class InventoryItemController {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @GetMapping("/inventoryOrig")
    public Page<InventoryItem> getInventoryItemsOrig(Pageable pageable) {
        return inventoryItemRepository.findAll(pageable);
    }
    
    @RequestMapping(value = "/inventory")
    public ResponseEntity<?> getInventoryItems(Pageable pageable) {
    	try {
    		Page<InventoryItem> response = inventoryItemRepository.findAll(pageable);     		
            return new ResponseEntity<>(response, HttpStatus.OK);
    	} catch (RuntimeException rex) {
        	return new ResponseEntity<>("bad input parameter", HttpStatus.BAD_REQUEST);        	
        }
    }
    
    @GetMapping("/inventory/{id}")
    public ResponseEntity<?> getInventoryItem(@PathVariable String id) {
        return inventoryItemRepository.findById(id)
                .map(inventoryItem -> {
                	return new ResponseEntity<>(inventoryItem.toJSON(), HttpStatus.OK);              	
                }).orElseThrow(() -> new NotFoundException("not found"));
    }

    @PostMapping("/inventory")
    public ResponseEntity<?> createInventoryItem(@Valid @RequestBody InventoryItem newInventoryItem) {
    	try {
    		inventoryItemRepository.findById(newInventoryItem.getId())
		    .map(inventoryItem -> {
		    	return new ResponseEntity<>("an existing item already exists", HttpStatus.CONFLICT);
		    	
		    });    	
    		InventoryItem inventoryItemResponse = inventoryItemRepository.save(newInventoryItem);
		    return new ResponseEntity<>("item created", HttpStatus.CREATED);
    	} catch (RuntimeException rex) {
		    return new ResponseEntity<>("invalid input, object invalid", HttpStatus.BAD_REQUEST); 
    	}        
    }
}