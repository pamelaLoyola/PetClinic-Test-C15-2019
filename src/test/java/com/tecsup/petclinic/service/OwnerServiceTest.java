package com.tecsup.petclinic.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OwnerServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);
	
	@Autowired
	OwnerService ownerService;
	
	
	@Test
	public void testFindOwnerByFirstname() {

		String FIND_NAME = "Peter";
		int SIZE_EXPECTED = 1;

		List<Owner> owners = ownerService.findOwnerByFirstname(FIND_NAME);

		assertEquals(SIZE_EXPECTED, owners.size());
		
		logger.info(">>>>>>>>>>>>>>> Owner found by Firstname <<<<<<<<<<<<<<<");
	}

	@Test
	public void testFindOwnerByLastname() {

		String FIND_LAST_NAME = "Black";
		int SIZE_EXPECTED = 1;

		List<Owner> owners = ownerService.findOwnerByLastname(FIND_LAST_NAME);

		assertEquals(SIZE_EXPECTED, owners.size());
		
		logger.info(">>>>>>>>>>>>>>> Owner found by Lastname <<<<<<<<<<<<<<<");
	}
	
	@Test
	public void testFindOwnerByCity() {

		String FIND_CITY = "Sun Prairie";
		int SIZE_EXPECTED = 1;

		List<Owner> owners = ownerService.findOwnerByCity(FIND_CITY);

		assertEquals(SIZE_EXPECTED, owners.size());
		
		logger.info(">>>>>>>>>>>>>>> Owner found by City <<<<<<<<<<<<<<<");
	}
	
	@Test
	public void testDeleteOwnerById() throws OwnerNotFoundException {
		
		Long ID_OWNER = 16L;
		
		ownerService.delete(ID_OWNER);
		
		try {
			ownerService.findById(ID_OWNER);
			
		} catch (Exception o) {
			
			logger.info(">>>>>>>>>>>>>>> Owner deleted <<<<<<<<<<<<<<<");
		}
		
	}
	
	@Test
	public void testValidate() {
		
		String NAME_OWNER = "Sirena";
		String LAST_NAME_OWNER = "Ortiz";
		String CITY_OWNER = "Lima";
		
		Owner newOwner = new Owner(NAME_OWNER, LAST_NAME_OWNER, CITY_OWNER);
		
		Owner createdOwner = ownerService.create(newOwner);
		
		try {
			
			Owner ownerReady = ownerService.findById(createdOwner.getId());
			
			logger.info(">>>>>>>>>>>>>>> Owner created <<<<<<<<<<<<<<<");
			
		}catch (OwnerNotFoundException o) {
			
			logger.info(">>>>>>>>>>>>>>> Owner no created <<<<<<<<<<<<<<<");
		}
		
		Iterable<Owner> owners = ownerService.findAll();
		
		while(owners.iterator().hasNext()) {
			
			try {
				
				Owner ownerExist = ownerService.findById(createdOwner.getId());
				
				logger.info(">>>>>>>>>>>>>>> This owner exists <<<<<<<<<<<<<<<");
				
				break;
	
			}catch (Exception e) {
				
				logger.info(">>>>>>>>>>>>>>> This owner doesn´t exist <<<<<<<<<<<<<<<");
			}
		}
	}

}
