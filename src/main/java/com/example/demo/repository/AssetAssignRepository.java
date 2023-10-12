package com.example.demo.repository;

import com.example.demo.entity.AssetAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface AssetAssignRepository extends JpaRepository<AssetAssign , Integer> {
    AssetAssign findByReturnDateAndAssetId(Date returnDate , int assetId);
}
