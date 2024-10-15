package com.pgmanager.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity

public class Room {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String roomNumber; // E.g., "101", "102"
	    private String sharingType; // "Single", "Double", "Triple"

	    @ManyToOne
	    @JoinColumn(name = "floor_id", nullable = false)
	    @JsonBackReference
	    private Floor floor;

	    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonManagedReference
	    private List<Bed> beds;


	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Room(Long id, String roomNumber, String sharingType, Floor floor, List<Bed> beds) {
		super();
		this.id = id;
		this.roomNumber = roomNumber;
		this.sharingType = sharingType;
		this.floor = floor;
		this.beds = beds;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}


	public String getSharingType() {
		return sharingType;
	}


	public void setSharingType(String sharingType) {
		this.sharingType = sharingType;
	}


	public Floor getFloor() {
		return floor;
	}


	public void setFloor(Floor floor) {
		this.floor = floor;
	}


	public List<Bed> getBeds() {
		return beds;
	}


	public void setBeds(List<Bed> beds) {
		this.beds = beds;
	}



	
    
}

