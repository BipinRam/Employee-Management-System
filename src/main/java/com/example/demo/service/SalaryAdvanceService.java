package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Payment;
import com.example.demo.entity.SalaryAdvance;
import com.example.demo.model.EmployeeModel;
import com.example.demo.model.SalaryAdvanceModel;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.SalaryAdvanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SalaryAdvanceService {
    @Autowired
    SalaryAdvanceRepository salaryAdvanceRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PaymentRepository paymentRepository;

//    CREATE
    public SalaryAdvanceModel createAdvSalary (SalaryAdvanceModel salaryAdvanceModel)throws Exception{
        SalaryAdvance salaryAdvance = new SalaryAdvance();
        salaryAdvance.setStatus("In-Progress");

        LocalDate currentDate = LocalDate.now();
        salaryAdvance.setAppliedDate(currentDate);

        Payment payment = paymentRepository.findByEmployeeId(salaryAdvanceModel.getEmployeeId());
        salaryAdvanceModel.setNetPay(payment.getNetPay());
        int netPay  = salaryAdvanceModel.getNetPay();

        double advanceSalary = netPay * 0.5;

        if (salaryAdvanceModel.getAdvanceAmount() <= advanceSalary){
            salaryAdvance.setAdvanceAmount(salaryAdvanceModel.getAdvanceAmount());
        }else {
            throw new Exception("Amount is greater than 50 percentage of net salary");
        }

        String currentMonth = String.valueOf(currentDate.getMonth());
        int currentYear = currentDate.getYear();

        String month = salaryAdvanceModel.getMonth();
        int year = salaryAdvanceModel.getYear();
        if ((currentYear == year && currentMonth == month)|| (currentYear == year && currentMonth == month +1 )||
                (currentYear == year + 1 && month == "january")){
            salaryAdvance.setMonth(salaryAdvanceModel.getMonth());
            salaryAdvance.setYear(salaryAdvanceModel.getYear());
        }else {
            throw new Exception("Advance salary already processed for input month and year");
        }

        Optional<Employee> employeeOptional = employeeRepository.findById(salaryAdvanceModel.getEmployeeId());
        if (employeeOptional.isPresent()){
            salaryAdvance.setEmployee(employeeOptional.get());
            salaryAdvanceRepository.save(salaryAdvance);

            salaryAdvanceModel.setId(salaryAdvance.getId());
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setId(salaryAdvance.getEmployee().getId());
            employeeModel.setEmployeeFullName(salaryAdvance.getEmployee().getEmployeeFullName());
            employeeModel.setMobileNumber(salaryAdvance.getEmployee().getMobileNumber());
            employeeModel.setEmail(salaryAdvance.getEmployee().getEmail());
            employeeModel.setBloodGroup(salaryAdvance.getEmployee().getBloodGroup());
        }else {
            throw new Exception("Invalid employee_id");
        }
        salaryAdvanceModel.setStatus(salaryAdvance.getStatus());
        salaryAdvanceModel.setAppliedDate(salaryAdvance.getAppliedDate());

        return salaryAdvanceModel;
    }

//    UPDATE
    public SalaryAdvanceModel updateAdvSalary (Long id ,SalaryAdvanceModel salaryAdvanceModel){
        SalaryAdvance salaryAdvance = salaryAdvanceRepository.findById(id).get();
            salaryAdvance.setStatus(salaryAdvanceModel.getStatus());
            salaryAdvance.setActionRemarks(salaryAdvanceModel.getActionRemarks());
            salaryAdvance.setActionDate(salaryAdvanceModel.getActionDate());
            salaryAdvanceRepository.save(salaryAdvance);

            salaryAdvanceModel.setMonth(salaryAdvance.getMonth());
            salaryAdvanceModel.setYear(salaryAdvance.getYear());
            salaryAdvanceModel.setAdvanceAmount(salaryAdvance.getAdvanceAmount());
            salaryAdvanceModel.setAppliedDate(salaryAdvance.getAppliedDate());
            salaryAdvanceModel.setEmployeeId(salaryAdvance.getId());

        return salaryAdvanceModel;

    }
}
