package com.example.demo.controller;

import com.example.demo.model.AssetModel;
import com.example.demo.model.BaseResponse;
import com.example.demo.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssetController {

    @Autowired
    AssetService assetService;

    @PostMapping(value = "/asset")
    public BaseResponse create (@RequestBody AssetModel assetModel)throws Exception{
        BaseResponse baseResponse = new BaseResponse();
        AssetModel data = assetService.create(assetModel);
        try{
            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        }catch (Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }
    @GetMapping(value = "/asset/{id}")
    public BaseResponse getId (@PathVariable(value = "id") int id){
        BaseResponse baseResponse = new BaseResponse();
        AssetModel data = assetService.getId(id);
        if (data != null){
            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        }else {
            baseResponse.setMessage("Invalid_id");
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }
    @GetMapping(value = "/asset")
    public BaseResponse getAll (){
        BaseResponse  baseResponse = new BaseResponse();
        List<AssetModel> data = assetService.getAll();
        baseResponse.setData(data);
        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        return baseResponse;
    }
    @PutMapping(value = "/asset/{id}")
    public BaseResponse update (@PathVariable(value = "id") int id , @RequestBody AssetModel assetModel)throws Exception{
        BaseResponse baseResponse = new BaseResponse();
        AssetModel data = assetService.update(id , assetModel);
        try{
            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        }catch (Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }
    @DeleteMapping(value = "/asset/{id}")
    public BaseResponse delete (@PathVariable(value = "id") int id )throws Exception{
        BaseResponse baseResponse = new BaseResponse();
        try{
            assetService.delete(id);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        }catch (Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }
}
