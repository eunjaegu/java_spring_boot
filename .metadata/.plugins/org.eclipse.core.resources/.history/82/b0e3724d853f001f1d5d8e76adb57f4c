package com.intrabucks.board.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.intrabucks.board.data.dto.reactdto.dto.Board_BoardDTO;
import com.intrabucks.board.data.repository.BoardRepository;
import com.intrabucks.employee.data.repository.DepartmentRepository;
import com.intrabucks.entity.Board;
import com.intrabucks.entity.Department;




/**
 * 게시판 관리 기능 관련 ServiceImpl로, 파일 업로드 및 다운로드
 * 
 * @author 구은재
 * @version 2.0
 * 2024-07-10
 **/

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository; 
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

	
	// 게시판 생성
	@Override
	public Board_BoardDTO createBoard(Board_BoardDTO board_BoardDTO) {

		Board board = new Board();

		board.setBoardContent(board_BoardDTO.getBoardContent());
		board.setBoardDate(board_BoardDTO.getBoardDate());
		board.setBoardFile(board_BoardDTO.getBoardFile());
		board.setBoardId(board_BoardDTO.getBoardId());
		board.setBoardTitle(board_BoardDTO.getBoardTitle());
		board.setDepartment(board_BoardDTO.getDepartment());
		board.setEmployee(board_BoardDTO.getEmployee());

		boardRepository.save(board);

		// 엔티티 -> dto
		Board_BoardDTO saveBoardDto = new Board_BoardDTO();
		saveBoardDto.setBoardContent(board.getBoardContent());
		saveBoardDto.setBoardDate(board.getBoardDate());
		saveBoardDto.setBoardFile(board.getBoardFile());
		saveBoardDto.setBoardId(board.getBoardId());
		saveBoardDto.setBoardTitle(board.getBoardTitle());
		saveBoardDto.setDepartment(board.getDepartment());
		saveBoardDto.setEmployee(board.getEmployee());

		return saveBoardDto;
	}

	// 게시판 목록 생성
	@Override
	public List<Board> selectBoardList() {
		List<Board> BoardList = boardRepository.findAll();
		return BoardList;
	}

	// 게시판 삭제
	@Override
	public String deleteBoard(Long board_id) {
		Optional<Board> board = boardRepository.findById(board_id);

		String result = "";
		if (board.isPresent()) {
			boardRepository.deleteById(board_id);
			result = "게시판 삭제 성공";
		} else {
			result = "해당 정보가 없어 게시판 삭제 실패";
		}

		return result;
	}

	// 게시판 수정
	@Override
	public Board_BoardDTO updateBoard(Long board_id, Board_BoardDTO board_BoardDTO) {
		Board board = boardRepository.getById(board_id);

		Board_BoardDTO boardDTO = new Board_BoardDTO();
		
		//dto 값 꺼내서 엔티티 세팅 후 저장
		if (board != null) {
			//게시판 업데이트
			board.setBoardContent(board_BoardDTO.getBoardContent());
			board.setBoardDate(board_BoardDTO.getBoardDate());
			board.setBoardFile(board_BoardDTO.getBoardFile());
			board.setBoardId(board_BoardDTO.getBoardId());
			board.setBoardTitle(board_BoardDTO.getBoardTitle());
			board.setDepartment(board_BoardDTO.getDepartment());
			board.setEmployee(board_BoardDTO.getEmployee());
			
			//레포지터리 적용
			Board boardSave = boardRepository.save(board);
			
			boardDTO.setBoardContent(boardSave.getBoardContent());
			boardDTO.setBoardDate(boardSave.getBoardDate());
			boardDTO.setBoardFile(boardSave.getBoardFile());
			boardDTO.setBoardId(boardSave.getBoardId());
			boardDTO.setBoardTitle(boardSave.getBoardTitle());
			boardDTO.setDepartment(boardSave.getDepartment());
			boardDTO.setEmployee(boardSave.getEmployee());
		}
		
		return boardDTO;
	}

	// 게시판 상세보기
	@Override
	public Board_BoardDTO selectOneBoard(Long board_id) {
		Board board = boardRepository.getById(board_id);
		
		Board_BoardDTO boardDTO = new Board_BoardDTO();
		
		boardDTO.setBoardContent(board.getBoardContent());
		boardDTO.setBoardDate(board.getBoardDate());
		boardDTO.setBoardFile(board.getBoardFile());
		boardDTO.setBoardId(board.getBoardId());
		boardDTO.setBoardTitle(board.getBoardTitle());
		boardDTO.setDepartment(board.getDepartment());
		boardDTO.setEmployee(board.getEmployee());

		
		return boardDTO;
	}

	// 게시판 부서별 조회
	@Override
	public List<Board> selectBoardOfDepartment(Long dept_id) {
		
		List<Board> BoardList = boardRepository.findByDepartment(dept_id);
		
		return BoardList;
	}

	// 검색
	@Override
	public List<Board> searchBoard(String keyword) {
	    List<Board> boardList = boardRepository.findByBoardTitleContaining(keyword);
	    return boardList;
	}

	/**파일 업로드&파일 다운로드*/
	//파일 업로드
//	private static final String UPLOAD_DIR = "src/main/resources/data/Boardfiles/";
//	
//	@Override
//	public Board_BoardDTO saveBoard(Board_BoardDTO boardDto, MultipartFile file) {
//		String fileName = file.getOriginalFilename();
//        try {
//            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Board board = new Board();
//        board.setBoardTitle(boardDto.getBoardTitle());
//        board.setBoardContent(boardDto.getBoardContent());
//        board.setBoardDate(new Date());
//        board.setBoardFile(fileName);
//        board.setEmployee(boardDto.getEmployee());
//        board.setDepartment(boardDto.getDepartment());
//        
//        board = boardRepository.save(board);
//        return new Board_BoardDTO(board.getBoardId(), board.getBoardTitle(), board.getBoardContent(),
//                            board.getEmployee(), board.getBoardDate(), 
//                            board.getBoardFile(), board.getDepartment());
//	}
	
	// 파일이 저장될 경로
    private static final String FILE_UPLOAD_PATH = "src/main/resources/data/files/";
	
	 //첨부파일 업로드
		@Override
		public Board_BoardDTO uploadFiles(Long boardID, MultipartFile file) {
			logger.info("받은 approvalID: {}", boardID);
	        logger.info("받은 파일: {}", file);
			
	        Board_BoardDTO boardDTO = new Board_BoardDTO();
			
	        // 게시판 엔티티 조회
	        Board board = boardRepository.findById(boardID)
	                .orElseThrow(() -> new EntityNotFoundException("Board not found"));
			
			// 첨부파일 DB 저장 및 파일 시스템에 저장
			try {
				 // 파일 시스템에 저장할 경로와 파일명 설정
				Path filePath = Paths.get(FILE_UPLOAD_PATH + file.getOriginalFilename());
	            Files.write(filePath, file.getBytes());

	         // Board 엔티티에 파일 정보 설정
	            board.setBoardFile(file.getOriginalFilename());
	            boardDTO.setBoardFile(file.getOriginalFilename()); // DTO에도 파일명 설정
	            
	         // BoardDTO 객체 설정
	            board.setBoardTitle(board.getBoardTitle());
	            board.setBoardContent(board.getBoardContent());
	            board.setEmployee(board.getEmployee());
	            board.setBoardDate(board.getBoardDate());
	            board.setDepartment(board.getDepartment());
	            
	         // 파일 관련 정보 저장
	            boardRepository.save(board);
	            
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("파일 저장 중 오류 발생");
			}
		
			
		    return boardDTO;
		}
/**
	//파일 다운로드
	@Override
	public byte[] downloadFile(Long boardId) {
		 Optional<Board> boardOpt = boardRepository.findById(boardId);
	        if (boardOpt.isPresent()) {
	            Board board = boardOpt.get();
	            String fileName = board.getBoardFile();
	            String filePath = UPLOAD_DIR + fileName;
	            try {
	                return Files.readAllBytes(Paths.get(filePath));
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return null;
	    }
	    */

		@Override
		public byte[] downloadFile(Long boardId) {
			// TODO Auto-generated method stub
			return null;
		}
}
