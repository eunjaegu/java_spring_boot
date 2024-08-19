package com.ej.api.controller;

import com.ej.api.dto.CsvDataDto;
import com.ej.api.service.CsvService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {

	@Autowired
    private CsvService csvService;

    @GetMapping("/csv-data")
    public ResponseEntity<List<CsvDataDto>> uploadCsvToDatabase() {
    	try {
            csvService.saveAllCsvFilesToDatabase();
            List<CsvDataDto> dataList = csvService.getAllData();
            return ResponseEntity.status(HttpStatus.OK).body(dataList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
