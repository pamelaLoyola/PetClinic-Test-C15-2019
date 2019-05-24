package com.tecsup.petclinic.service;

import java.util.List;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.PetNotFoundException;

public interface OwnerService {
	
	/**
	 * 
	 * @param pet
	 * @return
	 */
	Owner create(Owner owner);

	/**
	 * 
	 * @param pet
	 * @return
	 */
	Owner update(Owner owner);

	/**
	 * 
	 * @param id
	 * @throws PetNotFoundException
	 */
	void delete(Long id) throws OwnerNotFoundException;

	/**
	 * 
	 * @param id
	 * @return
	 */
	Owner findById(long id) throws OwnerNotFoundException;

	/**
	 * 
	 * @param name
	 * @return
	 */
	List<Owner> findOwnerByFirstname(String firstname);

	/**
	 * 
	 * @param typeId
	 * @return
	 */
	List<Owner> findOwnerByLastname(String lastname);

	/**
	 * 
	 * @param ownerId
	 * @return
	 */
	List<Owner> findOwnerByCity(String city);
	

	/**
	 * 
	 * @return
	 */
	Iterable<Owner> findAll();

}
