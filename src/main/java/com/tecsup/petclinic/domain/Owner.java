package com.tecsup.petclinic.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name ="owners")
public class Owner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private long id;
	
	@Column(name ="first_name")
	private String firstname;
	
	@Column(name ="last_name")
	private String lastname;
	
	@Column(name ="address")
	private String address;
	
	@Column(name ="city")
	private String city;
	
	@Column(name ="telephone")
	private String telphone;

	public Owner() {
		super();
	}

	public Owner(long id, String firstname, String lastname, String address, String city, String telphone) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.city = city;
		this.telphone = telphone;
	}
	

	public Owner(String firstname, String lastname, String address, String city, String telphone) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.city = city;
		this.telphone = telphone;
	}
	
	

	public Owner(String firstname, String lastname, String city) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", address=" + address
				+ ", city=" + city + ", telphone=" + telphone + "]";
	}
	
	
}
