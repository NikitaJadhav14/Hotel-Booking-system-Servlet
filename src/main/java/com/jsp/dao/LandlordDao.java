package com.jsp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;

import com.jsp.dto.Admin;
import com.jsp.dto.LandLord;

public class LandlordDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Nikita");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public void create(LandLord landLord) {
		try {
			entityTransaction.begin();
			entityManager.persist(landLord);
			entityTransaction.commit();
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(LandLord landLord) {
		LandLord landLord2 = entityManager.find(LandLord.class, landLord.getId());
		if (landLord2 != null) {
			entityTransaction.begin();
			if (landLord.getAddress() != null) {
				landLord2.setAddress(landLord.getAddress());
			}
			if (landLord.getName() != null) {
				landLord2.setName(landLord.getName());
			}
			if (landLord.getPropertys() != null) {
				landLord2.setPropertys(landLord.getPropertys());
			}
			if (landLord.getAdmin() != null) {
				landLord2.setAdmin(landLord.getAdmin());
			}
			if (landLord.getUsername() != null) {
				landLord2.setUsername(landLord.getUsername());
			}
			entityManager.merge(landLord2);
			entityTransaction.commit();
		} else {
			System.out.println("Id Not Found");
		}

	}

	public void delete(int id) {
		LandLord landLord2 = entityManager.find(LandLord.class, id);
		if (landLord2 != null) {
			entityTransaction.begin();
			entityManager.remove(landLord2);
			entityTransaction.commit();
		}
	}

	public LandLord readById(int id) {
		return entityManager.find(LandLord.class, id);

	}

	public List<LandLord> readAllLandlord() {
		String sql = "select s from LandLord s";
		Query query = entityManager.createQuery(sql);
		@SuppressWarnings("unchecked")
		List<LandLord> landLords = query.getResultList();
		return landLords;
	}

	public LandLord logIn(LandLord landLord) {
		try {
			LandLord landlord2;

			String sql = "select s from LandLord s where username like '" + landLord.getUsername()
					+ "' and password like '" + landLord.getPassword() + "'";
			Query query = entityManager.createQuery(sql);
			landlord2 = (LandLord) query.getSingleResult();
			return landlord2;

		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public LandLord validate(LandLord landLord) {
		try {
			String sql = "select s from LandLord s where username like '" + landLord.getUsername() + "'";
			Query query = entityManager.createQuery(sql);
			return (LandLord) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public LandLord getByUserName(LandLord landLord) {
		try {
			String sql = "select s from LandLord s where username like '" + landLord.getUsername() + "'";
			Query query = entityManager.createQuery(sql);

			LandLord landLord2 = (LandLord) query.getSingleResult();
			if (landLord2 != null) {
				return landLord2;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}