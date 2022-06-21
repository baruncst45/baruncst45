package com.accenture.lkm.ui;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.accenture.lkm.entity.CustomerEntity;

public class JPACustomer {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("unit1");
		EntityManager entityManager = factory.createEntityManager();

		try {

			getcustomerById(entityManager);
			addcustomer(entityManager);
			updatecustomer(entityManager);
			deletecustomerById(entityManager);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			factory.close();
		}

	}

	private static void deletecustomerById(EntityManager entityManager) {
		// TODO Auto-generated method stub
		CustomerEntity entity = null;
		int id = 2001;
		EntityManager em = null;
		entity = em.find(CustomerEntity.class, id);// select
		if (entity != null) {
			EntityTransaction transaction = em.getTransaction();
			// start the transaction
			try {
				transaction.begin();
				em.remove(entity);

				transaction.commit();
				System.out.println("Customer deleted: " + entity.getCustomerId());

			} catch (Exception e) {
				e.printStackTrace();
				transaction.rollback();
			}

		} else {
			System.out.println("Customer with id " + id + " is not found..");
		}

	}

	private static void updatecustomer(EntityManager entityManager) {
		// TODO Auto-generated method stub

		CustomerEntity entity = null;
		int id = 2001;
		EntityManager em = null;
		entity = em.find(CustomerEntity.class, id);// select
		if (entity == null) {
			System.out.println("Record not found");
			return;
		}

		EntityTransaction transaction = em.getTransaction();
		// start the transaction
		try {
			transaction.begin();
			// updating the entity in persistence context, will execute update query
			entity.setAddress("Mumbai");
			entity.setWalletBalance(70000);

			transaction.commit();
			System.out.println("customer updated: " + entity.getCustomerId());
		} catch (Exception e) {

			e.printStackTrace();
			transaction.rollback();
		}

	}

	private static void addcustomer(EntityManager entityManager) {
		// TODO Auto-generated method stub
		CustomerEntity entity = new CustomerEntity();
		entity.setCustomerId(2002);
		entity.setCustomerName("mahesh");
		entity.setAddress("Kolkata");
		entity.setInsertTime(new Date());
		entity.setWalletBalance(50000);

		EntityManager em = null;
		EntityTransaction transaction = em.getTransaction();
		// start the transaction
		try {
			transaction.begin();

			em.persist(entity);// insert --
			// entity added to persistence context
			// persistent /managed

			transaction.commit();
			System.out.println("customer added: " + entity.getCustomerId());
		} catch (Exception e) {

			e.printStackTrace();
			transaction.rollback();
		}
	}

	private static void getcustomerById(EntityManager entityManager) {
		// TODO Auto-generated method stub
		CustomerEntity entity = null;
		int id = 1002;

		EntityManager cm = null;
		entity = cm.find(CustomerEntity.class, id);// select

		if (entity != null) {
			System.out.println(entity);// entity.toString();
		} else {
			System.out.println("customer with id " + id + " is not found..");
		}

	}

}
