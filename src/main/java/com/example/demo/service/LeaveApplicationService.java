package com.example.demo.service;

import com.example.demo.entity.Branch;
import com.example.demo.entity.Employee;
import com.example.demo.entity.LeaveApplication;
import com.example.demo.model.BranchModel;
import com.example.demo.model.EmployeeModel;
import com.example.demo.model.LeaveApplicationModel;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.LeaveApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveApplicationService {

    @Autowired
    LeaveApplicationRepository leaveApplicationRepository;

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    BranchRepository branchRepository;

//    CREATE
    public LeaveApplicationModel createLeave(LeaveApplicationModel leaveApplicationModel) throws Exception{

        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setFromDate(leaveApplicationModel.getFromDate());
        leaveApplication.setToDate(leaveApplicationModel.getToDate());
        leaveApplication.setReason(leaveApplicationModel.getReason());
        leaveApplication.setStatus("In-Progress");
        leaveApplication.setAppliedDate(leaveApplicationModel.getAppliedDate());

        Date fromDate = leaveApplication.getFromDate();
        Date toDate = leaveApplication.getToDate();

//        LocalDate localFromDate = fromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate localToDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate localFromDate = fromDate.toLocalDate();
        LocalDate localToDate = toDate.toLocalDate();
        int numberOfLeave = (int) (ChronoUnit.DAYS.between(localFromDate, localToDate) + 1);

        leaveApplication.setNumberOfLeave(numberOfLeave);


        Optional<Employee> employee = employeeRepository.findById(leaveApplicationModel.getEmployeeId());
        Optional<Branch>branch = branchRepository.findById(leaveApplicationModel.getBranchId());
        if (employee.isPresent() && branch.isPresent()){
            leaveApplication.setEmployee(employee.get());
            leaveApplication.setBranch(branch.get());
            leaveApplicationRepository.save(leaveApplication);

            leaveApplicationModel.setId(leaveApplication.getId());
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setId(leaveApplication.getEmployee().getId());
            employeeModel.setEmployeeFullName(leaveApplication.getEmployee().getEmployeeFullName());
            employeeModel.setMobileNumber(leaveApplication.getEmployee().getMobileNumber());
            employeeModel.setEmail(leaveApplication.getEmployee().getEmail());
            employeeModel.setBloodGroup(leaveApplication.getEmployee().getBloodGroup());
            leaveApplicationModel.setEmployee(employeeModel);

            BranchModel branchModel = new BranchModel();
            branchModel.setBranchName(leaveApplication.getBranch().getBranchName());
            branchModel.setBranchCode(leaveApplication.getBranch().getBranchCode());
            leaveApplicationModel.setBranchModel(branchModel);

        } else {
            throw  new Exception("Invalid employee id or branch id");
        }
        return leaveApplicationModel;
    }

//    READ
    public LeaveApplicationModel getLeave (Long id){
        LeaveApplicationModel leaveApplicationModel = new LeaveApplicationModel();
        Optional<LeaveApplication> leaveApplicationOptional = leaveApplicationRepository.findById(id);
        if (leaveApplicationOptional.isPresent()){
            LeaveApplication leaveApplication = leaveApplicationOptional.get();

            leaveApplicationModel.setEmployeeId(leaveApplication.getEmployee().getId());
            leaveApplicationModel.setId(leaveApplication.getId());
            leaveApplicationModel.setFromDate(leaveApplication.getFromDate());
            leaveApplicationModel.setToDate(leaveApplication.getToDate());
            leaveApplicationModel.setReason(leaveApplication.getReason());
            leaveApplicationModel.setStatus(leaveApplication.getStatus());
            leaveApplicationModel.setAppliedDate(leaveApplication.getAppliedDate());
            leaveApplicationModel.setActionDate(leaveApplication.getActionDate());
            leaveApplicationModel.setActionRemarks(leaveApplicationModel.getActionRemarks());

            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setEmployeeFullName(leaveApplication.getEmployee().getEmployeeFullName());
            employeeModel.setMobileNumber(leaveApplication.getEmployee().getMobileNumber());
            employeeModel.setEmail(leaveApplication.getEmployee().getEmail());
            employeeModel.setBloodGroup(leaveApplication.getEmployee().getBloodGroup());
            leaveApplicationModel.setEmployee(employeeModel);
        }else {
            return null;
        }
        return leaveApplicationModel;
    }

    public List<LeaveApplicationModel> getLeaves(){
        List<LeaveApplicationModel> leaveApplicationModels = new ArrayList<>();
        List<LeaveApplication> leaveApplications = leaveApplicationRepository.findAll();
        if (!leaveApplications.isEmpty()){
            for (LeaveApplication leaveApplication : leaveApplications){
                LeaveApplicationModel leaveApplicationModel = new LeaveApplicationModel();

                leaveApplicationModel.setEmployeeId(leaveApplication.getEmployee().getId());
                leaveApplicationModel.setId(leaveApplication.getId());
                leaveApplicationModel.setFromDate(leaveApplication.getFromDate());
                leaveApplicationModel.setToDate(leaveApplication.getToDate());
                leaveApplicationModel.setReason(leaveApplication.getReason());
                leaveApplicationModel.setStatus(leaveApplication.getStatus());
                leaveApplicationModel.setAppliedDate(leaveApplication.getAppliedDate());
                leaveApplicationModel.setActionDate(leaveApplication.getActionDate());
                leaveApplicationModel.setActionRemarks(leaveApplicationModel.getActionRemarks());

                Employee employee = leaveApplication.getEmployee();
                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setEmployeeFullName(employee.getEmployeeFullName());
                employeeModel.setMobileNumber(employee.getMobileNumber());
                employeeModel.setEmail(employee.getEmail());
                employeeModel.setBloodGroup(employee.getBloodGroup());
                leaveApplicationModel.setEmployee(employeeModel);
                leaveApplicationModels.add(leaveApplicationModel);
            }
        }else {
            return  null;
        }
        return leaveApplicationModels;
    }

    public LeaveApplicationModel getLeaveReport (LeaveApplicationModel leaveApplicationModel){
        LeaveApplication leaveApplication =leaveApplicationRepository.findByEmployeeIdAndBranchIdAndFromDateAndToDateAndStatus(leaveApplicationModel.getEmployeeId() ,
                leaveApplicationModel.getBranchId(), leaveApplicationModel.getFromDate() , leaveApplicationModel.getToDate() , leaveApplicationModel.getStatus());
            Optional<Employee> employeeOptional = employeeRepository.findById(leaveApplicationModel.getEmployeeId());
            Optional<Branch> branchOptional = branchRepository.findById(leaveApplicationModel.getBranchId());
            if (employeeOptional.isPresent() && branchOptional.isPresent()){
                Employee employee = employeeOptional.get();
                Branch branch = branchOptional.get();
                leaveApplicationModel.setEmployeeId(employee.getId());
                leaveApplicationModel.setBranchId(branch.getId());
                leaveApplicationModel.setFromDate(leaveApplication.getFromDate());
                leaveApplicationModel.setToDate(leaveApplication.getToDate());
                leaveApplicationModel.setStatus(leaveApplication.getStatus());
                leaveApplicationModel.setNumberOfLeave(leaveApplication.getNumberOfLeave());
            }else {
                return  null;
            }
            return leaveApplicationModel;
    }


//    UPDATE
    public LeaveApplicationModel updateLeave ( Long id , LeaveApplicationModel leaveApplicationModel) throws Exception{
        LeaveApplication leaveApplication = leaveApplicationRepository.findById(id).get();
        leaveApplication.setStatus(leaveApplicationModel.getStatus());
        leaveApplication.setActionDate(leaveApplicationModel.getActionDate());
        leaveApplication.setActionRemarks(leaveApplicationModel.getActionRemarks());

        Optional<Employee> employee = employeeRepository.findById(leaveApplicationModel.getEmployeeId());
        if (employee.isPresent()){
            leaveApplication.setEmployee(employee.get());
            leaveApplicationRepository.save(leaveApplication);

            leaveApplicationModel.setId(leaveApplication.getId());
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setId(leaveApplication.getEmployee().getId());
            employeeModel.setEmployeeFullName(leaveApplication.getEmployee().getEmployeeFullName());
            employeeModel.setMobileNumber(leaveApplication.getEmployee().getMobileNumber());
            employeeModel.setEmail(leaveApplication.getEmployee().getEmail());
            employeeModel.setBloodGroup(leaveApplication.getEmployee().getBloodGroup());
            leaveApplicationModel.setEmployee(employeeModel);
        } else {
            throw  new Exception("Invalid employee id");
        }
        return leaveApplicationModel;
    }

//    DELETE
    public  void deleteLeaveApplicationId (Long id) throws Exception{
        Optional<LeaveApplication> leaveApplication = leaveApplicationRepository.findById(id);
        if (leaveApplication.isPresent()){
            leaveApplicationRepository.deleteById(id);
        }else {
            throw  new Exception("Invalid id");
        }
    }
}
