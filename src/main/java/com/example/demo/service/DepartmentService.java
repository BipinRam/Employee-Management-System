package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.entity.Department;
import com.example.demo.entity.Designation;
import com.example.demo.model.CompanyModel;
import com.example.demo.model.DepartmentModel;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository depRepository;

    @Autowired
    CompanyRepository companyRepository;

//    CREATE
    public DepartmentModel createDepartment(DepartmentModel depModel)throws Exception{
        Department department = new Department();
        department.setDepartmentName(depModel.getDepartmentName());
        department.setDepartmentCode(depModel.getDepartmentCode());

        Optional<Company> company =  companyRepository.findById(depModel.getCompanyId());
        if (company.isPresent()){
            department.setCompany(company.get());
            depRepository.save(department);

             depModel.setId(department.getId());
            CompanyModel companyModel = new CompanyModel();
            companyModel.setId(department.getCompany().getId());
            companyModel.setCompanyName(department.getCompany().getCompanyName());
            companyModel.setCompanyCode(department.getCompany().getCompanyCode());
            depModel.setCompany(companyModel);

        }else {
            throw new Exception("Invalid company id");
        }
        return depModel;
    }
//    READ
    public DepartmentModel readDepartment (long id){
        DepartmentModel departmentModel = new DepartmentModel();
        Optional<Department> departmentOptional = depRepository.findById(id);
        if (departmentOptional.isPresent()){
            Department department = departmentOptional.get();
            departmentModel.setId(department.getId());
            departmentModel.setDepartmentName(department.getDepartmentName());
            departmentModel.setDepartmentCode(department.getDepartmentCode());
            departmentModel.setCompanyId(department.getCompany().getId());

            CompanyModel companyModel = new CompanyModel();
            companyModel.setId(department.getCompany().getId());
            companyModel.setCompanyName(department.getCompany().getCompanyName());
            companyModel.setCompanyCode(department.getCompany().getCompanyCode());
            departmentModel.setCompany(companyModel);
        }else{
            return null;
        }
        return departmentModel;
    }

    public List<DepartmentModel> readBranches (){
        List<DepartmentModel> departmentModelList = new ArrayList<>();
        List<Department> departmentList = depRepository.findAll();
        if (!departmentList.isEmpty()){
            for (Department department : departmentList){
                DepartmentModel departmentModel = new DepartmentModel();
                departmentModel.setId(department.getId());
                departmentModel.setDepartmentName(department.getDepartmentName());
                departmentModel.setDepartmentCode(department.getDepartmentCode());
                departmentModel.setCompanyId(department.getCompany().getId());

                Company company = department.getCompany();
                CompanyModel companyModel = new CompanyModel();
                companyModel.setId(company.getId());
                companyModel.setCompanyName(company.getCompanyName());
                companyModel.setCompanyCode(company.getCompanyCode());
                departmentModel.setCompany(companyModel);
                departmentModelList.add(departmentModel);
            }
        }else {
            return null;
        }
        return departmentModelList;
    }
//    UPDATE
    public  DepartmentModel updateDepartment (Long id ,  DepartmentModel departmentModel){
        Department department = depRepository.findById(id).get();
        department.setId(departmentModel.getId());
        department.setDepartmentName(departmentModel.getDepartmentName());
        department.setDepartmentCode(departmentModel.getDepartmentCode());

        Optional<Company> company = companyRepository.findById(departmentModel.getCompanyId());
        department.setCompany(company.get());
        depRepository.save(department);

        departmentModel.setId(department.getId());
        CompanyModel companyModel = new CompanyModel();
        companyModel.setId(department.getCompany().getId());
        companyModel.setCompanyName(department.getCompany().getCompanyName());
        companyModel.setCompanyCode(department.getCompany().getCompanyCode());
        departmentModel.setCompany(companyModel);

        return departmentModel;
    }
//    DELETE
    public void deleteDepartment(Long id)throws Exception{
        Optional<Department> department = depRepository.findById(id);
        if (department.isPresent()){
            depRepository.deleteById(id);
        }else {
            throw new Exception();
        }

    }

}





