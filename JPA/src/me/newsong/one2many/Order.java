package me.newsong.one2many;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "orders")
public class Order {
	private Integer id;
	private String orderName;

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(String orderName) {
		super();
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

	public Order(Integer id, String orderName) {
		super();
		this.id = id;
		this.orderName = orderName;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderName=" + orderName + "]";
	}
	
}
