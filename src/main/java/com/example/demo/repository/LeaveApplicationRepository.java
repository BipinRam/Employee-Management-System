package com.example.demo.repository;

import com.example.demo.entity.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.sql.Date;


@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication , Long>, JpaSpecificationExecutor<LeaveApplication> {
    LeaveApplication findByEmployeeIdAndBranchIdAndFromDateAndToDateAndStatus (Long employeeId , Long branchId , Date fromDate , Date toDate , String status);
}
