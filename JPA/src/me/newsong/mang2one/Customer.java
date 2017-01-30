package me.newsong.mang2one;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

//@Entity
//@Table(name = "customers")
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

	public Customer(int age, String email, String lastName, Date createdTime, Date brithday) {
		super();
		this.age = age;
		this.email = email;
		this.lastName = lastName;
		this.createdTime = createdTime;
		this.brithday = brithday;
	}

	@Column(name = "id") // 如果不指定Column的name，那么字段名和成员变量名一致
	@GeneratedValue(strategy = GenerationType.AUTO)
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
		return "Customer [id=" + id + ", age=" + age + ", email=" + email + ", lastName=" + lastName + "]";
	}

}
