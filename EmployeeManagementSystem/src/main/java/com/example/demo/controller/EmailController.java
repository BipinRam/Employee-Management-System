package com.example.demo.controller;

import com.example.demo.dto.EmailDetailsDto;
import com.example.demo.model.BaseResponse;
import com.example.demo.report.AssetInventoryReportService;
import com.example.demo.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    EmailService emailService;
    @Autowired
    AssetInventoryReportService assetInventoryReportService;
    @Autowired
    JavaMailSender javaMailSender;


    @PostMapping(value = "/email")
    public BaseResponse email (@RequestBody EmailDetailsDto emailDetailsDto)throws Exception {
        EmailDetailsDto data = emailService.sendEmail(emailDetailsDto);
        BaseResponse baseResponse = new BaseResponse();
        try {
            baseResponse.setData(data);
            baseResponse.setMessage("Success");
            baseResponse.setCode(HttpStatus.OK);
            return baseResponse;
        } catch (Exception exception) {
            baseResponse.setMessage(exception.getMessage());
            baseResponse.setCode(HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }

    @PostMapping(value = "/email/attachment")
    public BaseResponse emailWithAttachment (@RequestBody EmailDetailsDto emailDetailsDto) throws Exception {
        EmailDetailsDto data = emailService.sendPdfAsAttachment(emailDetailsDto);
        BaseResponse baseResponse = new BaseResponse();
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
    @GetMapping(value = "/assetInventoryReport")
    public ResponseEntity<String> assetInventoryReportEmail(){
        try{
            byte[] pdfContent = assetInventoryReportService.generateAssetInventoryReportPdf();
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message , true);
            messageHelper.setFrom("bipinram25@gmail.com");
            messageHelper.setTo("bipinram007@gmail.com");
            messageHelper.setSubject("Asset Inventory Report");
            messageHelper.addAttachment("AssetInventoryReport.pdf" , new ByteArrayResource(pdfContent));
            javaMailSender.send(message);
            return ResponseEntity.ok("Asset inventory report email sent successfully");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send the email" + e.getMessage());
        }
    }
}
