package com.intrabucks.approval.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.intrabucks.entity.ApprovalDocument;
import com.intrabucks.entity.Employee;

/**
 * 매장 관리자 테이블(Manager) 엔티티로, 매장 관리자에 대한 내용을 담고 있음
 * @author 이정윤
 * @version 1.0
 * 2024-06-27
 * **/

public interface ApprovalDocumentRepository extends JpaRepository<ApprovalDocument, Long> {
	
	@Query("SELECT e FROM ApprovalDocument e WHERE LOWER(e.appDocTitle) LIKE LOWER(CONCAT('%', :title, '%'))")
	Page<ApprovalDocument> findByAppDocTitleContainingIgnoreCase(String title, Pageable pageable);

}
