package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeSalary;
import com.example.demo.model.EmployeeModel;
import com.example.demo.model.EmployeeSalaryModel;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeSalaryService {
    @Autowired
    EmployeeSalaryRepository empSalRepository;

    @Autowired
    EmployeeRepository employeeRepository;

//    CREATE
    public EmployeeSalaryModel CreateEmpSal (EmployeeSalaryModel empSalModel)throws Exception{
        EmployeeSalary employeeSalary = new EmployeeSalary();
        employeeSalary.setBasic(empSalModel.getBasic());
        employeeSalary.setDearnessAllowance(empSalModel.getDearnessAllowance());
        employeeSalary.setHouseRentAllowance(empSalModel.getHouseRentAllowance());
        employeeSalary.setOtherAdditions(empSalModel.getOtherAdditions());

        Optional<Employee> employeeOptional =employeeRepository.findById(empSalModel.getEmployeeId());
        if (employeeOptional.isPresent()){
            employeeSalary.setEmployee(employeeOptional.get());

            empSalModel.setId(employeeSalary.getId());
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setId(employeeSalary.getEmployee().getId());
            employeeModel.setEmployeeFullName(employeeSalary.getEmployee().getEmployeeFullName());
            empSalModel.setEmployee(employeeModel);

            int totalPay = employeeSalary.getBasic() + employeeSalary.getDearnessAllowance() + employeeSalary.getHouseRentAllowance() + employeeSalary.getOtherAdditions();
            employeeSalary.setTotalPay(totalPay);
            empSalRepository.save(employeeSalary);

            empSalModel.setTotalPay(employeeSalary.getTotalPay());
        }else {
            throw new Exception("Invalid_employee_id");
        }
        return empSalModel;
    }
//    READ
    public EmployeeSalaryModel readEmpSal (Long id){
        EmployeeSalaryModel empSalModel = new EmployeeSalaryModel();
        Optional<EmployeeSalary> empSalOptional = empSalRepository.findById(id);
        if (empSalOptional.isPresent()){
            EmployeeSalary employeeSalary = empSalOptional.get();
            empSalModel.setId(employeeSalary.getId());
            empSalModel.setBasic(employeeSalary.getBasic());
            empSalModel.setDearnessAllowance(employeeSalary.getDearnessAllowance());
            empSalModel.setHouseRentAllowance(employeeSalary.getHouseRentAllowance());
            empSalModel.setOtherAdditions(employeeSalary.getOtherAdditions());
            empSalModel.setTotalPay(employeeSalary.getTotalPay());


            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setId(employeeSalary.getEmployee().getId());
            employeeModel.setEmployeeFullName(employeeSalary.getEmployee().getEmployeeFullName());
            empSalModel.setEmployee(employeeModel);
        }else{
            return null;
        }
        return empSalModel;
    }
    public List<EmployeeSalaryModel> readEmpSalaries() {
        List<EmployeeSalaryModel> employeeSalaryModels = new ArrayList<>();
        List<EmployeeSalary> employeeSalaries = empSalRepository.findAll();
        if (!employeeSalaries.isEmpty()){
            for (EmployeeSalary employeeSalary : employeeSalaries){
                EmployeeSalaryModel employeeSalaryModel = new EmployeeSalaryModel();
                employeeSalaryModel.setId(employeeSalary.getId());
                employeeSalaryModel.setBasic(employeeSalary.getBasic());
                employeeSalaryModel.setDearnessAllowance(employeeSalary.getDearnessAllowance());
                employeeSalaryModel.setHouseRentAllowance(employeeSalary.getHouseRentAllowance());
                employeeSalaryModel.setOtherAdditions(employeeSalary.getOtherAdditions());
                employeeSalaryModel.setTotalPay(employeeSalary.getTotalPay());

                Employee employee = employeeSalary.getEmployee();
                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setId(employee.getId());
                employeeModel.setEmployeeFullName(employee.getEmployeeFullName());
                employeeSalaryModel.setEmployee(employeeModel);
                employeeSalaryModels.add(employeeSalaryModel);
            }
        }else {
            return null;
        }
        return employeeSalaryModels;
    }
//    UPDATE
    public EmployeeSalaryModel updateEmpSal (Long id , EmployeeSalaryModel empSalModel){
        EmployeeSalary employeeSalary = empSalRepository.findById(id).get();
        employeeSalary.setBasic(empSalModel.getBasic());
        employeeSalary.setDearnessAllowance(empSalModel.getDearnessAllowance());
        employeeSalary.setHouseRentAllowance(empSalModel.getHouseRentAllowance());
        employeeSalary.setOtherAdditions(empSalModel.getOtherAdditions());

        Optional<Employee> employeeOptional =employeeRepository.findById(empSalModel.getEmployeeId());
            employeeSalary.setEmployee(employeeOptional.get());

            empSalModel.setId(employeeSalary.getId());
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setId(employeeSalary.getEmployee().getId());
            employeeModel.setEmployeeFullName(employeeSalary.getEmployee().getEmployeeFullName());
            empSalModel.setEmployee(employeeModel);

            int totalPay = employeeSalary.getBasic() + employeeSalary.getDearnessAllowance() + employeeSalary.getHouseRentAllowance() + employeeSalary.getOtherAdditions();
            employeeSalary.setTotalPay(totalPay);
            empSalRepository.save(employeeSalary);

            empSalModel.setTotalPay(employeeSalary.getTotalPay());
            return empSalModel;
    }
//    DELETE
    public void deleteEmployeeSalary (Long id)throws Exception{
        Optional<EmployeeSalary> employeeSalary = empSalRepository.findById(id);
        if (employeeSalary.isPresent()){
            empSalRepository.deleteById(id);
        }else {
            throw new Exception("Invalid id");
        }

    }
}
