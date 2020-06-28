package au.edu.qut.SimpleInventoryApi;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import au.edu.qut.SimpleInventoryApi.model.InventoryItem;
import au.edu.qut.SimpleInventoryApi.model.Manufacturer;

public class InventoryItemControllerTest extends AbstractMockMvcTest {
	
	   @Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	   @Test
	   public void getInventoryItemList() throws Exception {
	      String uri = "/inventory";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      System.out.println ("=============================================================");	      
	      System.out.println ("InventoryItemList = "+content);
	      System.out.println ("=============================================================");	  
	      assertTrue(!content.isEmpty());
	      //InventoryItem[] inventoryItemList = super.mapFromJson(content, InventoryItem[].class);
	      //assertTrue(inventoryItemList.length > 0);
	   }	
	   @Test
	   public void getInventoryItem() throws Exception {

		  String id = "d290f1ee-6c54-4b01-90e6-d701748f0851"; 
	      String uri = "/inventory/" + id;
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertTrue(!content.isEmpty());;
	      //InventoryItem inventoryItem = super.mapFromJson(content, InventoryItem.class);
	      //assertTrue(inventoryItem != null);
	   }
	   
	   private Manufacturer getManufacturer () throws Exception {
		   
		  String name = "ACME Corporation"; 
	      String uri = "/manufacturer/" + name;
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      String content = mvcResult.getResponse().getContentAsString();
	      return (Manufacturer) super.mapFromJson(content, Manufacturer.class);
	   }	   
	   
	   @Test
	   public void createInventoryItem() throws Exception {
	      String uri = "/inventory";
	      
	      Manufacturer manufacturer = getManufacturer();
	      
	      InventoryItem inventoryItem = new InventoryItem();
	      inventoryItem.setId("d290f1ee-6c54-4b01-90e6-9999999");
	      inventoryItem.setName("Test Adapter");
//	      OffsetDateTime releaseDate = OffsetDateTime.now();
	      Date testDate = new Date();
	      Timestamp releaseDate = new Timestamp(testDate.getTime());	      
	      inventoryItem.setReleaseDate(releaseDate);
	      inventoryItem.setManufacturer(manufacturer);
	      
	      String inputJson = super.mapToJson(inventoryItem);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(201, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "item created");
	   }
}
