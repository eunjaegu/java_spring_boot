package com.intrabucks.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.intrabucks.board.data.dto.reactdto.dto.Board_BoardDTO;
import com.intrabucks.entity.Board;

/**
 * 게시판 관리 기능 관련 Service로, 파일 업로드 및 다운로드
 * 게시판 CRUD 기능 구현
 * @author 구은재
 * @version 2.0
 * 2024-07-10
 **/


public interface BoardService {
	
	//게시판 작성
	Board_BoardDTO createBoard(Board_BoardDTO board_BoardDTO);
	
	//게시판 수정
	Board_BoardDTO updateBoard(Long board_id, Board_BoardDTO board_BoardDTO);
	
	//게시판 전체 조회
	List<Board> selectBoardList();
	
	//게시판 하나만 조회
	Board_BoardDTO selectOneBoard(Long board_id);

	//게시판 부서별 리스트 조회     
	List<Board> selectBoardOfDepartment(Long dept_id);

	//게시판 제목 검색
	List<Board> searchBoard(String keyword);
	
	//게시판 삭제 
	String deleteBoard(Long board_id);
	
	//파일 업로드
	Board_BoardDTO saveBoard(Board_BoardDTO boardDto, MultipartFile file);
	
	//파일 첨부파일
	byte[] downloadFile(Long boardId);
	
	}



