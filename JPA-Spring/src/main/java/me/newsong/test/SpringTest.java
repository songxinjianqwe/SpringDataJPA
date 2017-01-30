package me.newsong.test;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import me.newsong.domain.Customer;
import me.newsong.service.inter.CustomerService;

public class SpringTest {
	private ApplicationContext ctx;
	private CustomerService service;
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		service = ctx.getBean(CustomerService.class);
	}

	@Test
	public void test() {
		Customer customer = new Customer(21, "aaa@163.com", "aaa", new Date(), new Date());
		service.save(customer);
	}

}
