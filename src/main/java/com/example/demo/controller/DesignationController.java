package com.example.demo.controller;

import com.example.demo.entity.Designation;
import com.example.demo.model.BaseResponse;
import com.example.demo.model.DepartmentModel;
import com.example.demo.model.DesignationModel;
import com.example.demo.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DesignationController {
    @Autowired
    DesignationService designationService;

    @PostMapping(value = "/designation")
    public BaseResponse create (@RequestBody DesignationModel designationModel) throws  Exception{
        BaseResponse baseResponse = new BaseResponse();
        try {
            DesignationModel data = designationService.createDesignation(designationModel);
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
    @GetMapping(value = "/designation/{id}")
    public BaseResponse read (@PathVariable long id){
        BaseResponse baseResponse = new BaseResponse();
        DesignationModel data = designationService.readDesignation(id);
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

    @GetMapping(value = "designation")
    public BaseResponse reads (){
        BaseResponse baseResponse = new BaseResponse();
        List<DesignationModel> data = designationService.readDesignations();
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
    @PutMapping(value = "/designation/{id}")
    public BaseResponse update (@PathVariable(value = "id") long id , @RequestBody DesignationModel designationModel) {
        DesignationModel data = designationService.updateDesignation(id, designationModel);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(data);
        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        return baseResponse;
    }
    @DeleteMapping(value = "/designation/{id}")
    public BaseResponse delete(@PathVariable(value = "id") long id){
        BaseResponse baseResponse = new BaseResponse();
        try{
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
            designationService.deleteDesignation(id);
        }catch (Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }

        return baseResponse;
    }
}
