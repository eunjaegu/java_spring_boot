package com.intrabucks.board.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	private static final String UPLOAD_DIR = "src/main/resources/data/Boardfiles/";
	
	@Override
	public Board_BoardDTO saveBoard(Board_BoardDTO boardDto, MultipartFile file) {
		String fileName = file.getOriginalFilename();
        try {
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Board board = new Board();
        board.setBoardTitle(boardDto.getBoardTitle());
        board.setBoardContent(boardDto.getBoardContent());
        board.setBoardDate(new Date());
        board.setBoardFile(fileName);
        board.setEmployee(boardDto.getEmployee());
        board.setDepartment(boardDto.getDepartment());
        
        board = boardRepository.save(board);
        return new Board_BoardDTO(board.getBoardId(), board.getBoardTitle(), board.getBoardContent(),
                            board.getEmployee(), board.getBoardDate(), 
                            board.getBoardFile(), board.getDepartment());
	}

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
}
