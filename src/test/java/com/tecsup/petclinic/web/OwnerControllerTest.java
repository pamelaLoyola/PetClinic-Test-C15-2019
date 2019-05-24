package com.tecsup.petclinic.web;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.domain.Pet;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class OwnerControllerTest {
	
	public static final ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testGetOwners() throws Exception {

		// int NRO_RECORD = 73;
		int ID_FIRST_RECORD = 1;

		this.mockMvc.perform(get("/owners"))
					.andExpect(status().isOk())
					.andExpect(content()
					.contentType(MediaType.APPLICATION_JSON_UTF8))
				// .andExpect(jsonPath("$", hasSize(NRO_RECORD)))
					.andExpect(jsonPath("$[0].id", is(ID_FIRST_RECORD)));

	}
	
	@Test
	public void testFindOwnerOK() throws Exception {

		String NAME_OWNER = "Maria";
		String LAST_NAME_OWNER = "Escobito";
		String ADDRESS_OWNER = "345 Maple St.";
		String CITY_OWNER = "Madison";
		String PHONE_OWNER = "6085557683";

		mockMvc.perform(get("/owners/8"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(8)))
				.andExpect(jsonPath("$.firstname", is(NAME_OWNER)))
				.andExpect(jsonPath("$.lastname", is(LAST_NAME_OWNER)))
				.andExpect(jsonPath("$.address", is(ADDRESS_OWNER)))
				.andExpect(jsonPath("$.city", is(CITY_OWNER)))
				.andExpect(jsonPath("$.telphone", is(PHONE_OWNER)));


	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindOwnerBad() throws Exception {

		mockMvc.perform(get("/owners/20"))
				.andExpect(status().isNotFound());

	}
	
	/**
	 * 
	 * @throws Exception
	 */
    @Test
    public void testCreateOwner() throws Exception {
		
    	String NAME_OWNER = "Jimena";
		String LAST_NAME_OWNER = "Loyola";
		String ADDRESS_OWNER = "Av. Los Portales";
		String CITY_OWNER = "Lima";
		String PHONE_OWNER = "9484124785";
		
		Owner newOwner = new Owner(NAME_OWNER,LAST_NAME_OWNER,ADDRESS_OWNER,CITY_OWNER,PHONE_OWNER);
	
	    mockMvc.perform(post("/owners")
	            .content(om.writeValueAsString(newOwner))
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            //.andExpect(jsonPath("$.id", is(1)))
	            .andExpect(jsonPath("$.firstname", is(NAME_OWNER)))
	            .andExpect(jsonPath("$.lastname", is(LAST_NAME_OWNER)))
	            .andExpect(jsonPath("$.address", is(ADDRESS_OWNER)))
	            .andExpect(jsonPath("$.city", is(CITY_OWNER)))
	            .andExpect(jsonPath("$.telphone", is(PHONE_OWNER)));
    
	}
    
    /**
     * 
     * @throws Exception
     */
    @Test
    public void testDeleteOwner() throws Exception {

    	String NAME_OWNER = "Santiago";
		String LAST_NAME_OWNER = "Hernandez";
		String ADDRESS_OWNER = "Urb. San Antonio";
		String CITY_OWNER = "Trujillo";
		String PHONE_OWNER = "965801773";
		
		Owner newOwner = new Owner(NAME_OWNER,LAST_NAME_OWNER,ADDRESS_OWNER,CITY_OWNER,PHONE_OWNER);
		
		ResultActions mvcActions = mockMvc.perform(post("/owners")
	            .content(om.writeValueAsString(newOwner))
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated());
	            
		String response = mvcActions.andReturn().getResponse().getContentAsString();

		Integer id = JsonPath.parse(response).read("$.id");

        mockMvc.perform(delete("/owners/" + id ))
                /*.andDo(print())*/
                .andExpect(status().isOk());
    }


}
