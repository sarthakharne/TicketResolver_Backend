package com.had.selfhelp.service;

import com.had.selfhelp.entity.Complaints;
import com.had.selfhelp.entity.Customer;

import java.util.List;

public interface ComplaintServices {

    public void save(Complaints c);
    public List<Complaints> getAll();

    public Complaints findById(int id);

}
