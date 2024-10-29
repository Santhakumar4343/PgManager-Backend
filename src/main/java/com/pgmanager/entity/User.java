package com.pgmanager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class User {

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
	
	@OneToOne(mappedBy = "assignedUser")
    @JsonBackReference
    private Bed bed;
}
