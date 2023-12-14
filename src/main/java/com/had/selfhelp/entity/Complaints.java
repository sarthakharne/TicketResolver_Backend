package com.had.selfhelp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.had.selfhelp.configuration.AesEncryptor;

import javax.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name="complaints")
public class Complaints {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="com_id")
    private int id;

    @Column(name="services")
    private ArrayList services;

    @Convert(converter = AesEncryptor.class)
    @Column(name="vehicleNo")
    private String vehicleNo;

    @Convert(converter = AesEncryptor.class)
    @Column(name="vehicleType")
    private String vehicleType;
    @Convert(converter = AesEncryptor.class)
    @Column(name="status")
    private String status;
    @Convert(converter = AesEncryptor.class)
    @Column(name="remark")
    private String remark;

    @Convert(converter = AesEncryptor.class)
    @Column(name = "address")
    private String address;



    @Column(name="aknow")
    private String aknow;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    public Complaints() {

    }

    public Complaints(int id, ArrayList services, String vehicleNo, String vehicleType,
                      String status, String remark, String address, String aknow, Customer customer) {
        this.id = id;
        this.services = services;
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.status = status;
        this.remark = remark;
        this.address = address;
        this.aknow = aknow;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList getServices() {
        return services;
    }

    public void setServices(ArrayList services) {
        this.services = services;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getAknow() {
        return aknow;
    }

    public void setAknow(String aknow) {
        this.aknow = aknow;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Complaints{" +
                "id=" + id +
                ", services=" + services +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", address='" + address + '\'' +
                ", aknow='" + aknow + '\'' +
                ", customer=" + customer +
                '}';
    }
}
