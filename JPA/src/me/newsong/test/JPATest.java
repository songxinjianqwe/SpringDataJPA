package me.newsong.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import me.newsong.mang2one.Customer;

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
	public void testMerge1() {
		Customer customer = new Customer(20,"aaa@163.com","aaa",new Date(),new Date());
		Customer customer2 = entityManager.merge(customer);
		System.out.println("customer1:"+customer.getId());
		System.out.println("customer2:"+customer2.getId());
	}

	@Test
	public void testMerge2() {
		Customer customer = entityManager.find(Customer.class, 3);
		customer.setId(200);
		Customer customer2 = entityManager.merge(customer);
		System.out.println("customer1:"+customer.getId());
		System.out.println("customer2:"+customer2.getId());
	}

	@Test
	public void testMerge3() {
		Customer customer = new Customer(20,"aaa@163.com","aaa",new Date(),new Date());
		customer.setId(200);
		Customer customer2 = entityManager.merge(customer);
		System.out.println("customer1:"+customer.getId());
		System.out.println("customer2:"+customer2.getId());
	}
	
	@After
	public void tearDown(){
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
	
}
