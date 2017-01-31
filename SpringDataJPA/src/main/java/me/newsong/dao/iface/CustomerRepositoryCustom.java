package me.newsong.dao.iface;

import me.newsong.domain.Customer;

public interface CustomerRepositoryCustom {
	Customer findByLastName(String lastName);
}
