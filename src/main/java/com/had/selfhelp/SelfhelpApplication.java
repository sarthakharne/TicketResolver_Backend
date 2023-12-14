package com.had.selfhelp;

import com.had.selfhelp.dao.CustomerRepository;
import com.had.selfhelp.entity.Customer;
import com.had.selfhelp.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SelfhelpApplication {

	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(SelfhelpApplication.class, args);

	}

}
