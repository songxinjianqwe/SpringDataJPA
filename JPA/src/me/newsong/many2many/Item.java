package me.newsong.many2many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name="items")
@Entity
public class Item {
	private Integer id;
	private String name;
	private List<Category> categories = new ArrayList<Category>();
	public Item() {
	}
	
	public Item(String name) {
		super();
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
	@Column(length=20,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//joinColumn是当前表映射到在关联表中的外键，关联在当前表的id列上
	//inverseJoinColumn是对方表映射在关联表中的外键，关联在对方表的id列上
	@JoinTable(name="item_category",
			   joinColumns={@JoinColumn(name="item_id",referencedColumnName="id")},
			   inverseJoinColumns={@JoinColumn(name="category_id",referencedColumnName="id")})
	@ManyToMany
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + "]";
	}
	
}
