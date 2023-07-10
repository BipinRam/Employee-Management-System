package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CompanyController {
    @Autowired
    CompanyService comService;

    @PostMapping(value = "/company/create")
    public Company createCompany (@RequestBody Company com){
        return comService.createCompany(com);
    }
    @GetMapping(value = "/company/read")
    public List<Company> readCompany(){
        return comService.getCompany();
    }
    @PutMapping(value = "/company/read")
    public Company readCompany(@PathVariable (value = "id")Long id , @RequestBody Company companyDetails){
        return comService.updateCompany(id , companyDetails);
    }
    @DeleteMapping(value = "/company/delete")
    public void  deleteCompany(@PathVariable(value = "id") Long id){
        comService.deleteCompany(id);
    }
}

