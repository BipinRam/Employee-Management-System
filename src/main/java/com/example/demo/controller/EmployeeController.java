package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.model.BaseResponse;
import com.example.demo.model.EmployeeModel;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService empService;

   @PostMapping(value = "/employee")
    public BaseResponse create (@RequestBody EmployeeModel employeeModel) throws Exception{
       BaseResponse baseResponse = new BaseResponse();
       try {
           EmployeeModel data = empService.createEmployee(employeeModel);
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

   @GetMapping(value = "/employee/{id}")
    public BaseResponse get (@PathVariable (value = "id") Long id){
       EmployeeModel data = empService.getEmployeeId(id);
       BaseResponse baseResponse = new BaseResponse();
       if (data != null){
           baseResponse.setData(data);
           baseResponse.setMessage("Success");
           baseResponse.setCode(HttpStatus.OK);
       }else {
           baseResponse.setMessage("Failure");
           baseResponse.setCode(HttpStatus.BAD_REQUEST);
       }
       return baseResponse;
   }

   @GetMapping(value = "/employee")
    public BaseResponse getAll (){
       List<EmployeeModel> data = empService.getEmployee();
       BaseResponse baseResponse = new BaseResponse();
       if (data != null){
           baseResponse.setData(data);
           baseResponse.setMessage("Success");
           baseResponse.setCode(HttpStatus.OK);
       }else {
           baseResponse.setMessage("Failure");
           baseResponse.setCode(HttpStatus.BAD_REQUEST);
       }
       return baseResponse;
   }
   @PutMapping(value = "/employee/{id}")
    public BaseResponse update (@PathVariable(value = "id")Long id , @RequestBody EmployeeModel employeeModel)throws Exception{

       BaseResponse baseResponse = new BaseResponse();
       try{
           EmployeeModel data = empService.updateEmployee(id , employeeModel);
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
   @DeleteMapping(value = "/employee/{id}")
   public BaseResponse delete(@PathVariable(value = "id") long id)throws Exception{
       BaseResponse baseResponse = new BaseResponse();
       try {
           empService.deleteEmployeeId(id);
           baseResponse.setMessage("Success");
           baseResponse.setCode(HttpStatus.OK);
       }catch (Exception exception){
           baseResponse.setMessage(exception.getMessage());
           baseResponse.setCode(HttpStatus.BAD_REQUEST);
       }
       return  baseResponse;
   }

}


