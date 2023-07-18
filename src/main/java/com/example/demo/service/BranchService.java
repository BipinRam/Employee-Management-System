package com.example.demo.service;

import com.example.demo.entity.Branch;
import com.example.demo.entity.Company;
import com.example.demo.model.BranchModel;
import com.example.demo.model.CompanyModel;
import com.example.demo.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    @Autowired
    BranchRepository branchRepository;

//    CREATE
      public BranchModel createBranch (BranchModel branchModel){
          Branch branch = new Branch();
          branch.setBranchName(branchModel.getBranchName());
          branch.setBranchCode(branchModel.getBranchCode());

          Company company=new Company();
          company.setId(branchModel.getCompanyId());
          branch.setCompany(company);

          Branch post = branchRepository.save(branch);

          BranchModel branchModel1 = new BranchModel();
          branchModel1.setId(post.getId());
          branchModel1.setBranchName(post.getBranchName());
          branchModel1.setBranchCode(post.getBranchCode());
          branchModel.setCompanyId(post.getCompany().getId());
      return branchModel1;
      }
//      READ
    public BranchModel getBranch(Long id){
        BranchModel branchModel = new BranchModel();
        Optional<Branch> branchOptional = branchRepository.findById(id);
        if(branchOptional.isPresent()) {
            Branch branch = branchOptional.get();
            branchModel.setCompanyId(branch.getCompany().getId());
            branchModel.setId(branch.getId());
            branchModel.setBranchName(branch.getBranchName());
            branchModel.setBranchCode(branch.getBranchCode());

            CompanyModel companyModel = new CompanyModel();

            companyModel.setId(branch.getCompany().getId());
            companyModel.setCompanyCode(branch.getCompany().getCompanyCode());
            companyModel.setCompanyName(branch.getCompany().getCompanyName());
            branchModel.setCompany(companyModel);
        } else {
            return null;
        }
        return branchModel;
    }
    public List<BranchModel> getBranches (){
        List<BranchModel> branchModels = new ArrayList<>();
        List<Branch> branchList = branchRepository.findAll();
        if(!branchList.isEmpty()){
            for (Branch branch: branchList ) {
                BranchModel branchModel = new BranchModel();
                branchModel.setId(branch.getId());
                branchModel.setBranchName(branch.getBranchName());
                branchModel.setBranchCode(branch.getBranchCode());

                Company company = branch.getCompany();
                CompanyModel companyModel = new CompanyModel();
                companyModel.setId(company.getId());
                companyModel.setCompanyCode(company.getCompanyCode());
                companyModel.setCompanyName(company.getCompanyName());
                branchModel.setCompany(companyModel);
                branchModels.add(branchModel);
            }
            }else {
            return null;
        }
        return branchModels;
    }

//    UPDATE
    public BranchModel updateBranch (Long id , BranchModel branchDetails){
          Branch branch = branchRepository.findById(id).get();
          branch.setId(branchDetails.getId());
          branch.setBranchName(branchDetails.getBranchName());
          branch.setBranchCode(branchDetails.getBranchCode());
          branchRepository.save(branch);
          return branchDetails;
    }
//    DELETE
    public void deleteBranchId (Long id){
          branchRepository.deleteById(id);
    }
}



