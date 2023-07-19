package com.example.demo.repository;

import com.example.demo.entity.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationRepository extends JpaRepository<Designation , Long> {
}
