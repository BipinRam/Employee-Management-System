package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.CompanyDeductionModel;
import com.example.demo.service.CompanyDeductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyDeductionController {
    @Autowired
    CompanyDeductionService companyDeductionService;

    @PostMapping(value = "/companyDeduction")
    public BaseResponse create (@RequestBody CompanyDeductionModel companyDeductionModel)throws Exception{
        BaseResponse baseResponse = new BaseResponse();
        try{
            CompanyDeductionModel data = companyDeductionService.createCompanyDeduction(companyDeductionModel);
            if (data != null){
                baseResponse.setData(data);
                baseResponse.setMessage("Success");
                baseResponse.setCode(HttpStatus.OK);
            }else {
                baseResponse.setMessage("Failure");
                baseResponse.setCode(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }


        return baseResponse;
    }
    @GetMapping(value = "/companyDeduction/{id}")
    public BaseResponse read (@PathVariable Long id){
        BaseResponse baseResponse = new BaseResponse();
        CompanyDeductionModel data = companyDeductionService.readCompanyDeduction(id);
        if (data != null){
            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        }else {
            baseResponse.setMessage("Failure");
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return  baseResponse;
    }

    @GetMapping(value = "/companyDeduction")
    public BaseResponse reads (){
        BaseResponse baseResponse = new BaseResponse();
        List<CompanyDeductionModel> data = companyDeductionService.readCompanyDeductions();
        if (data != null){
            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        }else  {

            baseResponse.setMessage("Failure");
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }
    @PutMapping(value = "/companyDeduction/{id}")
    public BaseResponse update (@PathVariable(value = "id")long id , @RequestBody CompanyDeductionModel companyDeductionModel){
        CompanyDeductionModel data = companyDeductionService.updateCompanyDeduction(id , companyDeductionModel);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(data);
        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        return baseResponse;
    }
    @DeleteMapping(value = "/companyDeduction/{id}")
    public BaseResponse delete (@PathVariable(value = "id")long id){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        companyDeductionService.deleteCompanyDeduction(id);
        return baseResponse;
    }

}
