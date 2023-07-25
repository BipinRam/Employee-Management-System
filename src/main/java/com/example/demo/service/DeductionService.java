package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.entity.Deduction;
import com.example.demo.model.DeductionModel;
import com.example.demo.repository.DeductionRepository;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DeductionService {
    @Autowired
    DeductionRepository deductionRepository;

    @Autowired
    CompanyRepository companyRepository;

//    CREATE
    public DeductionModel createCompanyDeduction (DeductionModel deductionModel) throws Exception {
        Deduction deduction = new Deduction();
        deduction.setProvidentFund(deductionModel.getProvidentFund());
        deduction.setEmployeeStateFund(deductionModel.getEmployeeStateFund());


        Optional<Company> company = companyRepository.findById(deductionModel.getCompanyId());
        DeductionModel deductionModel1;
        if (company.isPresent()){
            Deduction alreadySavedDeduction = deductionRepository.findByCompanyId(company.get().getId());
            Deduction deduction1;
            if(alreadySavedDeduction != null){
                alreadySavedDeduction.setProvidentFund(deductionModel.getProvidentFund());
                alreadySavedDeduction.setEmployeeStateFund(deductionModel.getEmployeeStateFund());
                deduction.setCompany(company.get());
                deduction1 = deductionRepository.save(alreadySavedDeduction);
            } else {

                Company company1 = new Company();
                company1.setId(deductionModel.getCompanyId());
                deduction.setCompany(company1);
                deduction1 = deductionRepository.save(deduction);
            }
            deductionModel1 = new DeductionModel();
            deductionModel1.setId(deduction1.getId());
            deductionModel1.setProvidentFund(deduction1.getProvidentFund());
            deductionModel1.setEmployeeStateFund(deduction1.getEmployeeStateFund());
            deductionModel1.setCompanyId(deduction1.getCompany().getId());
        }else {
            throw new Exception("Invalid company id");
        }

        return  deductionModel1;
    }

}
