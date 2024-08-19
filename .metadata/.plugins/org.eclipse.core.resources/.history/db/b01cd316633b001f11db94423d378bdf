package com.intrabucks.approval.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intrabucks.approval.data.reactdto.Approval1_Approval1DTO;
import com.intrabucks.approval.service.Approval1Service;
import com.intrabucks.employee.data.reactdto.Employee_EmployeeDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 결재라인 Controller :  결재라인 생성
 * @author 구은재
 * @version 1.0 
 * 2024-07-06
**/

@RestController
@RequestMapping("api/Approval1")
public class Approval1Controller {

	private final Approval1Service approval1Service;
	
	@Autowired
	public Approval1Controller(Approval1Service approval1Service) {
		this.approval1Service = approval1Service;
	}

	/*결재라인 생성(등록)*/
	@PostMapping("/create")
	//DTO로 결재자, 결재상태를 받아서 엔티티에 ID값으로 저장 
	public ResponseEntity<String> createApproval1(@RequestBody Approval1_Approval1DTO Approval1DTO) {
		System.out.println(Approval1DTO.getApprovalSteps()[0].getDeptCode());
		String  approvalLine = approval1Service.createApproval1(Approval1DTO);
		
		return ResponseEntity.ok().body(approvalLine);
	}
	
	/*세션 요청*/
	 @GetMapping("/session")
	  public ResponseEntity<Employee_EmployeeDTO> getSession(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.setAttribute("id", "jisoo@example.com");
	            String id = (String) session.getAttribute("id");
	            Employee_EmployeeDTO employee = approval1Service.sessionEmployee(id);
	            return ResponseEntity.ok(employee);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }
	    }
}
