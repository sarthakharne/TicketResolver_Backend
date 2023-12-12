package com.had.selfhelp.service;

import com.had.selfhelp.entity.Camplaints;
import com.had.selfhelp.entity.Customer;

import java.util.List;

public interface CamplaintServices {

    public void save(Camplaints c);
    public List<Camplaints> getAll();

    public Camplaints findById(int id);

}
