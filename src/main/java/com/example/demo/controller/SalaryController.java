package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.SalaryModel;
import com.example.demo.model.SearchModel;
import com.example.demo.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @PostMapping(value = "/salary")
    public BaseResponse create(@RequestBody SalaryModel salaryModel) throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        try {
            SalaryModel data = salaryService.createSalary(salaryModel);
            if (data != null) {
                baseResponse.setData(data);
                baseResponse.setMessage("Success");
                baseResponse.setCode(HttpStatus.OK);
            } else {
                baseResponse.setMessage("Failure");
                baseResponse.setCode(HttpStatus.BAD_REQUEST);
            }
            return baseResponse;
        } catch (Exception exception) {
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }

    @GetMapping(value = "/salary/{id}")
    public BaseResponse get  (@PathVariable long id){
        SalaryModel data = salaryService.readSalaryId(id);
        BaseResponse baseResponse = new BaseResponse();
        if (data != null){
            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        }else {
            baseResponse.setMessage("Wrong id");
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }

        return baseResponse;
    }
    @GetMapping(value = "/salary")
    public BaseResponse getAll (){
        List<SalaryModel> data = salaryService.getSalary();
        BaseResponse baseResponse = new BaseResponse();
        if (data != null){
            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        }else{
            baseResponse.setMessage("Failure");
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }

    @GetMapping(value = "/search")
    public BaseResponse getSearch  (@PathVariable long id){
        SearchModel data = salaryService.getSearch(id);
        BaseResponse baseResponse = new BaseResponse();
        if (data != null){
            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        }else {
            baseResponse.setMessage("Wrong id");
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }

        return baseResponse;
    }

    @PutMapping(value = "/salary/{id}")
    public BaseResponse update (@PathVariable (value = "id") long id , @RequestBody SalaryModel salaryModel){
        SalaryModel data = salaryService.updateSalary(id , salaryModel);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Success");
        baseResponse.setData(data);
        baseResponse.setCode(HttpStatus.OK);

        return baseResponse;
    }

    @DeleteMapping(value = "/salary/{id}")
    public BaseResponse delete (@PathVariable(value = "id")long id){
        BaseResponse baseResponse = new BaseResponse();
        try {
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
            salaryService.deleteSalaryId(id);
        }catch(Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
      return baseResponse;
    }
}
