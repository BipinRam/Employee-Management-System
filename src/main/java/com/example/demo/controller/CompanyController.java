package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.model.BaseResponse;
import com.example.demo.model.CompanyModel;
import com.example.demo.service.CompanyService;
import jakarta.validation.constraints.Null;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CompanyController {
    @Autowired
    CompanyService comService;

    @PostMapping(value = "/company")
    public BaseResponse createCompany (@RequestBody CompanyModel com){
        BaseResponse baseResponse = new BaseResponse();
        CompanyModel data = comService.createCompany(com);
        baseResponse.setData(data);
        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        return baseResponse;

    }
    @GetMapping("/company/{id}")
    public BaseResponse getCompanyById(@PathVariable Long id) {
        CompanyModel data = comService.getCompanyId(id);
        BaseResponse baseResponse = new BaseResponse();
        if (data != null){
            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        }else{
            baseResponse.setMessage("Wrong id");
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }

        return baseResponse;
    }
    @GetMapping(value = "/company")
    public BaseResponse readCompany(){
        BaseResponse baseResponse = new BaseResponse();

            List<CompanyModel> data = comService.getCompanies();
        if (data != null){
            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);

        }else{
            baseResponse.setData(null);
            baseResponse.setMessage("Failure");
            baseResponse.setCode(HttpStatus.BAD_REQUEST);

        }
        return baseResponse;
    }
    @PutMapping(value = "/company/{id}")
    public BaseResponse updateCompany(@PathVariable (value = "id")Long id , @RequestBody CompanyModel companyDetails){

        CompanyModel data = comService.updateCompany(id , companyDetails);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(data);
        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        return baseResponse;
    }
    @DeleteMapping(value = "/company/{id}")
    public void deleteCompany(@PathVariable(value = "id") Long id){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        comService.deleteCompanyId(id);

    }
}

