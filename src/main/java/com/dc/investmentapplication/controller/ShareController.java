package com.dc.investmentapplication.controller;


import com.dc.investmentapplication.service.ShareService;
import com.dc.investmentapplication.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/shares")
@Validated
public class ShareController {

    @Autowired
    private ShareService shareService;

    @PostMapping("/upload")
    public ApiResponse<String> uploadShares(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        long start = System.currentTimeMillis();
        if(file.isEmpty()) {
            ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), "Error caught while uploading" , "File is empty");
            response.setDuration(System.currentTimeMillis() - start);
            return response;
        }

        try{
            String resService = shareService.shareSharesFromExcel(file);
            ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), "Share file uploaded successfully", resService);
            response.setDuration(System.currentTimeMillis() - start);
            return response;
        }catch (Exception e){
            ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), "Error caught while uploading" , e.getMessage());
            return response;
        }
    }

}
