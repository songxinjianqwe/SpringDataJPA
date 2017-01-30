package me.newsong.many2many;

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
	
	@Test
	public void testAdd(){
		Item item = new Item("旺仔牛奶");
		Category category = new Category("乳制品");
		Category category2 = new Category("饮料");
		item.setCategories(Arrays.asList(category,category2));
		category.setItems(Arrays.asList(item));
		category2.setItems(Arrays.asList(item));
		
		entityManager.persist(item);
		entityManager.persist(category);
		entityManager.persist(category2);
	}
	
	//对于双向多对多的双方，获取时默认都使用懒加载策略
	@Test
	public void testFind(){
		Item item = entityManager.find(Item.class, 1);
		System.out.println(item.getName());
		System.out.println(item.getCategories());
	}
		
		
	@After
	public void tearDown(){
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}
