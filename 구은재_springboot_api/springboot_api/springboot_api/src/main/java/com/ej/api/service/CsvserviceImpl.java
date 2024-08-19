package com.ej.api.service;

import java.util.List;

import com.ej.api.dto.CsvDataDto;

public interface CsvserviceImpl {
	void saveAllCsvFilesToDatabase();

    List<CsvDataDto> getAllData();
}
