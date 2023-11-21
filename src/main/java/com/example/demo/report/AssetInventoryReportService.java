package com.example.demo.report;

import com.example.demo.dto.AssetInventoryReportDto;
import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetAssign;
import com.example.demo.entity.AssetType;
import com.example.demo.entity.Branch;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class AssetInventoryReportService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<AssetInventoryReportDto> assetInventoryReport(String branch , String type , Boolean assetAssign){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AssetInventoryReportDto> query = criteriaBuilder.createQuery(AssetInventoryReportDto.class);
        Root<Asset> assetRoot = query.from(Asset.class);
        Join <Asset , Branch> branchJoin = assetRoot.join("branch",JoinType.LEFT);
        Join <Asset , AssetType> assetTypeJoin = assetRoot.join("assetType",JoinType.LEFT);
        query.multiselect(
                branchJoin.get("branchName"),
                assetTypeJoin.get("type"),
                assetRoot.get("serialNo"),
                assetRoot.get("modelName"),
                assetRoot.get("manufacturingDate"),
                assetRoot.get("specification"),
                assetRoot.get("addedDate")
        );
        List<Predicate> predicates = new ArrayList<>();

        if (branch != null && !branch.isEmpty()){
            predicates.add(criteriaBuilder.equal(branchJoin.get("branchName") ,branch));
        }
        if (type != null && !type.isEmpty()){
            predicates.add(criteriaBuilder.equal(assetRoot.get("type") , type));
        }
        if (!predicates.isEmpty()) {

            query.where(predicates.toArray(new Predicate[0]));
        }
        TypedQuery<AssetInventoryReportDto> typedQuery = entityManager.createQuery(query);
        List<AssetInventoryReportDto> results = typedQuery.getResultList();

        return results;
    }
    public byte[] generateAssetInventoryReportPdf(){
        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document , outputStream);
            document.open();
            document.add(new Paragraph("Asset Inventory Report"));
            document.close();
            return outputStream.toByteArray();
        }catch (Exception exception){
            throw  new RuntimeException(exception.getMessage());
        }
    }
}
