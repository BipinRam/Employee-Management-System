package com.example.demo.controller;

import com.example.demo.model.AssetTypeModel;
import com.example.demo.model.BaseResponse;
import com.example.demo.service.AssetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssetTypeController {

    @Autowired
    AssetTypeService assetTypeService;

    @PostMapping(value = "/assetType")
    public BaseResponse create (@RequestBody AssetTypeModel assetTypeModel){
        BaseResponse baseResponse = new BaseResponse();
        AssetTypeModel data = assetTypeService.create(assetTypeModel);
            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        return baseResponse;
    }
    @GetMapping(value = "/assetType/{id}")
    public BaseResponse getId (@PathVariable(value = "id")int id){
        BaseResponse baseResponse = new BaseResponse();
        AssetTypeModel data = assetTypeService.getId(id);
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
    @GetMapping(value = "/assetType")
    public BaseResponse get () {
        BaseResponse baseResponse = new BaseResponse();
        List<AssetTypeModel> data = assetTypeService.get();
        baseResponse.setData(data);
        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        return baseResponse;
    }
    @PutMapping(value = "/assetType/{id}")
    public BaseResponse update (@PathVariable(value = "id") int id , @RequestBody AssetTypeModel assetTypeModel){
        BaseResponse baseResponse = new BaseResponse();
        AssetTypeModel data = assetTypeService.update(id , assetTypeModel);
        baseResponse.setData(data);
        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK);
        return baseResponse;
    }
    @DeleteMapping(value = "/assetType/{id}")
    public BaseResponse delete(@PathVariable(value = "id") int id)throws Exception{
        BaseResponse baseResponse = new BaseResponse();
        try {
            assetTypeService.delete(id);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
        }catch (Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }
}
