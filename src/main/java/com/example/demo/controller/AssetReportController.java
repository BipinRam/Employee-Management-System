package com.example.demo.controller;

import com.example.demo.dto.AssetAssignReportDto;
import com.example.demo.dto.AssetInventoryReportDto;
import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetAssign;
import com.example.demo.model.BaseResponse;
import com.example.demo.report.AssetAssignReportService;
import com.example.demo.report.AssetInventoryReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class AssetReportController {

    @Autowired
    AssetInventoryReportService assetInventoryReport;

    @GetMapping( "/assetInventory")
    public BaseResponse assetInventoryReport(
            @RequestParam(required = false) String branch,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Boolean assigned){
        List<AssetInventoryReportDto> inventoryReportData = assetInventoryReport.assetInventoryReport(branch , type , assigned);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(inventoryReportData);
        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        return baseResponse;
    }

//    @GetMapping( "/assetInventory")
//    public BaseResponse assetInventoryReport(
//            @RequestParam(required = false) String branch,
//            @RequestParam(required = false) String assetType,
//            @RequestParam(required = false) Boolean assigned){
//        List<Asset> data = assetInventoryReport.assetInventoryReport(branch , assetType , assigned);
//        BaseResponse baseResponse = new BaseResponse();
//        baseResponse.setData(data);
//        baseResponse.setMessage("Success");
//        baseResponse.setCode(HttpStatus.OK);
//        return baseResponse;
//    }

    @Autowired
    AssetAssignReportService assetAssignReportService;

    @GetMapping("/assetAssign")
    public BaseResponse assetAssign(
            @RequestParam(required = false) String employee,
            @RequestParam(required = false) String branch ,
            @RequestParam(required = false) String assetType,
            @RequestParam(required = false) Date assignedDate,
            @RequestParam(required = false) Date returnedDate,
            @RequestParam(required = false) Boolean assetAssign
    ){
        List <AssetAssignReportDto> data = assetAssignReportService.assetAssignReport(employee , branch , assetType , assignedDate , returnedDate , assetAssign);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(data);
        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        return  baseResponse;
    }
}
