package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.model.*;
import com.example.demo.repository.DeductionRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.SalaryRepository;
import jakarta.persistence.Basic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {
    @Autowired
    SalaryRepository salaryRepository;
    @Autowired
    DeductionRepository deductionRepository;
    @Autowired
    EmployeeRepository employeeRepository;

//    CREATE
    public SalaryModel createSalary (SalaryModel salaryModel )throws Exception{
        Salary salary = new Salary();
        salary.setId(salaryModel.getId());
        salary.setMonth(salaryModel.getMonth());
        salary.setBasic(salaryModel.getBasic());
        salary.setYear(salaryModel.getYear());
        salary.setDearnessAllowance(salaryModel.getDearnessAllowance());
        salary.setHouseRentAllowance(salaryModel.getHouseRentAllowance());
        salary.setOtherAdditions(salaryModel.getOtherAdditions());

        Optional<Deduction> deductionOptional = deductionRepository.findById(salaryModel.getDeductionId());
        Optional<Employee> employeeOptional =  employeeRepository.findById(salaryModel.getEmployeeId());
        if (deductionOptional.isPresent()|| employeeOptional.isPresent()){
            salary.setDeduction(deductionOptional.get());
            salary.setEmployee(employeeOptional.get());
            salaryRepository.save(salary);

            DeductionModel deductionModel = new DeductionModel();
            int employeeStateFund = deductionModel.getEmployeeStateFund();
            int providentFund = deductionModel.getProvidentFund();

            int totalSalary = salary.getBasic() + salary.getDearnessAllowance() + salary.getHouseRentAllowance() + salary.getOtherAdditions();
            int esiDeduction = (totalSalary * employeeStateFund)/100;
            int pfDeduction = (totalSalary * providentFund)/100;
            int totalDeduction = esiDeduction + pfDeduction;
            int netSalary = totalSalary - totalDeduction;
            salary.setNetSalary(netSalary);
            salaryRepository.save(salary);

        }else{
            throw new Exception("Invalid deduction / employee id");
        }
        salaryModel.setId(salary.getId());
        salaryModel.setNetSalary(salary.getNetSalary());
        return salaryModel;

    }

//    READ
    public SalaryModel readSalaryId (long id){
        Optional<Salary> salaryOptional = salaryRepository.findById(id);
        SalaryModel salaryModel = new SalaryModel();
        if (salaryOptional.isPresent()){
            Salary salary = salaryOptional.get();
            salaryModel.setId(salary.getId());
            salaryModel.setMonth(salary.getMonth());
            salaryModel.setYear(salary.getYear());
            salaryModel.setBasic(salary.getBasic());
            salaryModel.setDearnessAllowance(salary.getDearnessAllowance());
            salaryModel.setHouseRentAllowance(salary.getHouseRentAllowance());
            salaryModel.setOtherAdditions(salary.getOtherAdditions());
            salaryModel.setNetSalary(salary.getNetSalary());
            salaryModel.setEmployeeId(salary.getEmployee().getId());
            salaryModel.setDeductionId(salary.getDeduction().getId());


            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setId(salary.getEmployee().getId());
            salaryModel.setEmployee(employeeModel);

            DeductionModel deductionModel = new DeductionModel();
            deductionModel.setId(salary.getDeduction().getId());
            salaryModel.setDeduction(deductionModel);
        }else {
            return null;
        }
        return salaryModel;
    }

    public List<SalaryModel> getSalary (){
        List<SalaryModel> salaryModels = new ArrayList<>();
        List<Salary> salaryList = salaryRepository.findAll();
        if (!salaryList.isEmpty()){
            for (Salary salary : salaryList){
                SalaryModel salaryModel = new SalaryModel();
                salaryModel.setId(salary.getId());
                salaryModel.setMonth(salary.getMonth());
                salaryModel.setYear(salary.getYear());
                salaryModel.setBasic(salary.getBasic());
                salaryModel.setDearnessAllowance(salary.getDearnessAllowance());
                salaryModel.setHouseRentAllowance(salary.getHouseRentAllowance());
                salaryModel.setOtherAdditions(salary.getOtherAdditions());
                salaryModel.setNetSalary(salary.getNetSalary());

                salaryModel.setEmployeeId(salary.getEmployee().getId());
                salaryModel.setDeductionId(salary.getDeduction().getId());

                Employee employee = salary.getEmployee();
                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setId(employee.getId());
                salaryModel.setEmployee(employeeModel);
                salaryModels.add(salaryModel);

                Deduction deduction = salary.getDeduction();
                DeductionModel deductionModel = new DeductionModel();
                deductionModel.setId(deduction.getId());
                salaryModel.setDeduction(deductionModel);
                salaryModels.add(salaryModel);
            }
        }else {
            return null;
        }
        return salaryModels;
    }

//    READ SEARCH
      public SearchModel getSearch (long id){
       SearchModel searchModel = new SearchModel();
//       Optional<Salary> salaryOptional = salaryRepository.findById(id);
////       Employee emplo = salaryRepository.findByEmployeeId(salaryOptional.get().getId());
////       if (salaryOptional.isPresent()|| employeeOptional.isPresent()){
//           Salary salary = salaryOptional.get();
//           searchModel.setId(salary.getId());
//           searchModel.setMonth(salary.getMonth());
//           searchModel.setYear(salary.getYear());
//           searchModel.setEmployeeId(salary.getEmployee().getId());
//           searchModel.setNetSalary(salary.getNetSalary());
//       }else {
//           return  null;
//       }
       return searchModel;
      }

//    UPDATE
    public SalaryModel updateSalary (Long id , SalaryModel salaryModel){
        Salary salary = salaryRepository.findById(id).get();
        salary.setId(salaryModel.getId());
        salary.setMonth(salaryModel.getMonth());
        salary.setYear(salaryModel.getYear());
        salary.setBasic(salaryModel.getBasic());
        salary.setDearnessAllowance(salaryModel.getDearnessAllowance());
        salary.setHouseRentAllowance(salaryModel.getHouseRentAllowance());
        salary.setOtherAdditions(salaryModel.getOtherAdditions());

        salaryModel.setNetSalary(salary.getNetSalary());

        DeductionModel deductionModel = new DeductionModel();
        int employeeStateFund = deductionModel.getEmployeeStateFund();
        int providentFund = deductionModel.getProvidentFund();

        int totalSalary = salary.getBasic() + salary.getDearnessAllowance() + salary.getHouseRentAllowance() + salary.getOtherAdditions();
        int esiDeduction = (totalSalary * employeeStateFund)/100;
        int pfDeduction = (totalSalary * providentFund)/100;
        int totalDeduction = esiDeduction + pfDeduction;
        int netSalary = totalSalary - totalDeduction;
        salary.setNetSalary(netSalary);
        salaryRepository.save(salary);


        Optional<Employee> employee = employeeRepository.findById(salary.getEmployee().getId());
        salary.setEmployee(employee.get());
        salaryRepository.save(salary);

        Optional<Deduction> deduction = deductionRepository.findById(salary.getDeduction().getId());
        salary.setDeduction(deduction.get());
        salaryRepository.save(salary);

        return salaryModel;
    }
//    DELETE
    public void deleteSalaryId (Long id)throws  Exception{
        Optional<Salary> salary = salaryRepository.findById(id);
        if (salary.isPresent()){
            salaryRepository.deleteById(id);
        }else {
            throw new Exception();
        }
    }
}
