package me.newsong.mang2one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity
//@Table(name="orders")
public class Order {
	private Integer id;
	private String orderName;
	private Customer customer;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	public Order(String orderName, Customer customer) {
		super();
		this.orderName = orderName;
		this.customer = customer;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="order_name")
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	//映射多对一的关系
	@JoinColumn(name="customer_id")
//	@ManyToOne(fetch=FetchType.LAZY)//懒加载
	@ManyToOne//默认为Eager
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order(Integer id, String orderName, Customer customer) {
		super();
		this.id = id;
		this.orderName = orderName;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderName=" + orderName + ", customer=" + customer + "]";
	}
	
	
}
