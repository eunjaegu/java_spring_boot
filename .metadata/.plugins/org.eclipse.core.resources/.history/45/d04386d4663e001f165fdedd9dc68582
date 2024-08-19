package com.intrabucks.approval.service;

import com.intrabucks.approval.data.dto.reactdto.ApprovalDocument_ApprovalDocumentDTO;
import com.intrabucks.approval.data.dto.reactdto.DocumentType_DocumentTypeDTO;
import com.intrabucks.approval.data.reactdto.Approval1_Approval1DTO;
import com.intrabucks.approval.data.repository.ApprovalDocumentRepository;
import com.intrabucks.approval.data.repository.DocumentTypeRepository;
import com.intrabucks.employee.data.reactdto.Employee_EmployeeDTO;
import com.intrabucks.employee.data.repository.DepartmentRepository;
import com.intrabucks.employee.data.repository.EmployeeRepository;
import com.intrabucks.entity.ApprovalDocument;
import com.intrabucks.entity.Department;
import com.intrabucks.entity.DocumentType;
import com.intrabucks.entity.Employee;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
/**
 * 결재라인 Approval1ServiceImpl :  결재라인 생성
 * @author 구은재
 * @version 3.0 
 * 2024-07-07
**/


@Service
public class Approval1ServiceImp implements Approval1Service {
	
	public EmployeeRepository employeeRepository;
    public ApprovalDocumentRepository approvalDocumentRepository;
	public DocumentTypeRepository documentTypeRepository;
	
	@Autowired
	public Approval1ServiceImp(EmployeeRepository employeeRepository, ApprovalDocumentRepository approvalDocumentRepository, DocumentTypeRepository documentTypeRepository) {
		this.employeeRepository = employeeRepository;
		this.documentTypeRepository = documentTypeRepository; 
		this.approvalDocumentRepository = approvalDocumentRepository ;
	}

	/**결재라인생성(등록)*/
	@Override
	public String createApproval1(Approval1_Approval1DTO approval1DTO) {
	  StringBuilder approvalLineBuilder = new StringBuilder();
	  
	  for (Approval1_Approval1DTO.ApprovalStepDTO approvalStep : approval1DTO.getApprovalSteps()) {
          if(approvalStep.getEmployee() != null) {
        	  Employee employee = approvalStep.getEmployee();
              String empId = employee.getEmpId().toString();
              String empName = employee.getEmpName();
             // String deptId =  approvalStep.getDeptCode() ;
              String empPosition =  employee.getEmpPosition();
              String approvalState = approvalStep.getApprovalState();

              approvalLineBuilder.append(empId).append(",").append(empName).append(",").append(empPosition).append(",").append(approvalState).append("//-//");
          } else {
        	  String empId = approvalStep.getEmpId().toString();
              String empName = approvalStep.getEmpName();
              //String deptId =  approvalStep.getDeptCode() ;
              String empPosition =  approvalStep.getEmpPosition();
              String approvalState = approvalStep.getApprovalState();

              approvalLineBuilder.append(empId).append(",").append(empName).append(",").append(empPosition).append(",").append(approvalState).append("//-//");
          }
		  
      }

      String approvalLine = approvalLineBuilder.toString();
      System.out.println("생성된 결재 라인: " + approvalLine); // 콘솔에 출력

      return approvalLine;
  }
	
	
	/**session으로 empId 조회*/
	@Override
	public Employee_EmployeeDTO sessionEmployee(String empEmail) {
		System.err.println("Attempting to find employee with email: " + empEmail);
		
		//emp id로 사람 조회하기
		Employee employee = employeeRepository.findByEmpEmail(empEmail);
		
		Employee_EmployeeDTO employeeDTO = new Employee_EmployeeDTO();
		if (employee != null) {
		
			//dto 객체로 변환
			employeeDTO.setDeptCode(employee.getDepartment().getDeptCode());
			employeeDTO.setEmpEmail(employee.getEmpEmail());
			employeeDTO.setEmpName(employee.getEmpName());
			employeeDTO.setEmpPosition(employee.getEmpPosition());
			employeeDTO.setEmpId(employee.getEmpId());
			//나머지 다 채우기
		}
		
		 System.out.println("Returning employee DTO: " + employeeDTO);
		
		return employeeDTO;
	}

	
	
	/**문서ID조회*/
	@Override
	public ApprovalDocument_ApprovalDocumentDTO selectOneApproval(Long documentId) {
		ApprovalDocument approvalDocument = approvalDocumentRepository.findById(documentId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid document ID: " + documentId));
	
		return new ApprovalDocument_ApprovalDocumentDTO(
				approvalDocument.getAppDocId(),
				approvalDocument.getDocumentType(),
				approvalDocument.getAppDocTitle(),
				approvalDocument.getAppDocDepartment(),
				approvalDocument.getEmployee(),
				approvalDocument.getAppDocDepartmentGrade(),
				approvalDocument.getAppDocContent(),
				approvalDocument.getAppDocStage(),
				approvalDocument.getAppDocCreatedAt(),
				approvalDocument.getAppDocUpdatedAt(),	
				approvalDocument.getAppDocPathString()
				);
				
	}

	/**문서전체조회*/
	@Override
	public Page<ApprovalDocument_ApprovalDocumentDTO> ListApproval(String title, PageRequest pageable) {
	

		int page = pageable.getPageNumber();
	    if (page < 0) {
	        page = 0;
	    }
		
		final int pageSize = 5;
    	
		pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "appDocCreatedAt"));
    	
    	Page<ApprovalDocument> approvalPage;
    	
    	if (title == null || title.isEmpty()) {
            // empName이 null이거나 빈 문자열인 경우 모든 직원을 조회
    		approvalPage = approvalDocumentRepository.findAll(pageable);
        } else {
            // 문서 제목으로 페이징 하는 것 
        	approvalPage = approvalDocumentRepository.findByAppDocTitleContainingIgnoreCase(title, pageable);
        }   
    	
    	return approvalPage.map(approval ->
    	new ApprovalDocument_ApprovalDocumentDTO(
    			approval.getAppDocId(),
    			approval.getDocumentType(),
    			approval.getAppDocTitle(),
    			approval.getAppDocDepartment(),
    			approval.getEmployee(),
    			approval.getAppDocDepartmentGrade(),
    			approval.getAppDocContent(),
    			approval.getAppDocStage(),
    			approval.getAppDocCreatedAt(),
    			approval.getAppDocUpdatedAt(),	
    			approval.getAppDocPathString()
				)
    	);
    	
		
	}

	
	/**문서반려(수정)*/
	@Override
	public Long updateApproval(ApprovalDocument_ApprovalDocumentDTO approvalDocumentDTO) {
		
		ApprovalDocument approvalDocument = approvalDocumentRepository.findById(approvalDocumentDTO.getAppDocId())
	            .orElseThrow(() -> new IllegalArgumentException("Invalid approvalDocument ID:" + approvalDocumentDTO.getAppDocId()));
		
	    DocumentType documentType = documentTypeRepository.findById(approvalDocumentDTO.getDocTypeId().getDocumentTypeId())
	            .orElseThrow(() -> new IllegalArgumentException("Invalid Document Type ID: " + approvalDocumentDTO.getDocTypeId().getDocumentTypeId()));

	    Employee employee = employeeRepository.findById(approvalDocumentDTO.getEmpId().getEmpId())
	            .orElseThrow(() -> new IllegalArgumentException("Invalid Employee ID: " + approvalDocumentDTO.getEmpId().getEmpId()));

	    approvalDocument.setAppDocTitle(approvalDocumentDTO.getAppDocTitle());
	    approvalDocument.setAppDocContent(approvalDocumentDTO.getAppDocContent());
	    approvalDocument.setAppDocStage(approvalDocumentDTO.getAppDocStage());
	    approvalDocument.setDocumentType(documentType); // 외래키 설정
	    approvalDocument.setAppDocCreatedAt(approvalDocumentDTO.getAppDocCreatedAt());
	    approvalDocument.setAppDocUpdatedAt(approvalDocumentDTO.getAppDocUpdatedAt());
	    approvalDocument.setEmployee(employee); // 외래키 설정
	    approvalDocument.setAppDocDepartment(approvalDocumentDTO.getAppDocDepartment());
	    approvalDocument.setAppDocPathString(approvalDocumentDTO.getAppDocPathString());
	    approvalDocument.setAppDocDepartment(approvalDocumentDTO.getAppDocDepartmentGrade());
	    
	    approvalDocumentRepository.save(approvalDocument);
	
	    return approvalDocument.getAppDocId();
	  
	
	}

	
	    
}
