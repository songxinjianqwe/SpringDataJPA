package me.newsong.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import me.newsong.dao.inter.CustomerDao;
import me.newsong.domain.Customer;
@Repository
public class CustomerDaoImpl implements CustomerDao {
	//获取与当前事务关联的EntityManager
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Customer findByID(Integer id) {
		return null;
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(Customer customer) {
		entityManager.persist(customer);
	}

}
