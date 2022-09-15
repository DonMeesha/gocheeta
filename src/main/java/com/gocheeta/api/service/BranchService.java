package com.gocheeta.api.service;

import com.gocheeta.api.entity.Branch;

import java.util.List;

public interface BranchService {
    Branch saveAndUpdate(Branch newBranch);
    void delete(Long id);
    List<Branch> getAllBranches();
}
