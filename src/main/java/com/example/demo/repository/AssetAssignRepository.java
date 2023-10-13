package com.example.demo.repository;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface AssetAssignRepository extends JpaRepository<AssetAssign , Integer> {
    List<AssetAssign> findByAssetId(int assetId);
}
