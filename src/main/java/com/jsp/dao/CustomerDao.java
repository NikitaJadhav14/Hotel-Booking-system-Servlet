package com.jsp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;

import com.jsp.dto.Customer;
import com.jsp.dto.LandLord;

public class CustomerDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Nikita");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public void create(Customer customer) {
		try {
			entityTransaction.begin();
			entityManager.persist(customer);
			entityTransaction.commit();

		} catch (ConstraintViolationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(Customer customer) {
		Customer customer1 = entityManager.find(Customer.class, customer.getCustomer_id());
		if (customer1 != null) {
			entityTransaction.begin();
			if (customer.getCustomer_name() != null) {
				customer1.setCustomer_name(customer.getCustomer_name());
			}
			if (customer.getAadhar_no() != null) {
				customer1.setAadhar_no(customer.getAadhar_no());
			}
			if (customer.getCustomer_phone_no() != null) {
				customer1.setCustomer_phone_no(customer.getCustomer_phone_no());
			}
			if (customer.getRooms() != null) {
				customer1.setRooms(customer.getRooms());
			}
			entityManager.merge(customer1);
			entityTransaction.commit();
		}
	}

	public void delete(int id) {
		Customer customer2 = entityManager.find(Customer.class, id);
		if (customer2 != null) {
			entityTransaction.begin();
			entityManager.remove(customer2);
			entityTransaction.commit();
		}
	}

	public Customer readById(int id) {
		return entityManager.find(Customer.class, id);

	}

	public List<Customer> readAllCustomers() {
		String sql = "Select s From Customer s";
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}

	public Customer logIn(Customer customer) {
		try {
			Customer customer2;

			String sql = "select s from Customer s where username like '" + customer.getUsername()
					+ "' and password like '" + customer.getPassword() + "'";
			Query query = entityManager.createQuery(sql);
			customer2 = (Customer) query.getSingleResult();
			return customer2;

		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public Customer validate(Customer customer) {
		try {
			String sql = "select s from Customer s where username like '" + customer.getUsername() + "'";
			Query query = entityManager.createQuery(sql);
			return (Customer) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Customer getByUserName(Customer customer) {
		try {
			String sql = "select s from Customer s where username like '" + customer.getUsername() + "'";
			Query query = entityManager.createQuery(sql);
			return (Customer) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}