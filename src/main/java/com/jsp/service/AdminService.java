package com.jsp.service;

import java.util.ArrayList;
import java.util.List;

import com.jsp.dao.AdminDao;
import com.jsp.dao.LandlordDao;
import com.jsp.dto.Admin;
import com.jsp.dto.LandLord;

public class AdminService {
	AdminDao adminDao = new AdminDao();
	LandlordDao landlordDao = new LandlordDao();
	LandlordService landlordService = new LandlordService();

	public void create(Admin admin) {
		adminDao.create(admin);
	}

	public void update(Admin admin) {
		adminDao.update(admin);
	}

	public void delete(int id) {
		try {
			Admin admin = adminDao.readById(id);
			List<LandLord> landlords = admin.getLandLords();
			for (LandLord landLord : landlords) {
				landlordDao.delete(landLord.getId());
			}
			adminDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Admin readById(int id) {
		return adminDao.readById(id);
	}

	public List<Admin> readAllAdmin() {
		return adminDao.readAllAdmin();
	}

	public List<LandLord> readAllUnapprovedLandlords() {
		List<LandLord> landLords = landlordService.readAllLandlord();
		List<LandLord> landLords2 = new ArrayList<LandLord>();

		for (LandLord landLord : landLords) {
			if (landLord.getStatus().equalsIgnoreCase("Not Approved")) {
				landLords2.add(landLord);
			}
		}
		return landLords2;
	}

	public List<LandLord> readAllApprovedLandlords() {
		List<LandLord> landLords = landlordService.readAllLandlord();
		List<LandLord> landLords2 = new ArrayList<LandLord>();

		for (LandLord landLord : landLords) {
			if (landLord.getStatus().equalsIgnoreCase("Approved")) {
				landLords2.add(landLord);
			}
		}
		return landLords2;
	}

	public boolean approveLandlordById(int id, Admin admin) {
		LandLord landLord = landlordDao.readById(id);

		if (landLord.getStatus().equalsIgnoreCase("Approved")) {
			return false;
		} else {
			landLord.setAdmin(admin);
			landLord.setStatus("Approved");

			ArrayList<LandLord> landLords = new ArrayList<LandLord>();
			landLords.add(landLord);

			admin.setLandLords(landLords);

			adminDao.update(admin);
			landlordDao.update(landLord);
			return true;
		}

	}

	public boolean logIn(Admin admin) {
		if (adminDao.logIn(admin) == null) {
			return false;
		} else
			return true;
	}

	public boolean validate(Admin admin) {
	
		Admin admin2 = adminDao.validate(admin);
		if (admin2 != null && admin.getAdmin_email().equals(admin2.getAdmin_email())) {
			return false;
		} else {
			return true;
		}
	}

	public Admin getByEmailId(Admin admin) {
		if (admin.getAdmin_email() != null) {
			return adminDao.getByEmailId(admin);
		}
		return null;
	}
	public boolean validatName(Admin admin) {
		String name = admin.getAdmin_name();
		char[] c = name.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (!(c[i] >= 'a' && c[i] <= 'z' || c[i] >= 'A' && c[i] <= 'Z' || c[i] == ' ')) {
				return false;
			}
		}
		return true;
	}

}
