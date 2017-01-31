package me.newsong.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import me.newsong.dao.iface.CustomerRepository;
import me.newsong.domain.Customer;
import me.newsong.service.inter.CustomerService;

public class SpringTest {
	private ApplicationContext ctx;
	private CustomerRepository dao;
	private CustomerService service;
	
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		dao = ctx.getBean(CustomerRepository.class);
		service = ctx.getBean(CustomerService.class);
	}
	
	@Test
	public void add(){
		
	}
	@Test
	public void test() {
		Customer customer = dao.findByLastName("aaa");
		System.out.println(customer);
	}
	
	@Test
	public void test2(){
		List<Customer> customers = dao.findByLastNameStartingWithAndIdLessThan("a", 2);
		System.out.println(customers);
	}
	
	@Test
	public void test3(){
		List<Customer> customers = dao.findByEmailInOrBirthdayBefore(Arrays.asList("111@163.com","222@163.com"), new Date());
		System.out.println(customers);
	}
	
	@Test
	public void test4(){
		Customer customer = dao.findMaxIdCustomer();
		System.out.println(customer);
	}
	
	@Test
	public void test5(){
		List<Customer> customers = dao.findByEmailAndAge(21, "111@163.com");
		System.out.println(customers);
	}
	
	@Test
	public void test6(){
		List<Customer> customers = dao.findByEmailAndLastNameLike("1", "1");
		System.out.println(customers);
	}
	
	@Test
	public void test7(){
		long count = dao.getTotalCount();
		System.out.println(count);
	}
	
	@Test
	public void testModify(){
		service.updateEmailByID("asd@163.com", 2);
	}
	
	@Test
	public void testCrudRepository(){
		for(int i = 0; i < 26; ++i){
			Customer customer = new Customer(i,(char)(i+'a')+"@163.com",""+(char)(i+'a'),new Date(),new Date());
			dao.save(customer);
		}
	}
	
	@Test
	public void testPagingAndSorting(){
		//第一个参数是第几页，从0开始
		//第二个参数是一页几条记录
		//第三个参数是排序对象
		//Sort不是一个接口
		Order nameOrder = new Order(Direction.DESC,"lastName");//传入两个参数，一个是升降序，一个是属性名
		Order emailOrder = new Order(Direction.ASC,"email");
		Sort sort = new Sort(nameOrder,emailOrder);//传入一个Order的集合
		//返回一个Page，是一个类似于迭代器的对象
		PageRequest pageRequest = new PageRequest(0, 5,sort);
		Page<Customer> page  = dao.findAll(pageRequest);
		System.out.println("总记录数："+page.getTotalElements());
		System.out.println("当前页码："+page.getNumber());//从0开始
		System.out.println("总页数："+page.getTotalPages());
		System.out.println("当前页面的List："+page.getContent());
		System.out.println("当前页面的记录数："+page.getNumberOfElements());
	}
	
	@Test
	public void testPagingAndSortingWithQuery(){
		Specification<Customer> specification = new Specification<Customer>() {
			//Root 	待查询的实体类
			//CriteriaQuery
			//CriteriaBuilder：用于创建Criteria相关对象的工厂
			//Preditcate：查询条件
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Path<Integer> path = root.get("id");
				Predicate predicate = cb.gt(path, 40);
				return predicate;
			}
		};//使用匿名内部类
		PageRequest pageRequest = new PageRequest(0, 5);
		Page<Customer> page  = dao.findAll(specification,pageRequest);
		System.out.println("总记录数："+page.getTotalElements());
		System.out.println("当前页码："+page.getNumber());//从0开始
		System.out.println("总页数："+page.getTotalPages());
		System.out.println("当前页面的List："+page.getContent());
		System.out.println("当前页面的记录数："+page.getNumberOfElements());
	}
	
	@Test
	public void testCustomMethod(){
//		dao.findByLastName("asd");
		dao.findAllCustomers();
		dao.findAllCustomers();
	}
}
