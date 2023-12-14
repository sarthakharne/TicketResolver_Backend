package com.had.selfhelp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.had.selfhelp.dao.CustomerRepository;
import com.had.selfhelp.entity.Complaints;
import com.had.selfhelp.entity.Customer;
import com.had.selfhelp.entity.LoginRequest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;


    @Test
    void testSave() {
        Customer customer = new Customer();
        ArrayList<Complaints> complaintsList = new ArrayList<>();
        customer.setComplaints(complaintsList);
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1);
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setUsername("janedoe");
        when(customerRepository.save((Customer) any())).thenReturn(customer);

        Customer customer1 = new Customer();
        customer1.setComplaints(new ArrayList<>());
        customer1.setEmail("jane.doe@example.org");
        customer1.setFirstName("Jane");
        customer1.setId(1);
        customer1.setLastName("Doe");
        customer1.setPassword("iloveyou");
        customer1.setUsername("janedoe");
        customerServiceImpl.save(customer1);
        verify(customerRepository).save((Customer) any());
        assertEquals(complaintsList, customer1.getComplaints());
        assertEquals("janedoe", customer1.getUsername());
        assertEquals("iloveyou", customer1.getPassword());
        assertEquals("Doe", customer1.getLastName());
        assertEquals(1, customer1.getId());
        assertEquals("Jane", customer1.getFirstName());
        assertEquals("jane.doe@example.org", customer1.getEmail());
    }


    @Test
    void testSave2() {
        when(customerRepository.save((Customer) any())).thenThrow(new RuntimeException());

        Customer customer = new Customer();
        customer.setComplaints(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1);
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setUsername("janedoe");
        assertThrows(RuntimeException.class, () -> customerServiceImpl.save(customer));
        verify(customerRepository).save((Customer) any());
    }


    @Test
    void testLogin() {
        Customer customer = new Customer();
        customer.setComplaints(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1);
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setUsername("janedoe");
        when(customerRepository.findByUsernameAndPassword((String) any(), (String) any())).thenReturn(customer);
        assertSame(customer, customerServiceImpl.login(new LoginRequest("janedoe", "iloveyou")));
        verify(customerRepository).findByUsernameAndPassword((String) any(), (String) any());
    }


    @Test
    void testLogin3() {
        when(customerRepository.findByUsernameAndPassword((String) any(), (String) any()))
                .thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> customerServiceImpl.login(new LoginRequest("janedoe", "iloveyou")));
        verify(customerRepository).findByUsernameAndPassword((String) any(), (String) any());
    }


    @Test
    void testCostumerComplaint() {
        Customer customer = new Customer();
        ArrayList<Complaints> complaintsList = new ArrayList<>();
        customer.setComplaints(complaintsList);
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1);
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setUsername("janedoe");
        when(customerRepository.getReferenceById((Integer) any())).thenReturn(customer);

        Customer customer1 = new Customer();
        customer1.setComplaints(new ArrayList<>());
        customer1.setEmail("jane.doe@example.org");
        customer1.setFirstName("Jane");
        customer1.setId(1);
        customer1.setLastName("Doe");
        customer1.setPassword("iloveyou");
        customer1.setUsername("janedoe");
        List<Complaints> actualCostumerComplaintResult = customerServiceImpl.costumerComplaint(customer1);
        assertSame(complaintsList, actualCostumerComplaintResult);
        assertTrue(actualCostumerComplaintResult.isEmpty());
        verify(customerRepository).getReferenceById((Integer) any());
    }


    @Test
    void testCostumerComplaint2() {
        when(customerRepository.getReferenceById((Integer) any())).thenThrow(new RuntimeException());

        Customer customer = new Customer();
        customer.setComplaints(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1);
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setUsername("janedoe");
        assertThrows(RuntimeException.class, () -> customerServiceImpl.costumerComplaint(customer));
        verify(customerRepository).getReferenceById((Integer) any());
    }

    @Test
    void testFindById() {
        Customer customer = new Customer();
        customer.setComplaints(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1);
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setUsername("janedoe");
        when(customerRepository.getReferenceById((Integer) any())).thenReturn(customer);
        assertSame(customer, customerServiceImpl.findById(1));
        verify(customerRepository).getReferenceById((Integer) any());
    }

    @Test
    void testFindById2() {
        when(customerRepository.getReferenceById((Integer) any())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> customerServiceImpl.findById(1));
        verify(customerRepository).getReferenceById((Integer) any());
    }
}

