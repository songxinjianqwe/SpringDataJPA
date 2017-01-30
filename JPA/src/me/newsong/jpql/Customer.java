package me.newsong.jpql;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@NamedQuery(name="findCustomerByID",query="from Customer c where c.id = ?")
@Entity
@Table(name = "customers")
public class Customer {
	private Integer id;
	private int age;
	private String email;
	private String lastName;
	private Date createdTime;
	private Date brithday;
	private String info;
	

	public Customer() {
	}
	
	public Customer(int age, String lastName) {
		super();
		this.age = age;
		this.lastName = lastName;
	}

	public Customer(int age, String email, String lastName, Date createdTime, Date brithday) {
		super();
		this.age = age;
		this.email = email;
		this.lastName = lastName;
		this.createdTime = createdTime;
		this.brithday = brithday;
	}

	@GeneratedValue()
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Temporal(TemporalType.DATE)
	public Date getBrithday() {
		return brithday;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	@Transient
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", age=" + age + ", email=" + email + ", lastName=" + lastName + ", createdTime="
				+ createdTime + ", brithday=" + brithday + ", info=" + info + "]";
	}
	
	
}
