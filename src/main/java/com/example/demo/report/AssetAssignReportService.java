package com.example.demo.report;

import com.example.demo.dto.AssetAssignReportDto;
import com.example.demo.dto.AssetInventoryReportDto;
import com.example.demo.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AssetAssignReportService {

    @PersistenceContext
    EntityManager entityManager;

    public List<AssetAssignReportDto> assetAssignReport (String employee , String branch , String assetType , Date assignedDate ,
                                                 Date returnedDate , Boolean assetAssign){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery <AssetAssignReportDto> assetAssignCriteriaQuery = criteriaBuilder.createQuery(AssetAssignReportDto.class);
        Root <AssetAssign> assetAssignRoot = assetAssignCriteriaQuery.from(AssetAssign.class);
        Join <AssetAssign , Employee> employeeJoin = assetAssignRoot.join("employee", JoinType.LEFT);
        Join<AssetAssign , Asset > assetAssignAssetJoin = assetAssignRoot.join("asset" , JoinType.LEFT);
        Join<Asset , Branch> branchJoin = assetAssignAssetJoin.join("branch", JoinType.LEFT);
        Join<Asset , AssetType> assetAssetTypeJoin = assetAssignAssetJoin.join("assetType" , JoinType.LEFT);

//        List<Selection<Object>> selectionList = new ArrayList<>();
//        selectionList.add(employeeJoin.get("employeeFullName").alias("employeeFullName"));
//        selectionList.add(branchJoin.get("branchName").alias("branchName"));
//        selectionList.add(assetAssetTypeJoin.get("type").alias("type"));
//        selectionList.add(assetAssignRoot.get("assignDate").alias("assignDate"));
//        selectionList.add(assetAssignRoot.get("returnDate").alias("returnDate"));

        assetAssignCriteriaQuery.multiselect(
                assetAssetTypeJoin.get("type").alias("type") ,
                assetAssignAssetJoin.get("id").alias("id"),
                assetAssignAssetJoin.get("serialNo"),
                employeeJoin.get("employeeFullName"),
                assetAssignRoot.get("assignDate"),
                assetAssignRoot.get("assignRemarks"),
                assetAssignRoot.get("returnRemarks")
        );
        List <Predicate> predicates = new ArrayList<>();
        if (employee != null) {
            predicates.add(criteriaBuilder.equal(employeeJoin.get("employeeFullName"), employee));
        }
        if (branch != null){
            predicates.add(criteriaBuilder.equal(branchJoin.get("branchName") , branch));
        }

        if (assetType != null) {
            predicates.add(criteriaBuilder.equal(assetAssetTypeJoin.get("assetType"), assetType));
        }
        if (assignedDate != null) {
            predicates.add(criteriaBuilder.equal(
                    assetAssignRoot.get("assignDate"), assignedDate));
        }
        if (returnedDate != null) {
            predicates.add(criteriaBuilder.equal(assetAssignRoot.get("employeeId") , returnedDate));
        }
        if (assetAssign!= null){
            if (assetAssign) {
                predicates.add(criteriaBuilder.isNotNull(assetAssignRoot.get("assignedTo")));
            } else {
                predicates.add(criteriaBuilder.isNull(assetAssignRoot.get("assignedTo")));
            }
        }

        assetAssignCriteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<AssetAssignReportDto> typedQuery = entityManager.createQuery(assetAssignCriteriaQuery);
        List<AssetAssignReportDto> results = typedQuery.getResultList();

        return results;
    }
}
