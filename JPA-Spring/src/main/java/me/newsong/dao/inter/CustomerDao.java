package me.newsong.dao.inter;

import java.util.List;

import me.newsong.domain.Customer;

public interface CustomerDao {
	Customer findByID(Integer id);
	List<Customer> findAll();
	void update(Customer customer);
	void delete(Integer id);
	void save(Customer customer);
}
