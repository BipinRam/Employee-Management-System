package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.LeaveApplicationModel;
import com.example.demo.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaveApplicationController {
    @Autowired
    LeaveApplicationService leaveApplicationService;

    @PostMapping(value = "/leave")
    public BaseResponse create (@RequestBody LeaveApplicationModel leaveApplicationModel) throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        try {
            LeaveApplicationModel data = leaveApplicationService.createLeave(leaveApplicationModel);
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
            return baseResponse;
        }
    }


    @GetMapping(value = "/leave/{id}")
    public BaseResponse getId (@PathVariable Long id){
        LeaveApplicationModel data = leaveApplicationService.getLeave(id);
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
    @GetMapping(value = "/leave")
    public BaseResponse get (){
        List< LeaveApplicationModel > data = leaveApplicationService.getLeaves();
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

    @PutMapping(value = "/leave/{id}")
    public BaseResponse update (@PathVariable (value = "id")Long id , @RequestBody LeaveApplicationModel leaveApplicationModel)throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        try {
            LeaveApplicationModel data = leaveApplicationService.updateLeave(id , leaveApplicationModel);
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
            return baseResponse;
        }
    }

    @DeleteMapping(value = "/leave/{id}")
    public BaseResponse delete (@PathVariable(value = "id")Long id ) throws Exception{
        BaseResponse baseResponse = new BaseResponse();
        try{
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
            leaveApplicationService.deleteLeaveApplicationId(id);
        }catch (Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }


}
