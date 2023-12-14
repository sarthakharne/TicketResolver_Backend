package com.had.selfhelp.service;

import com.had.selfhelp.dao.CustomerRepository;
import com.had.selfhelp.entity.Complaints;
import com.had.selfhelp.entity.Customer;
import com.had.selfhelp.entity.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerServices {

    private CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository thecustomerRepository)
    {
        customerRepository=thecustomerRepository;
    }

    @Override
    public void save(Customer c)
    {
        customerRepository.save(c);
    }

    @Override
    public Customer login(LoginRequest log) {
        Customer c = customerRepository.findByUsernameAndPassword(log.getUsername(), log.getPassword());
        if(c==null)
            throw new RuntimeException("Did not find customer with these credentials");
        return c;
    }

    @Override
    public List<Complaints> costumerComplaint(Customer customer) {
        Customer c = customerRepository.getReferenceById(customer.getId());
        return c.getComplaints();
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.getReferenceById(id);
    }



}
