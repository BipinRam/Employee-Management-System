package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.entity.Designation;
import com.example.demo.model.CompanyModel;
import com.example.demo.model.DesignationModel;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignationService {
    @Autowired
    DesignationRepository designationRepository;

    @Autowired
    CompanyRepository companyRepository;

//    CREATE
    public DesignationModel createDesignation(DesignationModel designationModel)throws Exception {
        Designation designation = new Designation();
        designation.setDesignationName(designationModel.getDesignationName());
        designation.setDesignationCode(designationModel.getDesignationCode());

        Optional<Company> company = companyRepository.findById(designationModel.getCompanyId());
        DesignationModel designationModel1;
        if (company.isPresent()) {
            Company company1 = new Company();
            company1.setId(designationModel.getId());
            designation.setCompany(company1);
            Designation designation1 = designationRepository.save(designation);

            designationModel1 = new DesignationModel();
            designationModel1.setId(designation1.getId());
            designationModel1.setDesignationName(designation1.getDesignationName());
            designationModel1.setDesignationCode(designation1.getDesignationCode());
            designationModel1.setCompanyId(designation1.getCompany().getId());
        } else {
            throw new Exception("Invalid_company_id");
        }
        return designationModel1;
    }
//    READ
    public DesignationModel readDesignation (long id){
        DesignationModel designationModel = new DesignationModel();
        Optional<Designation> designationOptional = designationRepository.findById(id);
        if (designationOptional.isPresent()){
            Designation designation = designationOptional.get();
            designationModel.setId(designation.getId());
            designationModel.setDesignationName(designation.getDesignationName());
            designationModel.setDesignationCode(designation.getDesignationCode());
            designationModel.setCompanyId(designation.getCompany().getId());

            CompanyModel companyModel = new CompanyModel();
            companyModel.setId(designation.getCompany() .getId());
            companyModel.setCompanyName(designation.getDesignationName());
            companyModel.setCompanyCode(designation.getDesignationCode());
            designationModel.setCompanyModel(companyModel);
        }else{
            return null;
        }
        return designationModel;
    }

    public List<DesignationModel> readDesignations (){
        List<DesignationModel> designationModels = new ArrayList<>();
        List<Designation> designationList = designationRepository.findAll();
        if (!designationList.isEmpty()){
            for (Designation designation : designationList) {
                DesignationModel designationModel = new DesignationModel();
                designationModel.setId(designation.getId());
                designationModel.setDesignationName(designation.getDesignationName());
                designationModel.setDesignationCode(designation.getDesignationCode());
                designationModel.setCompanyId(designation.getCompany().getId());

                Company company = designation.getCompany();
                CompanyModel companyModel = new CompanyModel();
                companyModel.setId(company.getId());
                companyModel.setCompanyName(company.getCompanyName());
                companyModel.setCompanyCode(company.getCompanyCode());
                designationModel.setCompanyModel(companyModel);
                designationModels.add(designationModel);
            }
        }else {
            return null;
        }
        return designationModels;


    }
//    UPDATE
    public DesignationModel updateDesignation (Long id , DesignationModel designationModel){
        Designation designation = designationRepository.findById(id).get();
        designation.setId(designationModel.getId());
        designation.setDesignationName(designationModel.getDesignationName());
        designation.setDesignationCode(designationModel.getDesignationCode());

        Company company = designation.getCompany();
        company.setId(designation.getId());
        designation.setCompany(company);
        designationRepository.save(designation);
        return designationModel;
    }

//    DELETE
    public void deleteDesignation (Long id){
        designationRepository.deleteById(id);
    }
}
