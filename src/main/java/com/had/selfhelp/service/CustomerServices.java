package com.had.selfhelp.service;

import com.had.selfhelp.entity.Camplaints;
import com.had.selfhelp.entity.Customer;
import com.had.selfhelp.entity.LoginRequest;

import java.util.List;

public interface CustomerServices {

    public void save(Customer customer);

    public Customer login(LoginRequest log);

    public List<Camplaints> costumerComplaint(Customer c);

    public Customer findById(int id);
}

