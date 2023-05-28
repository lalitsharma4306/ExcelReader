package com.excelreader.controller;

import com.excelreader.service.ExcelReader1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
@Autowired
private ExcelReader1 excelReader1;

    @GetMapping("/sendExcelUrl")
    public ResponseEntity readExcel() {
        excelReader1.someMethod();
        return new ResponseEntity("Readed", HttpStatus.OK);
    }
}
