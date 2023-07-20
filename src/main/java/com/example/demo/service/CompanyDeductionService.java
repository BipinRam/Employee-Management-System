package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.entity.CompanyDeduction;
import com.example.demo.model.CompanyDeductionModel;
import com.example.demo.model.CompanyModel;
import com.example.demo.repository.CompanyDeductionRepository;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyDeductionService {
    @Autowired
    CompanyDeductionRepository companyDeductionRepository;

    @Autowired
    CompanyRepository companyRepository;

//    CREATE
    public CompanyDeductionModel createCompanyDeduction (CompanyDeductionModel companyDeductionModel) throws Exception {
        CompanyDeduction companyDeduction = new CompanyDeduction();
        companyDeduction.setPfPercentage(companyDeductionModel.getPfPercentage());
        companyDeduction.setEsiPercentage(companyDeductionModel.getEsiPercentage());

        Optional<Company> company = companyRepository.findById(companyDeduction.getId());
        CompanyDeductionModel comDedModel;
        if (company.isPresent()){
            Company  company1 = new Company();
            company1.setId(companyDeductionModel.getCompanyId());
            companyDeduction.setCompany(company1);
            CompanyDeduction post = companyDeductionRepository.save(companyDeduction);

            comDedModel = new CompanyDeductionModel();
            comDedModel.setId(post.getId());
            comDedModel.setPfPercentage(post.getPfPercentage());
            comDedModel.setEsiPercentage(post.getEsiPercentage());
            comDedModel.setCompanyId(post.getCompany().getId());
        }else {
            throw new Exception("Invalid company id");
        }

        return  comDedModel;
    }
//    READ
    public CompanyDeductionModel readCompanyDeduction (Long id ){
        CompanyDeductionModel companyDeductionModel = new CompanyDeductionModel();
        Optional <CompanyDeduction> companyDeductionOptional = companyDeductionRepository.findById(id);
        if (companyDeductionOptional.isPresent()){
            CompanyDeduction companyDeduction = companyDeductionOptional.get();
            companyDeductionModel.setId(companyDeductionModel.getId());
            companyDeductionModel.setPfPercentage(companyDeduction.getPfPercentage());
            companyDeductionModel.setEsiPercentage(companyDeduction.getEsiPercentage());
            companyDeductionModel.setCompanyId(companyDeduction.getCompany().getId());

            CompanyModel companyModel = new CompanyModel();
            companyModel.setId(companyDeductionModel.getId());
            companyModel.setCompanyName(companyDeduction.getCompany().getCompanyName());
            companyModel.setCompanyCode(companyDeduction.getCompany().getCompanyCode());
            companyDeductionModel.setCompanyModel(companyModel);
        }
        return companyDeductionModel;
    }

    public List<CompanyDeductionModel> readCompanyDeductions (){
        List<CompanyDeductionModel> companyDeductionModelList = new ArrayList<>();
        List<CompanyDeduction> companyDeductionList = companyDeductionRepository.findAll();
        if (!companyDeductionList.isEmpty()){
            for (CompanyDeduction companyDeduction : companyDeductionList){
                CompanyDeductionModel companyDeductionModel = new CompanyDeductionModel();
                companyDeductionModel.setId(companyDeduction.getId());
                companyDeductionModel.setPfPercentage(companyDeduction.getPfPercentage());
                companyDeductionModel.setEsiPercentage(companyDeduction.getEsiPercentage());
                companyDeductionModel.setCompanyId(companyDeduction.getCompany().getId());

                CompanyModel companyModel = new CompanyModel();
                companyModel.setId(companyDeduction.getId());
                companyModel.setCompanyName(companyDeduction.getCompany().getCompanyName());
                companyModel.setCompanyCode(companyDeduction.getCompany().getCompanyCode());

                companyDeductionModel.setCompanyModel(companyModel);
                companyDeductionModelList.add(companyDeductionModel);
            }
        }else {
            return null;
        }
        return  companyDeductionModelList;
    }
//    UPDATE
    public CompanyDeductionModel updateCompanyDeduction (long id , CompanyDeductionModel companyDeductionModel ){
        CompanyDeduction companyDeduction = companyDeductionRepository.findById(id).get();
        companyDeduction.setId(companyDeductionModel.getId());
        companyDeduction.setPfPercentage(companyDeductionModel.getPfPercentage());
        companyDeduction.setEsiPercentage(companyDeductionModel.getEsiPercentage());

        Company company =companyDeduction.getCompany();
        company.setId(companyDeductionModel.getCompanyId());
        companyDeductionRepository.save(companyDeduction);
        return companyDeductionModel;
    }
//    DELETE
    public void deleteCompanyDeduction(long id){
        companyDeductionRepository.deleteById(id);
    }
}
