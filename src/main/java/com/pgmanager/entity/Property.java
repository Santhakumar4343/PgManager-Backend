package com.pgmanager.entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity

public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String owneremail;
    private String name;
    private String pincode;
    private String ownerName;
    private String ownerPhoneNumber;
    private String propertyType;
//city area 
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Floor> floors;

	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Property(Long id, String owneremail, String name, String pincode, String ownerName, String ownerPhoneNumber,String propertyType,
			List<Floor> floors) {
		super();
		this.id = id;
		this.owneremail = owneremail;
		this.name = name;
		this.pincode = pincode;
		this.ownerName = ownerName;
		this.ownerPhoneNumber = ownerPhoneNumber;
		this.propertyType=propertyType;
		this.floors = floors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwneremail() {
		return owneremail;
	}

	public void setOwneremail(String owneremail) {
		this.owneremail = owneremail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getOwnerPhoneNumber() {
		return ownerPhoneNumber;
	}

	public void setOwnerPhoneNumber(String ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
	}

	public List<Floor> getFloors() {
		return floors;
	}

	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}

	@Override
	public String toString() {
		return "Property [id=" + id + ", owneremail=" + owneremail + ", name=" + name + ", pincode=" + pincode
				+ ", ownerName=" + ownerName + ", propertyType=" + propertyType + ",ownerPhoneNumber=" + ownerPhoneNumber + ", floors=" + floors + "]";
	}

	
    
}

