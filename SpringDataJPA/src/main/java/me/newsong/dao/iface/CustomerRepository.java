package me.newsong.dao.iface;


import java.util.Date;
import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import me.newsong.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>,JpaSpecificationExecutor<Customer>,CustomerRepositoryCustom{
	Customer findByLastName(String string);
	List<Customer> findByLastNameStartingWithAndIdLessThan(String lastName,Integer id);
	List<Customer> findByLastNameContainingAndIdGreaterThan(String lastName,Integer id);
	List<Customer> findByEmailInOrBirthdayBefore(List<String> emails,Date date);
	
	@Query("from Customer c where c.id = (select max(cc.id) from Customer cc )")
	Customer findMaxIdCustomer();
	@Query("from Customer c where c.email = :email and c.age = :age")
	List<Customer> findByEmailAndAge(@Param("age") int age,@Param("email") String email);
	
	@Query("from Customer c where c.email like %:email% and c.lastName like %:lastName%")
	List<Customer> findByEmailAndLastNameLike(@Param("email")String email,@Param("lastName")String lastName);
		
	@Query(value="select count(*) from customers",nativeQuery=true)
	long getTotalCount();
	
	@QueryHints({@QueryHint(name="org.hibernate.cacheable",value="true")})
	@Query("from Customer ")
	List<Customer> findAllCustomers();
	
	@Modifying
	@Query("update Customer c set c.email = :email where c.id = :id")
	void updateEmailByID(@Param("email") String email,@Param("id") Integer id);
	
}
