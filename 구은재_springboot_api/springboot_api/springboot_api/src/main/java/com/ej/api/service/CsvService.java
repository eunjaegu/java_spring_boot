package com.ej.api.service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ej.api.dto.CsvDataDto;
import com.ej.api.entity.Transaction;
import com.ej.api.repository.TransactionRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import lombok.extern.slf4j.Slf4j;

import java.io.Reader;

import java.util.ArrayList;


@Slf4j
@Service
public class CsvService implements CsvserviceImpl{

	@Autowired
	private TransactionRepository transactionRepository;

	private static final String CSV_FOLDER = "src/main/resources/csv/";

	@Transactional
	public void saveAllCsvFilesToDatabase() {
		transactionRepository.deleteAll();
        System.out.println("하하하");
        try {
            Files.list(Paths.get(CSV_FOLDER))
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".csv"))
                .forEach(path -> {
                	try (Reader reader = new FileReader(path.toFile())) {
                        CSVReader csvReader = new CSVReaderBuilder(reader)
                                .withSkipLines(1)
                                .build();
                        System.out.println("호호호");

                        List<String[]> csvRecords = csvReader.readAll();
                        List<Transaction> dataList = new ArrayList<>();
                        
                        System.out.println("히히히");

                    		 for (String[] record : csvRecords) {
//                                 String[] record = records[0].split("\t");
                                 
                                 System.out.println("헤헤헤");

                                 System.out.println("ㅇㅁㄴㅇㅁㄴㅇ: " + record.length);
                            if (record.length >= 9) { // 필드 개수가 9개 이상인지 확인
                            	
                                Transaction data = new Transaction();
                                data.setTransactionDate(testDateParsing(record[0]));
                                data.setApprovalNumber(Long.parseLong(record[1]));
                                data.setAccountType(record[2]);
                                data.setBrand(record[3]);
                                data.setCardNumber(record[4]);
                                data.setMerchantName(record[5]);
                                data.setAmount(Long.parseLong(record[6]));
                                data.setTransactionType(record[7]);
                                data.setAcquisitionStatus(record[8]);
                                // 필요에 따라 추가적인 열 매핑
                                dataList.add(data);
                                
                                System.out.println("흐흐흐");
                                
                            } else {
                                log.warn("행의 필드 수가 예상보다 적습니다: {}", String.join(",", record));
                            }
                        }
                        transactionRepository.saveAll(dataList);
                        log.info("CSV 파일 '{}'을 읽어와 데이터베이스에 저장했습니다.", path.getFileName());
                    } catch (IOException | CsvException e) {
                        log.error("CSV 파일 '{}' 읽기에 실패했습니다: {}", path.getFileName(), e.getMessage());
                    } catch (NumberFormatException e) {
                        log.error("CSV 파일 '{}'의 데이터 형식이 잘못되었습니다: {}", path.getFileName(), e.getMessage());
                    }
                });
        } catch (IOException e) {
            log.error("CSV 파일 목록 읽기에 실패했습니다: {}", e.getMessage());
        }
	}

	public List<CsvDataDto> getAllData() {
		List<Object[]> results = transactionRepository.findTransactionDetails();
				
		return  results.stream()
                .map(result -> new CsvDataDto((Date) result[0], (String) result[1], (Long) result[2]))
                .collect(Collectors.toList());
	}
	
	 public Date testDateParsing(String date) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        
	        System.out.println("허허허");
	        
	        try {
	            Date parsedDate = dateFormat.parse(date);
	            log.info("파싱된 날짜: {}", parsedDate);
	            return parsedDate;
	            // 여기에서 parsedDate를 원하는 형식으로 변환하여 사용
	        } catch (ParseException e) {
	            log.error("날짜 파싱 에러: {}", e.getMessage());
	        }
			return null;
	
	
	 }

}