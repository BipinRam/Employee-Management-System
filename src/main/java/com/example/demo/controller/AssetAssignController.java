package com.example.demo.controller;

import com.example.demo.model.AssetAssignModel;
import com.example.demo.model.BaseResponse;
import com.example.demo.service.AssetAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AssetAssignController {
    @Autowired
    AssetAssignService assetAssignService;

    @PostMapping(value = "/assetAssign")
    public BaseResponse create (@RequestBody AssetAssignModel assetAssignModel) throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        try {
        AssetAssignModel data = assetAssignService.create(assetAssignModel);

            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        }catch (Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }


    @PutMapping(value = "/assetAssign/{id}")
    public BaseResponse update (@PathVariable(value = "id")int id , @RequestBody AssetAssignModel assetAssignModel)throws Exception{
        BaseResponse baseResponse = new BaseResponse();
      try {
          AssetAssignModel data = assetAssignService.update(id, assetAssignModel);
          baseResponse.setData(data);
          baseResponse.setMessage("Success");
          baseResponse.setCode(HttpStatus.OK);
      }catch (Exception exception){
          baseResponse.setMessage("Failure");
          baseResponse.setCode(HttpStatus.BAD_REQUEST);
      }
      return baseResponse;
    }
    @DeleteMapping(value = "/assetAssign/{id}")
    public BaseResponse delete (@PathVariable(value = "id") int id){
        BaseResponse baseResponse = new BaseResponse();
        try {
            assetAssignService.delete(id);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        }catch (Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }
}
