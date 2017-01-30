package me.newsong.twoway_one2many;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity
//@Table(name = "orders")
public class Order {
	private Integer id;
	private String orderName;
	private Customer customer;
	public Order() {
		// TODO Auto-generated constructor stub
	}


	public Order(String orderName) {
		this.orderName = orderName;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "order_name")
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	
	@JoinColumn(name="customer_id")
	@ManyToOne
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderName=" + orderName + ", customer=" + customer + "]";
	}
	
}
