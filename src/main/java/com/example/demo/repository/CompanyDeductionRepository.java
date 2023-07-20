package com.example.demo.repository;

import com.example.demo.entity.CompanyDeduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDeductionRepository extends JpaRepository<CompanyDeduction, Long> {
}
