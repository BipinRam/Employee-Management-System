package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.repository.CompanyRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

//    CREATE
    public Company createCompany (Company com){
        return companyRepository.save(com);
    }
//    READ
    public List< Company > getCompany(){
        return companyRepository.findAll();
    }
//    UPDATE
    public Company updateCompany (Long id , @NotNull Company companyDetails){
        Company com =  new Company();
        com.setCompanyName(companyDetails.getCompanyName());
        com.setCompanyCode(companyDetails.getCompanyCode());
        return companyRepository.save(com);
    }
//    DELETE
    public void deleteCompany (Long id){
        companyRepository.deleteById(id);
    }
}


