package com.dc.investmentapplication.controller;


import com.dc.investmentapplication.entity.Share;
import com.dc.investmentapplication.service.ShareService;
import com.dc.investmentapplication.utils.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shares")
@Validated
public class ShareController {

    @Autowired
    private ShareService shareService;

    private RestClient restClient;

    int retryCall = 1;
    //@RateLimiter(name="shareLimit" ,fallbackMethod = "ShareFallBackMethod")
    //@Retry(name = "shareRetry", fallbackMethod = "shareRetryFallBackMethod")
    @CircuitBreaker(name = "shareCircuitBreak", fallbackMethod = "shareCircuitBreakFallBackMethod")
    @GetMapping("/rate-limit")
    public ApiResponse rateLimit(HttpServletRequest request) {
        System.out.println("retryCall : "+retryCall++);
        long start = System.currentTimeMillis();
        this.restClient.get().uri("http://localhost:9999/api/v1/share/register");
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), "Calling Share rate limit" , "e.getMessage()");
        response.setDuration(System.currentTimeMillis() - start);
        return response;
    }

    public ApiResponse ShareFallBackMethod(HttpServletRequest request, Throwable t) {
        long start = System.currentTimeMillis();
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Calling fallback share limiter" , "e.getMessage()");
        response.setDuration(System.currentTimeMillis() - start);
        return response;
    }

    public ApiResponse shareRetryFallBackMethod(HttpServletRequest request, Throwable t) {
        long start = System.currentTimeMillis();
        retryCall = 1;
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Calling fallback share Retry" , "e.getMessage()");
        response.setDuration(System.currentTimeMillis() - start);
        return response;
    }


    public ApiResponse shareCircuitBreakFallBackMethod(HttpServletRequest request, Throwable t) {
        long start = System.currentTimeMillis();
        retryCall = 1;
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Calling fallback share shareCircuitBreakFallBackMethod" , "e.getMessage()");
        response.setDuration(System.currentTimeMillis() - start);
        return response;
    }

    @GetMapping("/getAllShare")
    public ApiResponse getAllShare(HttpServletRequest request) {
        long start = System.currentTimeMillis();
        try{
            List<Share> shares = shareService.getAllShare();
            ApiResponse<List<Share>> response = new ApiResponse<>(HttpStatus.OK.value(), "Records Found!" , shares);
            response.setDuration(System.currentTimeMillis() - start);
            return response;
        }catch (Exception e){
            ApiResponse<String> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error caught while uploading" , e.getMessage());
            response.setDuration(System.currentTimeMillis() - start);
            return response;
        }
    }

    @PostMapping("/upload")
    public ApiResponse<String> uploadShares(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        long start = System.currentTimeMillis();

        try{
            if(file.isEmpty()) {
                ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), "Error caught while uploading" , "File is empty");
                response.setDuration(System.currentTimeMillis() - start);
                return response;
            }

            String resService = shareService.shareSharesFromExcel(file);
            ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), "Share file uploaded successfully", resService);
            response.setDuration(System.currentTimeMillis() - start);
            return response;
        }catch (Exception e){
            ApiResponse<String> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error caught while uploading" , e.getMessage());
            return response;
        }
    }

}
