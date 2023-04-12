package com.jsp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jsp.dto.Property;

public class PropertyDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Nikita");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public void create(Property property) {
		entityTransaction.begin();
		entityManager.persist(property);
		entityTransaction.commit();
	}

	public void update(Property property) {
		Property property1 = entityManager.find(Property.class, property.getProperty_id());
		if (property1 != null) {
			entityTransaction.begin();
			if (property.getProperty_name() != null) {
				property1.setProperty_name(property.getProperty_name());
			}
			if (property.getProperty_location() != null) {
				property1.setProperty_location(property.getProperty_location());
			}

			if (property.getNum_of_rooms() != null) {
				property1.setNum_of_rooms(property.getNum_of_rooms());
			}
			if (property.getRating() != null) {
				property1.setRating(property.getRating());
			}
			if (property.getLandLord() != null) {
				property1.setLandLord(property.getLandLord());
			}
			if (property.getRooms() != null) {
				property1.setRooms(property.getRooms());
			}
		}
		entityManager.merge(property1);
		entityTransaction.commit();

	}

	public void delete(int id) {
		Property property2 = entityManager.find(Property.class, id);
		entityTransaction.begin();
		entityManager.remove(property2);
		entityTransaction.commit();
	}

	public Property readPropertyById(int id) {
		return entityManager.find(Property.class, id);
	}

	public List<Property> readAllProperty() {
		String sql = "select s from Property s";
		Query query = entityManager.createQuery(sql);
		List<Property> properties = query.getResultList();
		return properties;
	}

	public Property readByName(Property property) {
		try {
			String sql = "select s from Property s where property_name like '" + property.getProperty_name() + "'";
			Query query = entityManager.createQuery(sql);
			Property property2 = (Property) query.getSingleResult();
			if (property2 != null) {
				return property2;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
