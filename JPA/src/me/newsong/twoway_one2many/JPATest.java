package me.newsong.twoway_one2many;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
	
	//单向n对1总会产生n条update语句（无论先插入哪一方）
	//使用mappedBy就可以不产生update语句（无论先插入哪一方）
	@Test
	public void testAdd(){
		Order order = new Order("789");
		Order order2 = new Order("iii");
		Order order3 = new Order("jjj");
		Customer customer = new Customer(20, "789iiijjj@163.com", "789iiijjj", new Date(), new Date());
		customer.setOrders(Arrays.asList(order,order2,order3));
		entityManager.persist(customer);
		entityManager.persist(order);
		entityManager.persist(order2);
		entityManager.persist(order3);
	}
	
	@Test
	public void testFind(){
		Customer customer = entityManager.find(Customer.class, 1);
		System.out.println(customer.getLastName());
		System.out.println(customer.getOrders());
	}
	
	@Test
	public void testRemove(){
		//可以删除1的一方，类似于 delete on set null，n的1方中的外键被置为空
		//这个也可以通过OneToMany的cascade属性来修改，比如级联删除REMOVE或拒绝删除
		entityManager.remove(entityManager.find(Customer.class, 2));
		
		//n的一方可以直接删除
	}
	
	@Test
	public void testUpdate(){
		Customer customer = entityManager.find(Customer.class,3);
		List<Order> orders = customer.getOrders();
		orders.get(0).setOrderName("123123123131");
	}
	
	@After
	public void tearDown(){
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}
