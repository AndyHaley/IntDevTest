/*
 * ********************************************************************
 * Simple Inventory API
 * 
 * Copyright (C) 2019 QUT
 * Developed by: Andrew Haley
 * 
 * ********************************************************************
 */
package au.edu.qut.SimpleInventoryApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * InventoryItem
 */
@Entity
@Table(name = "inventory_item")
@JsonIgnoreProperties(ignoreUnknown=true)

public class InventoryItem {

	
    @Id
    @NotNull
    @Column(name = "id")
    @SerializedName("id")    
    private String id = null;
    
    @NotNull
    @Column(name = "name")    
    @SerializedName("name")
    private String name = null;
    
    @NotNull
    @PastOrPresent
    @Column(name = "release_date")
    @SerializedName("releaseDate")
    private Timestamp releaseDate = null;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "manufacturer", referencedColumnName = "name")    
    @SerializedName("manufacturer")
    private Manufacturer manufacturer = null;

  public InventoryItem id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public InventoryItem name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

   /**
   * Get releaseDate
   * @return releaseDate
  **/
  public Timestamp getReleaseDate() {
    return releaseDate;
  }
  
  public void setReleaseDate(Timestamp releaseDate) {
    this.releaseDate = releaseDate;
  }

  public InventoryItem manufacturer(Manufacturer manufacturer) {
    this.manufacturer = manufacturer;
    return this;
  }

   /**
   * Get manufacturer
   * @return manufacturer
  **/
  public Manufacturer getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(Manufacturer manufacturer) {
    this.manufacturer = manufacturer;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InventoryItem inventoryItem = (InventoryItem) o;
    return Objects.equals(this.id, inventoryItem.id) &&
        Objects.equals(this.name, inventoryItem.name) &&
        Objects.equals(this.releaseDate, inventoryItem.releaseDate) &&
        Objects.equals(this.manufacturer, inventoryItem.manufacturer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, releaseDate, manufacturer);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InventoryItem {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    releaseDate: ").append(toIndentedString(releaseDate)).append("\n");
    sb.append("    manufacturer: ").append(toIndentedString(manufacturer)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public String toJSON() {
		return (new Gson()).toJson(this);
  }
  
}

