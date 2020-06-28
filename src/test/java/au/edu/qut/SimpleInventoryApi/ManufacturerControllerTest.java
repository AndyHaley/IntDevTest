package au.edu.qut.SimpleInventoryApi;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.OffsetDateTime;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import au.edu.qut.SimpleInventoryApi.model.Manufacturer;

public class ManufacturerControllerTest extends AbstractMockMvcTest {
	
	   @Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	   @Test
	   public void getManufacturerList() throws Exception {
	      String uri = "/manufacturer";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      System.out.println ("=============================================================");	      
	      System.out.println ("ManufacturerList = "+content);
	      System.out.println ("=============================================================");	  
	      assertTrue(!content.isEmpty());
	   }
	   @Test
	   public void getManufacturer () throws Exception {
		   
		  String name = "ACME Corporation"; 
	      String uri = "/manufacturer/" + name;
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      Manufacturer manufacturer = super.mapFromJson(content, Manufacturer.class);
	      assertTrue(manufacturer != null);
	   }	   
	   @Test
	   public void createManufacturer() throws Exception {
	      String uri = "/manufacturer";
	      
	      Manufacturer manufacturer = new Manufacturer();
	      manufacturer.setName("TEST Corporation");
	      manufacturer.setHomePage("localhost");
	      manufacturer.setPhone("999999999");
	      
	      String inputJson = super.mapToJson(manufacturer);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(201, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "item created");
	   }
}
