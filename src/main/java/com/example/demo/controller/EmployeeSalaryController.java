package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.DepartmentModel;
import com.example.demo.model.EmployeeSalaryModel;
import com.example.demo.service.EmployeeSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeSalaryController {
    @Autowired
    EmployeeSalaryService empSalService;

    @PostMapping(value = "/employeeSalary")
    public BaseResponse create(@RequestBody EmployeeSalaryModel empSalModel) throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        try {
            EmployeeSalaryModel data = empSalService.CreateEmpSal(empSalModel);
            if (data != null) {
                baseResponse.setData(data);
                baseResponse.setMessage("Success");
                baseResponse.setCode(HttpStatus.OK);
            } else {
                baseResponse.setMessage("Failure");
                baseResponse.setCode(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            baseResponse.setMessage(exception.getMessage());
        }
        return baseResponse;
    }

    @GetMapping(value = "/employeeSalary/{id}")
    public BaseResponse read(@PathVariable Long id) {
        BaseResponse baseResponse = new BaseResponse();
        EmployeeSalaryModel data = empSalService.readEmpSal(id);
        if (data != null) {
            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        } else {
            baseResponse.setMessage("Failure");
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }

    @GetMapping(value = "/employeeSalary")
    public BaseResponse reads() {
        BaseResponse baseResponse = new BaseResponse();
        List<EmployeeSalaryModel> data = empSalService.readEmpSalaries();
        if (data != null) {
            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        } else {
            baseResponse.setMessage("Failure");
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }

    @PutMapping(value = "/employeeSalary/{id}")
    public BaseResponse update(@PathVariable(value = "id") long id, @RequestBody EmployeeSalaryModel employeeSalaryModel) {
        EmployeeSalaryModel data = empSalService.updateEmpSal(id, employeeSalaryModel);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(data);
        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        return baseResponse;
    }
    @DeleteMapping(value = "/employeeSalary/{id}")
    public BaseResponse delete(@PathVariable(value = "id") long id)throws Exception{
        BaseResponse baseResponse = new BaseResponse();
        try {
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
            empSalService.deleteEmployeeSalary(id);
        }catch (Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return  baseResponse;
    }
}
