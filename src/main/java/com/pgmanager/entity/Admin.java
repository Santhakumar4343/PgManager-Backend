package com.pgmanager.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String username;
	private String email;
	private String ownerEmail;
	private String password;
	private String mobileNumber;
	private String panNumber;
	private String aadharNumber;
	private String presentAddress;
	private String permanetAddress;
	@Lob
	private byte[] aadharcard;
	
	@JsonBackReference
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Property> properties; 
}
