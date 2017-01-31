package me.newsong.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import me.newsong.dao.iface.CustomerRepositoryCustom;
import me.newsong.domain.Customer;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Customer findByLastName(String lastName) {
		System.out.println("呵呵呵");
		return null;
	}
}
