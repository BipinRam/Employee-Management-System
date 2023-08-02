package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.model.*;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.DesignationRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository empRepository;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    DesignationRepository designationRepository;

    // CREATE
   public EmployeeModel createEmployee (EmployeeModel employeeModel)throws Exception{
       Employee employee = new Employee();
       employee.setEmployeeFullName(employeeModel.getEmployeeFullName());
       employee.setMobileNumber(employeeModel.getMobileNumber());
       employee.setEmail(employeeModel.getEmail());
       employee.setBloodGroup(employeeModel.getBloodGroup());
       empRepository.save(employee);

       Optional<Branch> branch = branchRepository.findById(employeeModel.getBranchId());
       Optional<Department> department = departmentRepository.findById(employeeModel.getDepartmentId());
       Optional<Designation> designation = designationRepository.findById(employeeModel.getDesignationID());

       if (branch.isPresent() && department.isPresent() && designation.isPresent()){
           employee.setBranch(branch.get());
           employee.setDepartment(department.get());
           employee.setDesignation(designation.get());

           employeeModel.setId(employee.getId());
           BranchModel branchModel = new BranchModel();
           branchModel.setId(employee.getBranch().getId());
           branchModel.setBranchName(employee.getBranch().getBranchName());
           branchModel.setBranchCode(employee.getBranch().getBranchCode());
           employeeModel.setBranch(branchModel);

           DepartmentModel departmentModel = new DepartmentModel();
           departmentModel.setId(employee.getDepartment().getId());
           departmentModel.setDepartmentName(employee.getDepartment().getDepartmentName());
           departmentModel.setDepartmentCode(employee.getDepartment().getDepartmentCode());
           employeeModel.setDepartment(departmentModel);

           DesignationModel designationModel = new DesignationModel();
           designationModel.setId(employee.getDesignation().getId());
           designationModel.setDesignationName(employee.getDesignation().getDesignationName());
           designationModel.setDesignationCode(employee.getDesignation().getDesignationCode());
           employeeModel.setDesignation(designationModel);

       }else {
           throw  new Exception("Invalid branch/department/designation id");
       }
       return employeeModel;
   }
//   READ
    public EmployeeModel getEmployeeId (Long id){
        EmployeeModel employeeModel = new EmployeeModel();
        Optional<Employee> employeeOptional = empRepository.findById(id);
        if (employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            employeeModel.setId(employee.getId());
            employeeModel.setEmployeeFullName(employee.getEmployeeFullName());
            employeeModel.setMobileNumber(employee.getMobileNumber());
            employeeModel.setEmail(employee.getEmail());
            employeeModel.setBloodGroup(employee.getBloodGroup());

            BranchModel branchModel = new BranchModel();
            branchModel.setId(employee.getBranch().getId());
            branchModel.setBranchName(employee.getBranch().getBranchName());
            branchModel.setBranchCode(employee.getBranch().getBranchCode());
            employeeModel.setBranch(branchModel);

            DepartmentModel departmentModel = new DepartmentModel();
            departmentModel.setId(employee.getDepartment().getId());
            departmentModel.setDepartmentName(employee.getDepartment().getDepartmentName());
            departmentModel.setDepartmentCode(employee.getDepartment().getDepartmentCode());
            employeeModel.setDepartment(departmentModel);

            DesignationModel designationModel = new DesignationModel();
            designationModel.setId(employee.getDesignation().getId());
            designationModel.setDesignationName(employee.getDesignation().getDesignationName());
            designationModel.setDesignationCode(employee.getDesignation().getDesignationCode());
            employeeModel.setDesignation(designationModel);
        }else {
            return null;
        }
        return employeeModel;
    }

    public List<EmployeeModel> getEmployee (){
       List<EmployeeModel> employeeModelList = new ArrayList<>();
       List<Employee> employeeList = empRepository.findAll();

       if (!employeeList.isEmpty()){
           for (Employee employee : employeeList){
               EmployeeModel employeeModel = new EmployeeModel();
               employeeModel.setId(employee.getId());
               employeeModel.setEmployeeFullName(employee.getEmployeeFullName());
               employeeModel.setMobileNumber(employee.getMobileNumber());
               employeeModel.setEmail(employee.getEmail());
               employeeModel.setBloodGroup(employee.getBloodGroup());

               employeeModel.setBranchId(employee.getBranch().getId());
               employeeModel.setDepartmentId(employee.getDepartment().getId());
               employeeModel.setDesignationID(employee.getDesignation().getId());

              Branch branch = employee.getBranch();
              BranchModel branchModel = new BranchModel();
              branchModel.setId(branch.getId());
              branchModel.setBranchName(branch.getBranchName());
              branchModel.setBranchCode(branch.getBranchCode());
              employeeModel.setBranch(branchModel);


              Department department = employee.getDepartment();
              DepartmentModel departmentModel = new DepartmentModel();
              departmentModel.setId(department.getId());
              departmentModel.setDepartmentName(department.getDepartmentName());
              departmentModel.setDepartmentCode(department.getDepartmentCode());
              employeeModel.setDepartment(departmentModel);


              Designation designation = employee.getDesignation();
              DesignationModel designationModel = new DesignationModel();
              designationModel.setId(designation.getId());
              designationModel.setDesignationName(designation.getDesignationName());
              designationModel.setDesignationCode(designation.getDesignationCode());
              employeeModel.setDesignation(designationModel);

              employeeModelList.add(employeeModel);
           }
       }
       return employeeModelList;
    }
//    UPDATE
    public EmployeeModel updateEmployee (Long id , EmployeeModel employeeModel)throws Exception{
       Employee employee = empRepository.findById(id).get();
        employee.setEmployeeFullName(employeeModel.getEmployeeFullName());
        employee.setMobileNumber(employeeModel.getMobileNumber());
        employee.setEmail(employeeModel.getEmail());
        employee.setBloodGroup(employeeModel.getBloodGroup());

        Optional<Branch> branch = branchRepository.findById(employeeModel.getBranchId());
        Optional<Department> department = departmentRepository.findById(employeeModel.getDepartmentId());
        Optional<Designation> designation = designationRepository.findById(employeeModel.getDesignationID());

        if (branch.isPresent()|| department.isPresent()|| designation.isPresent()){
            employee.setBranch(branch.get());
            employee.setDepartment(department.get());
            employee.setDesignation(designation.get());
            empRepository.save(employee);

            employeeModel.setId(employee.getId());
            BranchModel branchModel = new BranchModel();
            branchModel.setId(employee.getBranch().getId());
            branchModel.setBranchName(employee.getBranch().getBranchName());
            branchModel.setBranchCode(employee.getBranch().getBranchCode());
            employeeModel.setBranch(branchModel);

            DepartmentModel departmentModel = new DepartmentModel();
            departmentModel.setId(employee.getDepartment().getId());
            departmentModel.setDepartmentName(employee.getDepartment().getDepartmentName());
            departmentModel.setDepartmentCode(employee.getDepartment().getDepartmentCode());
            employeeModel.setDepartment(departmentModel);

            DesignationModel designationModel = new DesignationModel();
            designationModel.setId(employee.getDesignation().getId());
            designationModel.setDesignationName(employee.getDesignation().getDesignationName());
            designationModel.setDesignationCode(employee.getDesignation().getDesignationCode());
            employeeModel.setDesignation(designationModel);
        }else {
            throw  new Exception("Invalid branch/department/designation id");
        }
        return employeeModel;

    }
//    DELETE
    public void deleteEmployee (Long id) throws  Exception{
       Optional<Employee> employeeOptional = empRepository.findById(id);
       if (employeeOptional.isPresent()){
           empRepository.deleteById(id);
       }else {
           throw new Exception("Invalid id");
       }
    }


}
