package com.had.selfhelp.controller;

import com.had.selfhelp.entity.Complaints;
import com.had.selfhelp.entity.Customer;
import com.had.selfhelp.entity.LoginRequest;
import com.had.selfhelp.service.ComplaintServices;
import com.had.selfhelp.service.CustomerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerServices customerServices;

     @Autowired
    ComplaintServices complaintServices;

    @PostMapping("/customer")
    public void save(@RequestBody Customer c ){
        customerServices.save(c);
    }

    @PostMapping("/customer/{cust_id}")
    public List<Complaints> save(@RequestBody Complaints c , @PathVariable(name="cust_id")int cust_id)
    {
         Customer cust = customerServices.findById(cust_id);
         c.setCustomer(cust);
         c.setStatus("Pending");
         c.setAknow("Pending");
         complaintServices.save(c);
         log.info("Registering the complaints");
         return customerServices.costumerComplaint(cust);
    }

    @GetMapping("/complaints/{cust_id}")
    public List<Complaints>getcomplaints(@PathVariable(name="cust_id")int cust_id)
    {
        log.info("updating the complaints instance");
        Customer cust = customerServices.findById(cust_id);
        if(cust_id==1)
        {
          return   complaintServices.getAll();
        }
        else
            return customerServices.costumerComplaint(cust);
    }
    @PostMapping("/login")
    public Customer validate(@RequestBody LoginRequest log)
    {
          return customerServices.login(log);
    }

    @PostMapping("/owner/status/{com_id}")
    public void status(@PathVariable(name="com_id")int com_id)
    {
        log.info("cheking complaints status");
        Complaints c =  complaintServices.findById(com_id);
        c.setStatus("Updating the complaint");
        c.setStatus("Done");
        complaintServices.save(c);

    }

    @PostMapping("/owner/aknow/{com_id}/{aknow}")
    public void status(@PathVariable(name="com_id")int com_id,@PathVariable(name="aknow")String aknow)
    {
        log.info("Aknowledge the retraction of complaints");
        Complaints c =  complaintServices.findById(com_id);
        c.setAknow(aknow);
        c.setStatus("Completed the task and deleting it.");
        complaintServices.save(c);
    }


}
