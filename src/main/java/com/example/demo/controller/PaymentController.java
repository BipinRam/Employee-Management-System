package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.PaymentModel;
import com.example.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "/salary")
    public BaseResponse create(@RequestBody PaymentModel paymentModel) throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        try {
            PaymentModel data = paymentService.createPayment(paymentModel);
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
        PaymentModel data = paymentService.readPaymentId(id);
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
        List<PaymentModel> data = paymentService.getPayment();
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
    public BaseResponse getSearch  (@RequestBody PaymentModel paymentModel){
        PaymentModel data = paymentService.getSearch(paymentModel);
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
    public BaseResponse update (@PathVariable (value = "id") long id , @RequestBody PaymentModel paymentModel)throws Exception{
        BaseResponse baseResponse = new BaseResponse();
        try {
            PaymentModel data = paymentService.updatePayment(id , paymentModel);
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

    @DeleteMapping(value = "/salary/{id}")
    public BaseResponse delete (@PathVariable(value = "id")long id){
        BaseResponse baseResponse = new BaseResponse();
        try {
            baseResponse.setMessage("Deleted");
            baseResponse.setCode(HttpStatus.OK);
            paymentService.deletePaymentId(id);
        }catch(Exception exception){
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
      return baseResponse;
    }
}
