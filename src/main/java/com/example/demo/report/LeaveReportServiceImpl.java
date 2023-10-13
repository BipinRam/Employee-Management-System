package com.example.demo.report;

import com.example.demo.entity.LeaveApplication;
import com.example.demo.repository.LeaveApplicationRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveReportServiceImpl implements LeaveReportService {


    @Autowired
    LeaveApplicationRepository leaveApplicationRepository;

    @Override
    public List<LeaveApplication> listAllLeaveApplication(LeaveApplication leaveApplication){
        Specification<LeaveApplication> leaveApplicationSpecification= criteria(leaveApplication);

        List<LeaveApplication> leaveApplications = leaveApplicationRepository.findAll(leaveApplicationSpecification);
        return leaveApplications;
    }

    private Specification<LeaveApplication> criteria (LeaveApplication leaveApplication){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates=new ArrayList<>();
            if(leaveApplication.getStatus()!=null){
                predicates.add(criteriaBuilder.equal(root.get("status"),leaveApplication.getStatus()));
            }
            if (leaveApplication.getFromDate()!= null){
                predicates.add(criteriaBuilder.equal(root.get("fromDate") , leaveApplication.getFromDate()));
            }
            if (leaveApplication.getToDate()!= null){
                predicates.add(criteriaBuilder.equal(root.get("toDate") , leaveApplication.getToDate()));
            }
            if (leaveApplication.getEmployee()!= null){
                predicates.add(criteriaBuilder.equal(root.get("employee").get("id") , leaveApplication.getEmployee().getId()));
            }
            if (leaveApplication.getBranch()!= null){
                predicates.add(criteriaBuilder.equal(root.get("branch").get("id") , leaveApplication.getBranch().getId()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }

}
