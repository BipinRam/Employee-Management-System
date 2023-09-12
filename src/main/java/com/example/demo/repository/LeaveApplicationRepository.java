package com.example.demo.repository;

import com.example.demo.entity.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication , Long> {

}
