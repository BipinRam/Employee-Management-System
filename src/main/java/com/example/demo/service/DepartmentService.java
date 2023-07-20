package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.entity.Department;
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
        DepartmentModel departmentModel;
        if (company.isPresent()){
            Company company1 = new Company();
            company1.setId(depModel.getCompanyId());
            department.setCompany(company1);
            Department depart = depRepository.save(department);

            departmentModel = new DepartmentModel();
            departmentModel.setId(depart.getId());
            departmentModel.setDepartmentName(depart.getDepartmentName());
            departmentModel.setDepartmentCode(depart.getDepartmentCode());
            departmentModel.setCompanyId(depart.getCompany().getId());
        }else {
            throw new Exception("Invalid company id");
        }
        return departmentModel;
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
            departmentModel.setCompanyModel(companyModel);
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
                departmentModel.setCompanyModel(companyModel);
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

        Company company = department.getCompany();
        company.setId(departmentModel.getCompanyId());
        department.setCompany(company);
        depRepository.save(department);
        return departmentModel;
    }
//    DELETE
    public void deleteDepartment(Long id){
        depRepository.deleteById(id);
    }

}





