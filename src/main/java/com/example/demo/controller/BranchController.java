package com.example.demo.controller;

import com.example.demo.entity.Branch;
import com.example.demo.model.BaseResponse;
import com.example.demo.model.BranchModel;
import com.example.demo.model.CompanyModel;
import com.example.demo.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BranchController {
    @Autowired
    BranchService branchService;

    @PostMapping(value = "/branch")
    public BaseResponse create (@RequestBody BranchModel branchModel) throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        try {
            BranchModel data = branchService.createBranch(branchModel);
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
    @GetMapping("/branch/{id}")
    public BaseResponse getBranch (@PathVariable Long id) {
        BranchModel data = branchService.getBranch(id);
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
    @GetMapping(value = "/branch")
    public BaseResponse readBranch(){
        BaseResponse baseResponse = new BaseResponse();

        List<BranchModel> data = branchService.getBranches();
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
    @PutMapping(value = "/branch/{id}")
    public BaseResponse updateBranch(@PathVariable (value = "id")Long id , @RequestBody BranchModel companyDetails){

        BranchModel data = branchService.updateBranch(id , companyDetails);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(data);
        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        return baseResponse;
    }
    @DeleteMapping(value = "/branch/{id}")
    public BaseResponse deleteBranch(@PathVariable(value = "id") Long id){
        BaseResponse baseResponse = new BaseResponse();

        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        branchService.deleteBranchId(id);
        return baseResponse;

    }
}
