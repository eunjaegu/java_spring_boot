package com.intrabucks.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.intrabucks.board.data.dto.reactdto.dto.Board_BoardDTO;
import com.intrabucks.board.service.BoardService;
import com.intrabucks.entity.Board;
import com.intrabucks.entity.Department;
import com.intrabucks.entity.Employee;


/**
 * 게시판 관리 기능 관련 Controller로, 파일 업로드 및 다운로드
 * 게시판 CRUD 기능 구현
 * @author 구은재
 * @version 2.0
 * 2024-07-10
 **/

@RestController
@RequestMapping("/api/v1/intrabucks/board")
public class BoardController {
   
   @Autowired
   private BoardService boardService;
   
   //게시판 작성
   @PostMapping("/createBoard")
   public ResponseEntity<Board_BoardDTO> createBoard(@RequestBody Board_BoardDTO board_BoardDTO){
	   Board_BoardDTO board = boardService.createBoard(board_BoardDTO);
	   return ResponseEntity.ok(board);
   }
   
   //게시판 수정
   @PutMapping("/updateBoard/{board_id}")
   public ResponseEntity<Board_BoardDTO> updateBoard(@PathVariable Long board_id, @RequestBody Board_BoardDTO board_BoardDTO){
	   Board_BoardDTO board = boardService.updateBoard(board_id, board_BoardDTO);			   
	   return ResponseEntity.ok(board);
   }
   
   //게시판 전체 조회
   @GetMapping("/selectBoardList")
   public ResponseEntity<List<Board>> selectBoardList(){
	  List<Board> board = boardService.selectBoardList();			   
	  return ResponseEntity.ok(board);
   }
   
   //게시판 하나만 조회
   @GetMapping("/selectOneBoard/{board_id}")
   public ResponseEntity<Board_BoardDTO> selectOneBoard(@PathVariable Long board_id){
	   Board_BoardDTO board = boardService.selectOneBoard(board_id);   
	   return ResponseEntity.ok(board);
   }
   
   //게시판 부서별 리스트 조회   
   @GetMapping("/selectBoardOfDepartment/{dept_id}")
   public ResponseEntity<List<Board>> selectBoardOfDepartment(@PathVariable Long dept_id){
	   List<Board> selctOneDeptList= boardService.selectBoardOfDepartment(dept_id);
	   return ResponseEntity.ok(selctOneDeptList);
   }
   
   //게시판 제목 검색
   @GetMapping("/searchBoard/{keyword}")
   public ResponseEntity<List<Board>> searchBoard(@PathVariable String keyword){
	   List<Board> boardSearchList = boardService.searchBoard(keyword);
	   return ResponseEntity.ok(boardSearchList);
   }
   
   //게시판 삭제 
   @DeleteMapping("/deleteBoard/{board_id}")
   public ResponseEntity<String> deleteBoard(@PathVariable Long board_id){
	   String result = boardService.deleteBoard(board_id);
	   return ResponseEntity.ok(result);
   }
   
   /**첨부파일 업로드&다운로드*/
   //첨부파일 업로드
   @PostMapping("/uploadBoard")
   public ResponseEntity<Board_BoardDTO> uploadFile(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("boardTitle") String boardTitle,
                                                    @RequestParam("boardContent") String boardContent,
                                                    @RequestParam("empEmail") String empEmail,
                                                    @RequestParam("deptId") Long deptId) {

       // 이메일과 부서 ID를 이용해 실제 Employee와 Department를 조회하는 코드가 필요합니다.
       Employee employee = new Employee(); // 예시로 생성
       employee.setEmpEmail(empEmail);

       Department department = new Department(); // 예시로 생성
       department.setDeptId(deptId);

       // DTO 객체 생성 및 설정
       Board_BoardDTO boardDto = new Board_BoardDTO();
       boardDto.setBoardTitle(boardTitle);
       boardDto.setBoardContent(boardContent);
       boardDto.setEmployee(employee);
       boardDto.setDepartment(department);

       // 서비스 메소드를 통해 업로드 처리
       Board_BoardDTO savedBoard = boardService.saveBoard(boardDto, file);

       if (savedBoard != null) {
           return ResponseEntity.ok(savedBoard);
       } else {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
   
   
   //첨부파일 다운로드
   @GetMapping("/downloadBoard/{boardId}")
   public ResponseEntity<byte[]> downloadFile(@PathVariable Long boardId) {
       byte[] data = boardService.downloadFile(boardId);
       if (data != null) {
           return ResponseEntity.ok()
                   .contentType(MediaType.APPLICATION_OCTET_STREAM)
                   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"file\"")
                   .body(data);
       }
       return ResponseEntity.notFound().build();
   }

   

}
