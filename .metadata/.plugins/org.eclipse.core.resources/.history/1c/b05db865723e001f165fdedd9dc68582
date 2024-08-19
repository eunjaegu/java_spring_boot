package com.intrabucks.approval.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intrabucks.approval.data.dto.reactdto.ApprovalDocument_ApprovalDocumentDTO;
import com.intrabucks.approval.data.dto.reactdto.DocumentType_DocumentTypeDTO;
import com.intrabucks.approval.data.reactdto.Approval1_Approval1DTO;
import com.intrabucks.approval.service.Approval1Service;
import com.intrabucks.employee.data.reactdto.Employee_EmployeeDTO;
import com.intrabucks.entity.DocumentType;
import com.intrabucks.quitter.data.reactdto.Quitter_QuitterDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 결재라인 Controller : 결재라인 생성 및 세션 요청
 * @author 구은재
 * @version 1.0 
 * 2024-07-06
**/

@RestController
@RequestMapping("api/Approval1")
public class Approval1Controller {

	@Autowired
	private final Approval1Service approval1Service;
	
	@Autowired
	public Approval1Controller(Approval1Service approval1Service) {
		this.approval1Service = approval1Service;
	}

	/*결재라인 생성(등록)*/
	@PostMapping("/create")
	//DTO로 결재자, 결재상태를 받아서 엔티티에 ID값으로 저장 
	public ResponseEntity<String> createApproval1(@RequestBody Approval1_Approval1DTO Approval1DTO) {
		System.out.println(Approval1DTO);
		System.out.println(Approval1DTO.getApprovalSteps()[0].getEmpPosition());
		String  approvalLine = approval1Service.createApproval1(Approval1DTO);
		
		return ResponseEntity.ok().body(approvalLine);
	}
	
	/*세션 요청*/
	 @GetMapping("/session")
	  public ResponseEntity<Employee_EmployeeDTO> getSession(HttpServletRequest request) {
	        System.out.println("***********************************************************************");
		// 세션 없이 "id", "jisoo@example.com"으로만 테스트
		    String id = "seongjin@example.com";
		    Employee_EmployeeDTO employee = approval1Service.sessionEmployee(id);
		    return ResponseEntity.ok(employee);
		  
		  /**
		  HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.setAttribute("id", "jisoo@example.com");
	            String id = (String) session.getAttribute("id");
	            Employee_EmployeeDTO employee = approval1Service.sessionEmployee(id);
	            return ResponseEntity.ok(employee);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }
	        */
	    }
	  

	  /**문서ID조회*/
	  @GetMapping("/{documentId}")
	  public ResponseEntity<ApprovalDocument_ApprovalDocumentDTO> selectOneApproval(@PathVariable Long documentId){
		  ApprovalDocument_ApprovalDocumentDTO approvalDocumentDTO = approval1Service.selectOneApproval(documentId);
	
		  if (approvalDocumentDTO != null) {
		        return ResponseEntity.ok(approvalDocumentDTO);
		    } else {
		        return ResponseEntity.notFound().build();
		    }
	  }
	  
	  /**전체문서목록조회*/	  
	  @GetMapping("/select")
		public ResponseEntity<Page<ApprovalDocument_ApprovalDocumentDTO>> getAllApproval(@RequestParam(required = false) String title,
				@RequestParam(required = false) Integer page, @RequestParam(defaultValue = "5") int size) {

			int pageNumber = (page != null && page > 0) ? page : 0;
			PageRequest pageable = PageRequest.of(pageNumber, size, Sort.by(Sort.Direction.DESC, "empJoinDate"));
			Page<ApprovalDocument_ApprovalDocumentDTO> ApprovalDocument = approval1Service.ListApproval(title, pageable);
			return ResponseEntity.ok().body(ApprovalDocument);
		}
	  
	  /**문서반려(수정)*/
	  @PutMapping("/returning/{documentId}")
	  public ResponseEntity<Long> updateEmployee(@RequestBody ApprovalDocument_ApprovalDocumentDTO ApprovalDocument) {
			Long documentId = approval1Service.updateApproval(ApprovalDocument);
			return ResponseEntity.ok().body(documentId);
	  }
}
