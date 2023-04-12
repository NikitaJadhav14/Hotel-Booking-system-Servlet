package com.jsp.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int property_id;
	private String property_name;
	private String property_location;
	private Integer rating;
	private Integer num_of_rooms;

	@OneToMany(mappedBy = "property")
	private List<Room> rooms;

	@ManyToOne
	@JoinColumn
	private LandLord landLord;

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public int getProperty_id() {
		return property_id;
	}

	public void setProperty_id(int property_id) {
		this.property_id = property_id;
	}

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public String getProperty_location() {
		return property_location;
	}

	public void setProperty_location(String property_location) {
		this.property_location = property_location;
	}

	public LandLord getLandLord() {
		return landLord;
	}

	public void setLandLord(LandLord landLord) {
		this.landLord = landLord;
	}

	public Integer getNum_of_rooms() {
		return num_of_rooms;
	}

	public void setNum_of_rooms(Integer num_of_rooms) {
		this.num_of_rooms = num_of_rooms;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	

}