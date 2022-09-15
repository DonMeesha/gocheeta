package com.gocheeta.api.controller;

import com.gocheeta.api.entity.Branch;
import com.gocheeta.api.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/branches")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @PostMapping()
    public ResponseEntity<Branch> createBranch(@RequestBody Branch branchDetails) {
        return new ResponseEntity<Branch>(branchService.saveAndUpdate(branchDetails), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable long id, @RequestBody  Branch branchDetails) {
        branchDetails.setId(id);
        return new ResponseEntity<Branch>(branchService.saveAndUpdate(branchDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> deleteBranch(@PathVariable long id) {
        try {
            branchService.delete(id);
            Map response = new HashMap();
            response.put("status", HttpStatus.OK);
            response.put("message", "Successfully Deleted");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @GetMapping()
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }
}
