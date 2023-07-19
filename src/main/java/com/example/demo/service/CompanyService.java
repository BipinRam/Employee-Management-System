package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.model.CompanyModel;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

//    CREATE
    public CompanyModel createCompany (CompanyModel com){
        Company comp = new Company();
        comp.setCompanyName(com.getCompanyName());
        comp.setCompanyCode(com.getCompanyCode());
        Company saved = companyRepository.save(comp);

        CompanyModel comm = new CompanyModel();
        comm.setId(saved.getId());
        comm.setCompanyName(saved.getCompanyName());
        comm.setCompanyCode(saved.getCompanyCode());
        return comm;
    }

//    READ
    public CompanyModel  getCompanyId(Long id){
        CompanyModel com = new CompanyModel();
        Optional<Company> compOpt = companyRepository.findById(id);
        if(compOpt.isPresent()) {
            Company comp = compOpt.get();
            com.setId(comp.getId());
            com.setCompanyName(comp.getCompanyName());
            com.setCompanyCode(comp.getCompanyCode());
        } else {
            return null;
        }
        return com;
    }
    public List<CompanyModel> getCompanies (){
        List<CompanyModel> companies = new ArrayList<>();
        List<Company> companyList = companyRepository.findAll();
        if(!companyList.isEmpty()){
            for (Company com: companyList ) {
                CompanyModel model = new CompanyModel();
                model.setCompanyName(com.getCompanyName());
                model.setCompanyCode(com.getCompanyCode());
                model.setId(com.getId());
                companies.add(model);
            }
        }else{
            return null;
        }
        return companies;
    }


//    UPDATE
    public CompanyModel updateCompany (Long id , CompanyModel companyDetails){
        Company com =  companyRepository.findById(id).get();
        com.setId(companyDetails.getId());
        com.setCompanyName(companyDetails.getCompanyName());
        com.setCompanyCode(companyDetails.getCompanyCode());
        companyRepository.save(com);
        return companyDetails;
    }
//    DELETE
    public void deleteCompanyId (Long id){
        companyRepository.deleteById(id);
    }


}


