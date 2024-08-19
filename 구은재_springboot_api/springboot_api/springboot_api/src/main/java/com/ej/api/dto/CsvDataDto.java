package com.ej.api.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 클래스에 있는 모든 필드를 초기화 하는 생성자 자동 작성
@NoArgsConstructor // 기본 생성자(파라미터 안받음)
@Builder
public class CsvDataDto {

	private Date transactionDate; //이용일시
	
	private String merchantName; //가맹점명
	
	private Long amount; // 이용금액
	
}
