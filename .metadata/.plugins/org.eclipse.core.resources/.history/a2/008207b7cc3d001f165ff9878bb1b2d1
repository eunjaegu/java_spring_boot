package com.intrabucks.approval.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.intrabucks.approval.data.dto.reactdto.ApprovalDocument_ApprovalDocumentDTO;
import com.intrabucks.approval.data.reactdto.Approval1_Approval1DTO;
import com.intrabucks.employee.data.reactdto.Employee_EmployeeDTO;


/**
 * 결재라인 Approval1Service :  결재라인 생성
 * @author 구은재
 * @version 2.0 
 * 2024-07-06
**/

public interface Approval1Service {

	public String createApproval1(Approval1_Approval1DTO approval1DTO); // 결재라인 생성(등록)
	public Employee_EmployeeDTO sessionEmployee(String empEmail); //session 정보로 empId조회
	public ApprovalDocument_ApprovalDocumentDTO selectOneApproval(Long documentTypeId);  // 문서ID조회
	public Page<ApprovalDocument_ApprovalDocumentDTO> ListApproval(String title, PageRequest pageable); // 문서전체조회
	public Long updateApproval(ApprovalDocument_ApprovalDocumentDTO approvalDocument); // 문서반려(수정)
	

}
