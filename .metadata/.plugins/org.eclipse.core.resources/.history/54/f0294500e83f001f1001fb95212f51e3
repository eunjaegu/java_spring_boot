package com.intrabucks.board.data.dto.reactdto.dto;

import java.util.Date;
import com.intrabucks.entity.Department;
import com.intrabucks.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Board 기능 인트라넷에서 리액트로 API 사용을 목적으로 DTO 생성, 파일 업로드 및 다운로드
 * @author 구은재
 * 2024.07.10
 * **/


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board_BoardDTO {
	
	private Long boardId; //글 ID
	
	private String boardTitle; //글 제목
	
	private String boardContent; //글 내용
	
	private Employee employee; //작성자 이메일 FK
	
	private Date boardDate; //게시일자

	private String boardFile; //첨부파일

	private Department department; //부서 FK
	

}