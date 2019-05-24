package com.tecsup.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.domain.Pet;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.PetNotFoundException;
import com.tecsup.petclinic.service.OwnerService;
import com.tecsup.petclinic.service.PetService;

@RestController
public class OwnerController {
	
	@Autowired
	OwnerService ownerService;
	

	
	@GetMapping("/owners")
	public Iterable<Owner> getOwners(){
		
		return ownerService.findAll();
	}
	

	@PostMapping("/owners")
	@ResponseStatus(HttpStatus.CREATED)
	Owner create(@RequestBody Owner newOwner) {
		return ownerService.create(newOwner);
	}

	
	@GetMapping("/owners/{id}")
	ResponseEntity<Owner> findOne(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(ownerService.findById(id), HttpStatus.OK);
		} catch (OwnerNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	

	@PutMapping("/owners/{id}")
	Owner saveOrUpdate(@RequestBody Owner newOwner, @PathVariable Long id) {
		Owner owner = null;
		try {
			owner = ownerService.findById(id);
			owner.setFirstname(newOwner.getFirstname());
			owner.setLastname(newOwner.getLastname());
			owner.setAddress(newOwner.getAddress());
			owner.setCity(newOwner.getCity());
			owner.setTelphone(newOwner.getTelphone());
			ownerService.update(owner);
		} catch (OwnerNotFoundException e) {
			owner = ownerService.create(newOwner);
		}
		return owner;
	}
	

	@DeleteMapping("/owners/{id}")
	ResponseEntity<String> delete(@PathVariable Long id) {

		try {
			ownerService.delete(id);
			return new ResponseEntity<>("" + id, HttpStatus.OK);
		} catch (OwnerNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
