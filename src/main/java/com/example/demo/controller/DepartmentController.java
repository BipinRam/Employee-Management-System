package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.model.BaseResponse;
import com.example.demo.model.BranchModel;
import com.example.demo.model.DepartmentModel;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService depService;


    @PostMapping(value = "/department")
    public BaseResponse create (@RequestBody DepartmentModel departmentModel)throws Exception{
        BaseResponse baseResponse = new BaseResponse();
        try {
            DepartmentModel data = depService.createDepartment(departmentModel);
            if (data != null) {
                baseResponse.setData(data);
                baseResponse.setMessage("Success");
                baseResponse.setCode(HttpStatus.OK);
            } else {
                baseResponse.setMessage("Failure");
                baseResponse.setCode(HttpStatus.BAD_REQUEST);
            }

            return baseResponse;
        }catch (Exception e){
            baseResponse.setMessage(e.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);

        }
        return baseResponse;
    }
    @GetMapping(value = "/department/{id}")
    public BaseResponse read (@PathVariable long id){
        DepartmentModel data = depService.readDepartment(id);
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
    @GetMapping(value = "/department")
    public BaseResponse read (){
        BaseResponse baseResponse = new BaseResponse();

        List<DepartmentModel> data = depService.readBranches();
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
    @PutMapping(value = "/department/{id}")
    public BaseResponse update (@PathVariable(value = "id") long id , @RequestBody DepartmentModel departmentModel){
        DepartmentModel data = depService.updateDepartment(id , departmentModel);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(data);
        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        return baseResponse;
    }
    @DeleteMapping(value = "/department/{id}")
    public BaseResponse delete(@PathVariable(value = "id") long id)throws Exception{
        BaseResponse baseResponse = new BaseResponse();
        try {
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
            depService.deleteDepartment(id);
        }catch (Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }

        return  baseResponse;
    }
}


