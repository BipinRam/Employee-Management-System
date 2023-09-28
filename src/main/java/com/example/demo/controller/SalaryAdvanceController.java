package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.SalaryAdvanceModel;
import com.example.demo.service.SalaryAdvanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SalaryAdvanceController {
    @Autowired
    SalaryAdvanceService salaryAdvanceService;

    @PostMapping(value = "/salaryAdvance")
    public BaseResponse create (@RequestBody SalaryAdvanceModel salaryAdvanceModel)throws Exception{
        BaseResponse baseResponse = new BaseResponse();
        try {
            SalaryAdvanceModel data = salaryAdvanceService.createAdvSalary(salaryAdvanceModel);
            if (data != null){
                baseResponse.setData(data);
                baseResponse.setMessage("Success");
                baseResponse.setCode(HttpStatus.OK);
            }else {
                baseResponse.setMessage("Failure");
                baseResponse.setCode(HttpStatus.BAD_REQUEST);
            }
            return baseResponse;
        }catch (Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }

        return baseResponse;
    }

    @PutMapping(value = "/salaryAdvance/{id}")
    public BaseResponse update (@PathVariable Long id , @RequestBody SalaryAdvanceModel salaryAdvanceModel)throws Exception{
        BaseResponse baseResponse = new BaseResponse();
        try {
            SalaryAdvanceModel data = salaryAdvanceService.updateAdvSalary(id , salaryAdvanceModel);
            if (data != null){
                baseResponse.setData(data);
                baseResponse.setMessage("Success");
                baseResponse.setCode(HttpStatus.OK);
            }else {
                baseResponse.setMessage("Failure");
                baseResponse.setCode(HttpStatus.BAD_REQUEST);
            }
            return baseResponse;
        }catch (Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }

        return baseResponse;
    }
}
