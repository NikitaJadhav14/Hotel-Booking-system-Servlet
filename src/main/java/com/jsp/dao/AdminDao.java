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

public class AdminDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Nikita");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public void create(Admin admin) {
		try {
			entityTransaction.begin();
			entityManager.persist(admin);
			entityTransaction.commit();
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(Admin admin) {
		Admin admin1 = entityManager.find(Admin.class, admin.getAdmin_id());
		if (admin1 != null) {
			entityTransaction.begin();
			if (admin.getAdmin_name() != null) {
				admin1.setAdmin_name(admin.getAdmin_name());
			}
			if (admin.getAdmin_email() != null) {
				admin1.setAdmin_email(admin.getAdmin_email());
			}
			if (admin.getLandLords() != null) {
				admin1.setLandLords(admin.getLandLords());
			}
			entityManager.merge(admin1);
			entityTransaction.commit();
		}
	}

	public void delete(int id) {
		Admin admin1 = entityManager.find(Admin.class, id);
		if (admin1 != null) {
			entityTransaction.begin();
			entityManager.remove(admin1);
			entityTransaction.commit();
		}
	}

	public Admin readById(int id) {
		return entityManager.find(Admin.class, id);
	}

	public List<Admin> readAllAdmin() {
		String sql = "select s from Admin s";
		Query query = entityManager.createQuery(sql);
		List<Admin> admins = query.getResultList();
		return admins;
	}

	public Admin logIn(Admin admin) {
		try {
			Admin admin2;

			String sql = "select s from Admin s where admin_email like '" + admin.getAdmin_email()
					+ "' and password like '" + admin.getPassword() + "'";
			Query query = entityManager.createQuery(sql);
			admin2 = (Admin) query.getSingleResult();
			return admin2;

		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Admin validate(Admin admin) {
		try {
			String sql = "select s from Admin s where admin_email like '" + admin.getAdmin_email() + "'";
			Query query = entityManager.createQuery(sql);
			return (Admin) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Admin getByEmailId(Admin admin) {
		try {
			String sql = "select s from Admin s where admin_email like '" + admin.getAdmin_email() + "'";
			Query query = entityManager.createQuery(sql);
			Admin admin2 = (Admin) query.getSingleResult();
			if (admin2 != null) {
				return admin2;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
