package me.newsong.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.newsong.dao.iface.CustomerRepository;
import me.newsong.domain.Customer;
import me.newsong.service.inter.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository dao;

	@Transactional
	@Override
	public Customer findByID(Integer id) {
		return null;
	}

	@Transactional
	@Override
	public List<Customer> findAll() {
		return null;
	}

	@Transactional
	@Override
	public void update(Customer customer) {
		
	}

	@Transactional
	@Override
	public void delete(Integer id) {

	}

	@Transactional
	@Override
	public void save(Customer customer) {
	}
	
	@Transactional
	@Override
	public void updateEmailByID(String email, Integer id) {
		dao.updateEmailByID(email, id);
	}

}
