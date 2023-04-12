package com.jsp.service;

import java.util.ArrayList;

import java.util.List;

import com.jsp.dao.LandlordDao;
import com.jsp.dao.PropertyDao;
import com.jsp.dto.Admin;
import com.jsp.dto.LandLord;
import com.jsp.dto.Property;

public class LandlordService {
	LandlordDao landlordDao = new LandlordDao();
	PropertyService propertyService = new PropertyService();

	public void create(LandLord landLord) {
		landLord.setStatus("NOT APPROVED");
		landlordDao.create(landLord);
	}

	public void update(LandLord landLord) {
		landlordDao.update(landLord);
	}

	public void delete(int id) {
		LandLord landLord = landlordDao.readById(id);
		for (Property property : landLord.getPropertys()) {
			propertyService.delete(property.getProperty_id());
		}
		landlordDao.delete(id);
	}

	public LandLord readById(int id) {
		return landlordDao.readById(id);

	}

	public List<LandLord> readAllLandlord() {
		return landlordDao.readAllLandlord();
	}

	public void addPropertyById(int id, LandLord landLord) {
		LandLord landLord1 = landlordDao.readById(landLord.getId());
		if (landLord1.getStatus().equalsIgnoreCase("APPROVED")) {
			Property property = propertyService.readById(id);
			property.setLandLord(landLord);

			ArrayList<Property> properties = new ArrayList<Property>();
			properties.add(property);

			landLord.setPropertys(properties);

			landlordDao.update(landLord);
			propertyService.update(property);
		} else {
			System.out.println("Landlord is not approved");
		}
	}

	public boolean logIn(LandLord landLord) {

		LandLord landLord2 = landlordDao.logIn(landLord);
		if (landlordDao.logIn(landLord) != null && landLord.getUsername().equals(landLord2.getUsername())
				&& landLord.getPassword().equals(landLord2.getPassword())) {
			return true;
		} else
			return false;
	}

	public boolean validate(LandLord landLord) {
		LandLord landLord2 = landlordDao.validate(landLord);
		if (landLord2 != null && landLord.getUsername().equals(landLord2.getUsername())) {
			return false;
		} else {
			return true;
		}
	}

	public LandLord getByUserName(LandLord landLord) {
		if (landLord.getUsername() != null) {
			return landlordDao.getByUserName(landLord);
		}
		return null;
	}

	public boolean validatName(LandLord landLord) {
		String name = landLord.getName();
		char[] c = name.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (!(c[i] >= 'a' && c[i] <= 'z' || c[i] >= 'A' && c[i] <= 'Z' || c[i] == ' ')) {
				return false;
			}
		}
		return true;
	}
}
