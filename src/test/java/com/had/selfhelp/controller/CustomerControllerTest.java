package com.had.selfhelp.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.had.selfhelp.entity.Complaints;
import com.had.selfhelp.entity.Customer;
import com.had.selfhelp.entity.LoginRequest;
import com.had.selfhelp.service.ComplaintServices;
import com.had.selfhelp.service.CustomerServices;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CustomerController.class})
@ExtendWith(SpringExtension.class)
class CustomerControllerTest {
    @MockBean
    private ComplaintServices complaintServices;

    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerServices customerServices;

    @Test
    void testSave2() throws Exception {
        doNothing().when(customerServices).save((Customer) any());

        Customer customer = new Customer();
        customer.setComplaints(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1);
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(customer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



}

