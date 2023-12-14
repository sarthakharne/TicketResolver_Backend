package com.had.selfhelp.dao;

import com.had.selfhelp.entity.Complaints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CamplaintRepository extends JpaRepository<Camplaints,Integer> {

    Camplaints findById(Integer id;);
}
