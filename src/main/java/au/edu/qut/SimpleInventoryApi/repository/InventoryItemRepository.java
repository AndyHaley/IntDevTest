/*
 * ********************************************************************
 * Simple Inventory API
 * 
 * Copyright (C) 2019 QUT
 * Developed by: Andrew Haley
 * 
 * ********************************************************************
 */
package au.edu.qut.SimpleInventoryApi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.edu.qut.SimpleInventoryApi.model.InventoryItem;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, String> {
}