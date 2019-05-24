package com.tecsup.petclinic.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
	
	List<Owner> findByFirstname(String firstname);
	
	List<Owner> findByLastname(String lastname);
	
	List<Owner> findByCity(String city);

}
