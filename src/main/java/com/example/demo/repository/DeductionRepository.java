package com.example.demo.repository;

import com.example.demo.entity.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeductionRepository extends JpaRepository<Deduction, Long> {

    Deduction findByCompanyId(Long companyId);
}
