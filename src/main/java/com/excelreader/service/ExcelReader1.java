package com.excelreader.service;

import com.excelreader.controller.dto.Product;
import com.excelreader.controller.dto.UrlDto;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelReader1 {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${excelTestUrl}")
    String excelTestUrl;
    String filePath = "C:\\Users\\lalit\\Downloads\\ExcelReader\\ExcelReader\\src\\main\\resources\\data.xlsx";

    public void readColumnFromExcel(String filePath, String columnName) throws IOException {
        int retryCount = 0;
        int maxRetries = 3;
        long initialWaitTimeMillis = 20000;
        FileInputStream fileInputStream = new FileInputStream(filePath);

        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        int columnIndex = -1;
        Row headerRow = sheet.getRow(0);
        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                columnIndex = cell.getColumnIndex();
                break;
            }
        }

        if (columnIndex == -1) {
            System.out.println("Column not found");
            workbook.close();
            return;
        }

        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            Cell cell = row.getCell(columnIndex);

            // Process the cell value (e.g., print it)

            if (cell != null && !cell.getStringCellValue().equals("NA")) {

                System.out.println(rowIndex+" "+cell.getStringCellValue());

                UrlDto urlDto = new UrlDto();
                urlDto.setUrl(cell.getStringCellValue());
                if(urlDto.getUrl()!=null) {
                    try {
                        sendUrl(urlDto);
                    }
                    catch (HttpClientErrorException.TooManyRequests ex) {
                        long waitTimeMillis = (long) (initialWaitTimeMillis * Math.pow(2, retryCount));
                        try {
                            Thread.sleep(waitTimeMillis);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        retryCount++;
                    }
                }
            }
        }

        workbook.close();
    }

    public void someMethod() {
//        excelReader.readExcelFile(filePath);
        String columnName = "onemg";
        try {
            readColumnFromExcel(filePath, columnName);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Product sendUrl(UrlDto urlDto) {
//        System.out.println("urlDto : "+urlDto);
        HttpEntity<UrlDto> entity = new HttpEntity<UrlDto>(urlDto);
//        System.out.println(entity);
        Product body = restTemplate.exchange(excelTestUrl, HttpMethod.POST, entity, Product.class).getBody();
        System.out.println("Product : "+body);
//        System.out.println();
        return body;
    }
}
