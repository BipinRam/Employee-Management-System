package com.example.demo.service;

import com.example.demo.dto.AssetInventoryReportDto;
import com.example.demo.dto.EmailDetailsDto;
import com.example.demo.report.AssetInventoryReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AssetInventoryReportService service;

    public EmailDetailsDto sendEmail (EmailDetailsDto emailDetailsDto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDetailsDto.getRecipient());
        message.setFrom("bipinram25@gmailcom");
        message.setSubject(emailDetailsDto.getSubject());
        message.setText(emailDetailsDto.getMsgBody());

        javaMailSender.send(message);
        return emailDetailsDto;
    }
    public EmailDetailsDto sendPdfAsAttachment (EmailDetailsDto emailDetailsDto) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);

            byte[] pdfBytes = generatePdfDocument(emailDetailsDto);
            message.setTo(emailDetailsDto.getRecipient());
            message.setSubject(emailDetailsDto.getSubject());
            message.setText(emailDetailsDto.getMsgBody());

            File attachmentFile = new File(emailDetailsDto.getAttachment());
            if (attachmentFile.exists()){
                FileSystemResource fileSystemResource = new FileSystemResource(attachmentFile);
                message.addAttachment(fileSystemResource.getFilename(), new ByteArrayResource(pdfBytes));
            }

            javaMailSender.send(mimeMessage);
        } catch (MailSendException e) {
            throw e;
        }catch (IOException e){
            throw new MessagingException("Failed to generate pdf " , e);
        }
        return emailDetailsDto;
    }
    public byte[] generatePdfDocument(EmailDetailsDto emailDetailsDto) throws IOException {
        File jsonFile = new File(emailDetailsDto.getAttachment()).getAbsoluteFile();

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
        Map<String, Object> map = mapper.readValue(jsonFile, mapType);

        String json = mapper.writeValueAsString(map);
        String[] strings = json.split(System.lineSeparator());

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

//        contentStream.setFont(pdType1Font, 12);
        contentStream.beginText();
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(25, 725);
        for (String string : strings) {
            contentStream.showText(string);
            contentStream.newLine();
        }
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        contentStream.endText();
        contentStream.close();

//        String pdfFilePath = "C:/Users/bipin/OneDrive/Documents";
//        document.save(pdfFilePath);
        document.close();
        return pdfOutputStream.toByteArray();
    }
}

