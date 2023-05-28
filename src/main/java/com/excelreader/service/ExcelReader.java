//package com.excelreader.service;
//import org.apache.poi.ss.usermodel.*;
//import org.springframework.stereotype.Component;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//@Component
//public class ExcelReader {
//
//    public void readExcelFile(String filePath) {
//        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
//            Workbook workbook = WorkbookFactory.create(fileInputStream);
//            Sheet sheet = workbook.getSheetAt(0); // Assuming the first sheet
//
//            // Iterate over rows
//            for (Row row : sheet) {
//                // Iterate over cells
//                for (Cell cell : row) {
//                    // Read the cell value based on the cell type
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            String cellValue = cell.getStringCellValue();
//                            System.out.println(cellValue);
//                            break;
//                        case NUMERIC:
//                            double numericValue = cell.getNumericCellValue();
//                            System.out.println(numericValue);
//                            break;
//                        // Handle other cell types as needed
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
