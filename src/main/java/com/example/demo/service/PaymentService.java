package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.model.*;
import com.example.demo.repository.DeductionRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSalaryRepository;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    DeductionRepository deductionRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeSalaryRepository empSalRepository;

//    CREATE
    public PaymentModel createPayment (PaymentModel paymentModel )throws Exception{
        Payment payment = new Payment();
        payment.setId(paymentModel.getId());
        payment.setMonth(paymentModel.getMonth());
        payment.setYear(paymentModel.getYear());

        Optional<Deduction> deductionOptional = deductionRepository.findById(paymentModel.getDeductionId());
        Optional<Employee> employeeOptional =  employeeRepository.findById(paymentModel.getEmployeeId());
        Optional<EmployeeSalary> empSalOptional = empSalRepository.findById(paymentModel.getEmployeeId());
        if (deductionOptional.isPresent() && employeeOptional.isPresent() && empSalOptional.isPresent()){
            payment.setDeduction(deductionOptional.get());
            payment.setEmployee(employeeOptional.get());
            payment.setEmployeeSalary(empSalOptional.get());

            paymentRepository.save(payment);

//            Deduction
            int employeeStateFund = payment.getDeduction().getEmployeeStateFund();
            int providentFund = payment.getDeduction().getProvidentFund();

//            EmployeeSalary
            int basic = payment.getEmployeeSalary().getBasic();
            int dearnessAllowance = payment.getEmployeeSalary().getDearnessAllowance();
            int houseRentAllowance =  payment.getEmployeeSalary().getHouseRentAllowance();
            int otherAdditions = payment.getEmployeeSalary().getOtherAdditions();

            int totalSalary = basic + dearnessAllowance + houseRentAllowance + otherAdditions;
            int esiDeduction = (totalSalary * employeeStateFund)/100;
            int pfDeduction = (totalSalary * providentFund)/100;
            int totalDeduction = esiDeduction + pfDeduction;
            int netPay = totalSalary - totalDeduction;
            payment.setNetPay(netPay);

        }else{
            throw new Exception("Invalid deduction / employee id");
        }
        paymentModel.setId(payment.getId());
        paymentModel.setNetPay(payment.getNetPay());
        return paymentModel;

    }

//    READ
    public PaymentModel readPaymentId (long id){
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        PaymentModel paymentModel = new PaymentModel();
        if (paymentOptional.isPresent()){
            Payment payment = paymentOptional.get();
            paymentModel.setId(payment.getId());
            paymentModel.setMonth(payment.getMonth());
            paymentModel.setYear(payment.getYear());
            paymentModel.setNetPay(payment.getNetPay());

            paymentModel.setEmployeeSalaryId(payment.getEmployeeSalary().getId());
            paymentModel.setEmployeeId(payment.getEmployee().getId());
            paymentModel.setDeductionId(payment.getDeduction().getId());


            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setId(payment.getEmployee().getId());
            employeeModel.setEmployeeFullName(payment.getEmployee().getEmployeeFullName());
            paymentModel.setEmployee(employeeModel);

            DeductionModel deductionModel = new DeductionModel();
            deductionModel.setId(payment.getDeduction().getId());
            deductionModel.setProvidentFund(payment.getDeduction().getProvidentFund());
            deductionModel.setEmployeeStateFund(payment.getDeduction().getEmployeeStateFund());
            paymentModel.setDeduction(deductionModel);

            EmployeeSalaryModel empSalModel = new EmployeeSalaryModel();
            empSalModel.setBasic(payment.getEmployeeSalary().getBasic());
            empSalModel.setDearnessAllowance(payment.getEmployeeSalary().getDearnessAllowance());
            paymentModel.setEmployeeSalaryModel(empSalModel);
        }else {
            return null;
        }
        return paymentModel;
    }

    public List<PaymentModel> getPayment (){
        List<PaymentModel> paymentModels = new ArrayList<>();
        List<Payment> paymentList = paymentRepository.findAll();
        if (!paymentList.isEmpty()){
            for (Payment payment : paymentList){
                PaymentModel paymentModel = new PaymentModel();
                paymentModel.setId(payment.getId());
                paymentModel.setMonth(payment.getMonth());
                paymentModel.setYear(payment.getYear());
                paymentModel.setNetPay(payment.getNetPay());

                paymentModel.setEmployeeId(payment.getEmployee().getId());
                paymentModel.setDeductionId(payment.getDeduction().getId());
                paymentModel.setEmployeeSalaryId(payment.getEmployeeSalary().getId());


                Employee employee = payment.getEmployee();
                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setId(employee.getId());
                paymentModel.setEmployee(employeeModel);

                Deduction deduction = payment.getDeduction();
                DeductionModel deductionModel = new DeductionModel();
                deductionModel.setId(deduction.getId());
                paymentModel.setDeduction(deductionModel);

                EmployeeSalaryModel empSalModel = new EmployeeSalaryModel();
                empSalModel.setBasic(payment.getEmployeeSalary().getBasic());
                empSalModel.setDearnessAllowance(payment.getEmployeeSalary().getDearnessAllowance());
                paymentModel.setEmployeeSalaryModel(empSalModel);

                paymentModels.add(paymentModel);
            }
        }else {
            return null;
        }
        return paymentModels;
    }

//    READ SEARCH
      public PaymentModel getSearch (PaymentModel paymentModel ){

        Payment payment = paymentRepository.findByEmployeeIdAndMonthAndYear(paymentModel.getEmployeeId(), paymentModel.getMonth(), paymentModel.getYear());
          Optional<Employee> employeeOptional = employeeRepository.findById(paymentModel.getEmployeeId());
        if (employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            paymentModel.setId(employee.getId());

            paymentModel.setMonth(payment.getMonth());
            paymentModel.setYear(payment.getYear());
            paymentModel.setNetPay(payment.getNetPay());

          }else {
              return null;
          }
          return paymentModel;
      }

//    UPDATE
    public PaymentModel updatePayment (Long id , PaymentModel paymentModel)throws Exception{
        Payment payment = paymentRepository.findById(id).get();
        payment.setId(paymentModel.getId());
        payment.setMonth(paymentModel.getMonth());
        payment.setYear(paymentModel.getYear());

        Optional<Deduction> deductionOptional = deductionRepository.findById(paymentModel.getDeductionId());
        Optional<Employee> employeeOptional =  employeeRepository.findById(paymentModel.getEmployeeId());
        Optional<EmployeeSalary> empSalOptional = empSalRepository.findById(paymentModel.getEmployeeId());
        if (deductionOptional.isPresent() && employeeOptional.isPresent() && empSalOptional.isPresent()){
            payment.setDeduction(deductionOptional.get());
            payment.setEmployee(employeeOptional.get());
            payment.setEmployeeSalary(empSalOptional.get());

            paymentRepository.save(payment);

//            Deduction
            int employeeStateFund = payment.getDeduction().getEmployeeStateFund();
            int providentFund = payment.getDeduction().getProvidentFund();

//            EmployeeSalary
            int basic = payment.getEmployeeSalary().getBasic();
            int dearnessAllowance = payment.getEmployeeSalary().getDearnessAllowance();
            int houseRentAllowance =  payment.getEmployeeSalary().getHouseRentAllowance();
            int otherAdditions = payment.getEmployeeSalary().getOtherAdditions();

            int totalSalary = basic + dearnessAllowance + houseRentAllowance + otherAdditions;
            int esiDeduction = (totalSalary * employeeStateFund)/100;
            int pfDeduction = (totalSalary * providentFund)/100;
            int totalDeduction = esiDeduction + pfDeduction;
            int netPay = totalSalary - totalDeduction;
            payment.setNetPay(netPay);


        }else{
            throw new Exception("Invalid deduction / employee id");
        }
        paymentModel.setId(payment.getId());
        paymentModel.setNetPay(payment.getNetPay());
        return paymentModel;

    }
//    DELETE
    public void deletePaymentId (Long id)throws  Exception{
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()){
            paymentRepository.deleteById(id);
        }else {
            throw new Exception();
        }
    }
}
