package com.gocheeta.api.service.impl;

import com.gocheeta.api.entity.Branch;
import com.gocheeta.api.repository.BranchRepository;
import com.gocheeta.api.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IBranchService implements BranchService {

    @Autowired
    private BranchRepository repository;

    @Override
    public Branch saveAndUpdate(Branch newBranch) {
        try {
            return repository.save(newBranch);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public List<Branch> getAllBranches() {
        try {
            return repository.findAll();
        } catch (Exception exception) {
            throw exception;
        }
    }
}
