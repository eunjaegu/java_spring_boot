package com.intrabucks.board.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.intrabucks.entity.Board;
import com.intrabucks.entity.Department;

public interface BoardRepository extends JpaRepository<Board, Long> {

	//부서별 게시판 조회
	@Query(value = "select * from board where dept_id = ?1", nativeQuery = true)
	List<Board> findByDepartment(Long dept_id);
	
	
	//부서별 검색어 조회
	List<Board> findByBoardTitleContaining(String keyword);
}
