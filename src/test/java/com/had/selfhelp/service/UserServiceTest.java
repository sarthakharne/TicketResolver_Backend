package com.had.selfhelp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.had.selfhelp.dao.RoleRepository;
import com.had.selfhelp.dao.UserRepository;
import com.had.selfhelp.entity.Customer;
import com.had.selfhelp.entity.Role;
import com.had.selfhelp.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void testFindAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);
        List<User> actualFindAllUsersResult = userService.findAllUsers();
        assertSame(userList, actualFindAllUsersResult);
        assertTrue(actualFindAllUsersResult.isEmpty());
        verify(userRepository).findAll();
    }

    @Test
    void testFindAllUsers2() {
        when(userRepository.findAll()).thenThrow(new ExpressionException("An error occurred"));
        assertThrows(ExpressionException.class, () -> userService.findAllUsers());
        verify(userRepository).findAll();
    }
    @Test
    void testFindByUserId() {
        User user = new User();
        user.setPassword("iloveyou");
        user.setRoleSet(new HashSet<>());
        user.setUserId(1);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById((Integer) any())).thenReturn(ofResult);
        ResponseEntity<User> actualFindByUserIdResult = userService.findByUserId(1);
        assertTrue(actualFindByUserIdResult.hasBody());
        assertTrue(actualFindByUserIdResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualFindByUserIdResult.getStatusCode());
        verify(userRepository).findById((Integer) any());
    }

    @Test
    void testFindByUserId2() {
        when(userRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ExpressionException.class, () -> userService.findByUserId(1));
        verify(userRepository).findById((Integer) any());
    }

    @Test
    void testFindByUserId3() {
        when(userRepository.findById((Integer) any())).thenThrow(new ExpressionException("An error occurred"));
        assertThrows(ExpressionException.class, () -> userService.findByUserId(1));
        verify(userRepository).findById((Integer) any());
    }

    @Test
    void testCreateUser() {
        Role role = new Role();
        role.setId(1);
        role.setName("Name");
        when(roleRepository.findByName((String) any())).thenReturn(role);

        User user = new User();
        user.setPassword("iloveyou");
        user.setRoleSet(new HashSet<>());
        user.setUserId(1);
        user.setUsername("janedoe");
        when(userRepository.save((User) any())).thenReturn(user);

        Customer customer = new Customer();
        customer.setComplaints(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1);
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setUsername("janedoe");
        ResponseEntity<User> actualCreateUserResult = userService.createUser(customer);
        assertTrue(actualCreateUserResult.hasBody());
        assertTrue(actualCreateUserResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCreateUserResult.getStatusCode());
        verify(roleRepository).findByName((String) any());
        verify(userRepository).save((User) any());
    }

    @Test
    void testCreateUser2() {
        Role role = new Role();
        role.setId(1);
        role.setName("Name");
        when(roleRepository.findByName((String) any())).thenReturn(role);
        when(userRepository.save((User) any())).thenThrow(new ExpressionException("An error occurred"));

        Customer customer = new Customer();
        customer.setComplaints(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1);
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setUsername("janedoe");
        assertThrows(ExpressionException.class, () -> userService.createUser(customer));
        verify(roleRepository).findByName((String) any());
        verify(userRepository).save((User) any());
    }

    @Test
    void testSave() {
        User user = new User();
        user.setPassword("iloveyou");
        user.setRoleSet(new HashSet<>());
        user.setUserId(1);
        user.setUsername("janedoe");
        when(userRepository.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setPassword("iloveyou");
        user1.setRoleSet(new HashSet<>());
        user1.setUserId(1);
        user1.setUsername("janedoe");
        userService.save(user1);
        verify(userRepository).save((User) any());
        assertEquals("iloveyou", user1.getPassword());
        assertEquals("janedoe", user1.getUsername());
        assertEquals(1, user1.getUserId());
        assertTrue(user1.getRoleSet().isEmpty());
        assertTrue(userService.findAllUsers().isEmpty());
    }

    @Test
    void testSave2() {
        when(userRepository.save((User) any())).thenThrow(new ExpressionException("An error occurred"));

        User user = new User();
        user.setPassword("iloveyou");
        user.setRoleSet(new HashSet<>());
        user.setUserId(1);
        user.setUsername("janedoe");
        assertThrows(ExpressionException.class, () -> userService.save(user));
        verify(userRepository).save((User) any());
    }
}

