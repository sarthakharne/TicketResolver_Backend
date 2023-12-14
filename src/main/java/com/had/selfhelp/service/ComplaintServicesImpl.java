package com.had.selfhelp.service;

import com.had.selfhelp.dao.ComplaintRepository;
import com.had.selfhelp.entity.Complaints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServicesImpl implements ComplaintServices {

    private ComplaintRepository complaintRepository;


    @Autowired
    public ComplaintServicesImpl(ComplaintRepository theComplaintRepository)
    {
        complaintRepository = theComplaintRepository;
    }
    @Override
    public void save(Complaints c)
    {
          complaintRepository.save(c);
    }

    @Override
    public List<Complaints> getAll() {
        return complaintRepository.findAll();
    }

    @Override
    public Complaints findById(int id) {
        return complaintRepository.getReferenceById(id);
    }
}
