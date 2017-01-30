package me.newsong.mang2one;

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
	public void testAdd(){
		Customer customer = new Customer(21, "sss@163.com", "sss", new Date(), new Date());
		Order order = new Order("asd",customer);
		entityManager.persist(customer);
		entityManager.persist(order);
		System.out.println(order.getId());
	}
	//默认使用左外连接来获取1的一端的对象
	//如果希望使用懒加载获取1的一端的对象，需要修改ManyToOne的属性
	
	@Test
	public void testFind(){
		Order order = entityManager.find(Order.class, 1);
		System.out.println(order.getOrderName());
		System.out.println(order.getCustomer());
	}
	
	
	@Test
	public void testRemove(){
		//多的一端直接删
//		entityManager.remove(entityManager.find(Order.class, 1));
		//不能直接删除1的一端，存在外键关联
		
	}
	
	@Test
	public void testUpdate(){
		Order order = entityManager.find(Order.class, 1);
		Customer customer = order.getCustomer();
		customer.setAge(100);
		entityManager.merge(customer);
	}
	
	@After
	public void tearDown(){
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}
