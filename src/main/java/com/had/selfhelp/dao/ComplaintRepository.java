package com.had.selfhelp.dao;

import com.had.selfhelp.entity.Complaints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaints,Integer> {
}
