package com.dc.investmentapplication.service;

import com.dc.investmentapplication.entity.Share;
import com.dc.investmentapplication.exception.GlobalExceptionHandler;
import com.dc.investmentapplication.helper.GlobalHelper;
import com.dc.investmentapplication.repository.ShareRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShareService {

    @Autowired
    private ShareRepository shareRepository;
    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    public String shareSharesFromExcel(MultipartFile file) throws Exception {
        String dateTime = GlobalHelper.threadSafeDatetimeFormatSQL.get().format(new Date());
        try{
            boolean isfirstSkip = false;
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<Share> shares = new ArrayList<>();
            int count = 0;
            for (Row row : sheet) {
                if(!isfirstSkip) {
                    isfirstSkip = true;
                    continue;
                }

                /*if(count == 10000) {
                    shareRepository.saveAll(shares);
                    count = 0;
                    shares.clear();
                    System.out.println("Save All called.........");
                }*/

                Share share = new Share();
                share.setSymbol(row.getCell(0).getStringCellValue());
                share.setCompanyName(row.getCell(1).getStringCellValue());
                share.setMarket(row.getCell(2).getStringCellValue());
                share.setCurrentPrice(BigDecimal.valueOf(row.getCell(3).getNumericCellValue()));
                share.setCurrency(row.getCell(4).getStringCellValue());
                share.setVolume((long)row.getCell(5).getNumericCellValue());
                share.setDayHigh(BigDecimal.valueOf(row.getCell(6).getNumericCellValue()));
                share.setDayLow(BigDecimal.valueOf(row.getCell(7).getNumericCellValue()));
                share.setYearHigh(BigDecimal.valueOf(row.getCell(8).getNumericCellValue()));
                share.setYearLow(BigDecimal.valueOf(row.getCell(9).getNumericCellValue()));
                share.setDividendYield(BigDecimal.valueOf(row.getCell(10).getNumericCellValue()));
                share.setPeRatio(BigDecimal.valueOf(row.getCell(11).getNumericCellValue()));
                share.setCreatedAt(dateTime);
                share.setUpdatedAt(dateTime);
                shares.add(share);
                count++;
            }

            shareRepository.saveAll(shares);
            workbook.close();
            return "Data uploaded successfully";
        }catch (Exception e){
            GlobalHelper.logger.error("Unexpected error occurred", e);
            throw new Exception("Error caught in file upload");
        }

    }
}
