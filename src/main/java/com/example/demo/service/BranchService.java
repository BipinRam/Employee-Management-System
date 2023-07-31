package com.example.demo.service;
import com.example.demo.entity.Branch;
import com.example.demo.entity.Company;
import com.example.demo.model.BranchModel;
import com.example.demo.model.CompanyModel;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    @Autowired
    BranchRepository branchRepository;

    @Autowired
    CompanyRepository companyRepository;

//    CREATE
      public BranchModel createBranch (BranchModel branchModel) throws Exception {
          Branch branch = new Branch();
          branch.setBranchName(branchModel.getBranchName());
          branch.setBranchCode(branchModel.getBranchCode());

          Optional<Company> company = companyRepository.findById(branchModel.getCompanyId());
          if (company.isPresent()) {
              branch.setCompany(company.get());
              branchRepository.save(branch);

              branchModel.setId(branch.getId());
              CompanyModel companyModel = new CompanyModel();
              companyModel.setId(branch.getCompany().getId());
              companyModel.setCompanyName(branch.getCompany().getCompanyName());
              companyModel.setCompanyCode(branch.getCompany().getCompanyCode());
              branchModel.setCompany(companyModel);
          } else {
              throw new Exception("Invalid company id");
          }
          return branchModel;
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
                branchModel.setCompanyId(branch.getCompany().getId());
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
    public BranchModel updateBranch (Long id , BranchModel branchModel){
          Branch branch = branchRepository.findById(id).get();
          branch.setId(branchModel.getId());
          branch.setBranchName(branchModel.getBranchName());
          branch.setBranchCode(branchModel.getBranchCode());

         Optional<Company> company = companyRepository.findById(branchModel.getCompanyId());
         branch.setCompany(company.get());
         branchRepository.save(branch);

         branchModel.setId(branch.getId());
         CompanyModel companyModel = new CompanyModel();
         companyModel.setId(branch.getCompany().getId());
         companyModel.setCompanyName(branch.getCompany().getCompanyName());
         companyModel.setCompanyCode(branch.getCompany().getCompanyCode());
         branchModel.setCompany(companyModel);

          return branchModel;
    }
//    DELETE
    public void deleteBranchId (Long id)throws Exception{
          Optional<Branch> branch = branchRepository.findById(id);
          if (branch.isPresent()){
              branchRepository.deleteById(id);
          }else {
              throw new Exception();
          }
    }
}



