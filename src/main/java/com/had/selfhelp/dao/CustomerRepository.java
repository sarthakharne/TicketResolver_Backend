package com.had.selfhelp.dao;


import com.had.selfhelp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

     Customer findByUsernameAndPassword(String username,String Password);
}
