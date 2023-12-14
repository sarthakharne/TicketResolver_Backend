package com.had.selfhelp.entity;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="custumer")
public class Customer {
     
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private int id;
	@Column (name="first_name")
	private String firstname;
	@Column (name="last_name")
	private String lastname;
	@Column (name="email")
	private String email;

	@Column (name="username")
	private String username;
	@Column (name="password")
	private  String password;

	@OneToMany(mappedBy = "customer")
	private List<Complaints> complaints;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	
	public Customer(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastName) {
		this.lastname = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Complaints> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaints> complaints) {
		this.complaints = complaints;
	}

	public Customer(int id, String firstName, String lastName, String email, String username, String password) {
		this.id = id;
		this.firstname = firstName;
		this.lastname = lastName;
		this.email = email;
		this.username = username;
		this.password = password;

	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", firstName='" + firstname + '\'' +
				", lastName='" + lastname + '\'' +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", complaints=" + complaints +
				'}';
	}

}
