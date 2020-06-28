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
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import java.io.IOException;

/**
 * Manufacturer
 */
@Entity
@Table(name = "manufacturer")
@JsonIgnoreProperties(ignoreUnknown=true)

public class Manufacturer {
	
	@Id
	@NotNull
	@Column(name = "name")    
    @SerializedName("name")	
    private String name = null;
	
	@NotNull
    @Column(name = "home_page")    
    @SerializedName("homePage")
    private String homePage = null;
    
    @NotNull    
    @Column(name = "phone")   
    @SerializedName("phone")
    private String phone = null;

  public Manufacturer name(String name) {
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

  public Manufacturer homePage(String homePage) {
    this.homePage = homePage;
    return this;
  }

   /**
   * Get homePage
   * @return homePage
  **/
  public String getHomePage() {
    return homePage;
  }

  public void setHomePage(String homePage) {
    this.homePage = homePage;
  }

  public Manufacturer phone(String phone) {
    this.phone = phone;
    return this;
  }

   /**
   * Get phone
   * @return phone
  **/
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Manufacturer manufacturer = (Manufacturer) o;
    return Objects.equals(this.name, manufacturer.name) &&
        Objects.equals(this.homePage, manufacturer.homePage) &&
        Objects.equals(this.phone, manufacturer.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, homePage, phone);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Manufacturer {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    homePage: ").append(toIndentedString(homePage)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
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

