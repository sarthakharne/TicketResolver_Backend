package com.had.selfhelp.service;

import com.had.selfhelp.dao.CamplaintRepository;
import com.had.selfhelp.entity.Camplaints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CamplaintServicesImpl implements CamplaintServices {

    private CamplaintRepository camplaintRepository;


    @Autowired
    public CamplaintServicesImpl(CamplaintRepository theCamplaintRepository)
    {
        camplaintRepository = theCamplaintRepository;
    }
    @Override
    public void save(Camplaints c)
    {
          camplaintRepository.save(c);
    }

    @Override
    public List<Camplaints> getAll() {
        return camplaintRepository.findAll();
    }

    @Override
    public Camplaints findById(int id) {
        return camplaintRepository.getReferenceById(id);
    }
}
