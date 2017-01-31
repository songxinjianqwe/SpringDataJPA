package me.newsong.service.inter;

import java.util.List;

import me.newsong.domain.Customer;

public interface CustomerService {
	Customer findByID(Integer id);
	List<Customer> findAll();
	void update(Customer customer);
	void delete(Integer id);
	void save(Customer customer);
	
	void updateEmailByID(String email,Integer id);
}	
