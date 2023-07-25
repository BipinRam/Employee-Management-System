package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.DeductionModel;
import com.example.demo.service.DeductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeductionController {
    @Autowired
    DeductionService deductionService;

    @PostMapping(value = "/deduction")
    public BaseResponse create (@RequestBody DeductionModel companyDeductionModel)throws Exception{
        BaseResponse baseResponse = new BaseResponse();
        try{
            DeductionModel data = deductionService.createCompanyDeduction(companyDeductionModel);
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

}
