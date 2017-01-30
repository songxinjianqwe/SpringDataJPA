package me.newsong.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.ejb.QueryHints;
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
	public void testFindEntities() {
		String JPQL = "from Customer c where  c.age > ?";
		Query query = entityManager.createQuery(JPQL);
		query.setParameter(1, 20);
		List<Customer> customers = query.getResultList();
		for (Customer c : customers) {
			System.out.println(c);
		}
	}

	// 查询部分属性，返回的是Object[]或者Object[]的List
	// 如果希望返回一个对象，那么需要在select之后写一个new 对象(属性列表)
	// 并且在实体类中提供对应参数的构造器
	// 注意构造器中参数顺序需要和JPQL中参数顺序一致
	@Test
	public void testFindPartlyProperties() {
		// String JPQL = "select c.lastName , c.age from Customer c where c.id >
		// ?";
		// Query query = entityManager.createQuery(JPQL);
		// query.setParameter(1, 2);
		// List<Object[]> properties = query.getResultList();
		// for(Object[] c:properties){
		// for(Object o : c){
		// System.out.print(o+" ");
		// }
		// System.out.println();
		// }
		String JPQL = "select new Customer( c.age,c.lastName) from Customer c where  c.id > ?";
		Query query = entityManager.createQuery(JPQL);
		query.setParameter(1, 2);
		List<Customer> customers = query.getResultList();
		for (Customer c : customers) {
			System.out.println(c);
		}
	}

	// 在实体类前使用@NamedQuery标记了的查询语句
	@Test
	public void testNamedQuery() {
		Query query = entityManager.createNamedQuery("findCustomerByID").setParameter(1, 2);
		Customer customer = (Customer) query.getSingleResult();
		System.out.println(customer);
	}

	// 本地SQL
	@Test
	public void testNativeQuery() {
		String sql = "select age from customers where id = ?";
		Query query = entityManager.createNativeQuery(sql).setParameter(1, 3);
		int age = (int) query.getSingleResult();
		System.out.println(age);
	}

	@Test
	public void testQueryCache() {
		String JPQL = "from Customer c where  c.age > ?";
		Query query = entityManager.createQuery(JPQL);
		List<Customer> customers = query.setParameter(1, 20).setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
		for (Customer c : customers) {
			System.out.println(c);
		}
		System.out.println();
		query = entityManager.createQuery(JPQL);
		customers = query.setParameter(1, 20).setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
		for (Customer c : customers) {
			System.out.println(c);
		}
	}
	
	@Test
	public void testOrderBy(){
		String JPQL = "from Customer c order by c.age asc";
		List<Customer> customers = entityManager.createQuery(JPQL).getResultList();
		for(Customer customer : customers){
			System.out.println(customer);
		}
	}
	
	//查询Order数量大于2 的Customer
	@Test
	public void testGroupBy(){
		String JPQL = "select o.customer from Order o group by o.customer having count(o.id) > :count";
		List<Customer> customers = entityManager.createQuery(JPQL).setParameter("count",2L).getResultList();
		for(Customer c : customers){
			System.out.println(c);
		}
	}
	
	@Test
	public void testJoinQuery(){
		String JPQL = "from Order o inner join fetch o.customer c where  c.id > :customerID";
		List<Order>  orders = entityManager.createQuery(JPQL).setParameter("customerID", 3).getResultList();
		System.out.println(orders);
	}
	
	//查找所有Customer中lastName为aaa的Order，使用子查询的方式
	@Test
	public void testSubQuery(){
		String JPQL = "from Order o where o.customer = (select c from Customer c where c.lastName = :lastName)";
		List<Order> orders = entityManager.createQuery(JPQL).setParameter("lastName", "aaa").getResultList();
		System.out.println(orders);
	}
	
	
	@Test
	public void testJPQLFunction(){
		String JPQL = "select upper(c.email) from Customer c ";
		List<String> emails = entityManager.createQuery(JPQL).getResultList();
		System.out.println(emails);
	}
	
	@Test
	public void testUpdate(){
		String JPQL = "update Customer c set c.age = :age where c.id = :id";
		Query query = entityManager.createQuery(JPQL).setParameter("age", 2000).setParameter("id", 4);
		query.executeUpdate();
	}
	
	@Test
	public void testDelelte(){
		String JPQL = "delete from Order o where o.id > :id";
		Query query = entityManager.createQuery(JPQL).setParameter("id",3);
		query.executeUpdate();
	}
	
	@After
	public void tearDown() {
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}
