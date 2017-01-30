package me.newsong.one2one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "managers")
public class Manager {
	private Integer id;
	private String name;
	private Department dept;

	public Manager() {
	}

	public Manager(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 由一方维护关联关系，设置@OneToOne，并设置@JoinColumn，unique必须为true
	@JoinColumn(name = "dept_id", unique = true)
	@OneToOne
	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + ", dept=" + dept + "]";
	}

}
