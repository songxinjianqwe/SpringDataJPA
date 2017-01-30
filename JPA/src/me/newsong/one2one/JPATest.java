package me.newsong.one2one;

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
		Manager manager = new Manager("宋欣建");
		Department dept = new Department("研发部");
		dept.setMgr(manager);
		manager.setDept(dept);
		entityManager.persist(dept);//先保存不维护关联关系的一方
		entityManager.persist(manager);
	}
	
	
	//默认使用左外连接，一次性全部查出
	//可以通过@OneToOne的fetch属性来修改策略
	//维护关联关系的一方，fetch=LAZY时默认不会直接获取其关联对象
	//但是不维护关联关系的一方，即使fetch=LAZY，也无法使用延迟加载，因为不确定是否有关联的一方（外键在另一张表中）
	//使用fetch=LAZY反而会发送2条sql，降低效率
	@Test
	public void testFind(){
		Department dept = entityManager.find(Department.class, 1);
		Manager manager = dept.getMgr();
		System.out.println(dept.getName());
		System.out.println(dept);
		System.out.println(manager);
	}
	
	@Test
	public void testRemove(){
	}
	
	@Test
	public void testUpdate(){
	}
	
	@After
	public void tearDown(){
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}
