package me.newsong.twolevelcache;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JPATest {
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;
	EntityTransaction entityTransaction;

	@Before
	public void setUp() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("JPA");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
	}

	@Test
	public void test() {
		Customer customer = new Customer(12, "aaa@163.com", "aaa", new Date(), new Date());
		entityManager.persist(customer);
	}

	@Test
	public void test2LevelCache() {
		Customer customer = entityManager.find(Customer.class, 1);
		System.out.println(customer);
		entityTransaction.commit();
		entityManager.close();
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Customer customer2 = entityManager.find(Customer.class, 1);
		System.out.println(customer2);
	}

	@After
	public void tearDown() {
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}
