package com.intrabucks.approval.service;


import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.intrabucks.approval.data.dto.reactdto.ApprovalDocument_ApprovalDocumentDTO;
import com.intrabucks.approval.data.dto.reactdto.AttachedFile_AttachedFileDTO;
import com.intrabucks.approval.data.dto.reactdto.DocumentType_DocumentTypeDTO;
import com.intrabucks.approval.data.repository.ApprovalDocumentRepository;
import com.intrabucks.approval.data.repository.ApprovalRepository;
import com.intrabucks.approval.data.repository.AttachedFileRepository;
import com.intrabucks.approval.data.repository.DocumentTypeRepository;
import com.intrabucks.entity.ApprovalDocument;
import com.intrabucks.entity.AttachedFile;
import com.intrabucks.entity.DocumentType;

/**
 * 전자결재 기능 관련 Service로, 문서 리스트, 문서 선택 등의 기능 구현
 * 전자결재 관련 html 폼 선택 및 리스트 출력, 전자결재 저장,수정,삭제,조회 등의 기능 구현
 * @author 김아현
 * @version 1.0 2024-06-28
 **/


@Service
public class ApprovalServiceImpl implements ApprovalService{
	/**의존성 주입*/
	@Autowired
    private	ApprovalRepository approvalRepository;
	
	@Autowired
	private DocumentTypeRepository documentTypeRepository;

	@Autowired
	private AttachedFileRepository attachedFileRepository;
	
	@Autowired
	private ApprovalDocumentRepository approvalDocumentRepository;
	
	/**HTML 폼 관련 코드*/
	//폼 리스트 뽑기
	@Override
	public List<DocumentType> selectFormList() {
		List<DocumentType> FormList = documentTypeRepository.findAll();
		return FormList;
	}

	//폼 상세보기(폼 띄우기)
	@Override
	public DocumentType_DocumentTypeDTO selectOneForm(Long documentTypeId) {
		
		//dto 객체 생성
		DocumentType_DocumentTypeDTO oneFormDTO = new DocumentType_DocumentTypeDTO();
				
		//폼 하나 조회
		DocumentType oneForm = documentTypeRepository.getById(documentTypeId);
		
		//조회된 값이 있다면, dto로 변경. 없으면, throw 처리
		if (oneForm != null) {
			oneFormDTO.setDocumentTypeId(oneForm.getDocumentTypeId());
			oneFormDTO.setDocumentAuthority(oneForm.getDocumentAuthority());
			oneFormDTO.setDocumentTypeContent(oneForm.getDocumentTypeContent());
			oneFormDTO.setDocumentTypeName(oneForm.getDocumentTypeName());
			oneFormDTO.setDocumentFormName(oneForm.getDocumentFormName());
			
			return oneFormDTO;
			
		}else {
			System.out.println("조회된 폼이 없어서, throw 처리");
			return null;
		}
	
	}

	/**첨부파일 관련 코드*/
	//첨부파일 업로드
	@Override
	public AttachedFile_AttachedFileDTO uploadFiles(Long approvalID, MultipartFile file) {
		AttachedFile attachedFile = new AttachedFile();
		
		//ApprovalDocument 객체 조회
		ApprovalDocument approvalDocument = approvalDocumentRepository.getById(approvalID);
		
		String result = "";
		
		//첨부파일 DB 저장
		try {
			attachedFile.setFileName(file.getName());
			attachedFile.setActualFileName(file.getOriginalFilename());
			attachedFile.setDocument(approvalDocument);
			attachedFile.setFileSize(file.getSize());
			attachedFile.setFileData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AttachedFile saveAttachedFile = attachedFileRepository.save(attachedFile);

		AttachedFile_AttachedFileDTO attachedFileDTO = new AttachedFile_AttachedFileDTO();
		//엔티티 -> dto
		attachedFileDTO.setActualFileName(saveAttachedFile.getActualFileName());
		attachedFileDTO.setDocumentType(saveAttachedFile.getDocument());
		attachedFileDTO.setFileId(saveAttachedFile.getFileId());
		attachedFileDTO.setFileName(saveAttachedFile.getFileName());
		attachedFileDTO.setFileSize(saveAttachedFile.getFileSize());
		
	    return attachedFileDTO;
	}
	
	//첨부파일 삭제
	public String deleteFile(Long fileID) {
		
		AttachedFile attachedFile = attachedFileRepository.getById(fileID);
			
		String result = "";
		
		if (attachedFile != null) {
			attachedFileRepository.delete(attachedFile);
			result = "첨부파일 삭제 성공";
		}else {
			result = "해당 file이 존재하지 않아 첨부파일 삭제 실패";
		}
		
		return result;
	}

	/**전자결재 관련 코드*/
	//전자결재 수행(Create)
	@Override
	public ApprovalDocument_ApprovalDocumentDTO saveApproval(ApprovalDocument_ApprovalDocumentDTO approvalDocumentDTO) {
		
		//사용자 입력값 db에 저장
		ApprovalDocument approvalDocument = new ApprovalDocument();
		/**
		approvalDocument.setApprovalPathString(approvalDocumentDTO.getApprovalPathString());
		approvalDocument.setApprovalStage(approvalDocumentDTO.getApprovalStage());
		approvalDocument.setContent(approvalDocumentDTO.getContent());
		approvalDocument.setCreatedAt(approvalDocumentDTO.getCreatedAt());
		approvalDocument.setDepartmentName(approvalDocumentDTO.getDepartmentName());
		approvalDocument.setDocumentId(approvalDocumentDTO.getDocumentId());
		approvalDocument.setDocumentType(approvalDocumentDTO.getDocumentType());
		approvalDocument.setEmployee(approvalDocumentDTO.getEmployee());
		approvalDocument.setTitle(approvalDocumentDTO.getTitle());
		approvalDocument.setUpdatedAt(approvalDocumentDTO.getUpdatedAt());
		
		approvalDocumentRepository.save(approvalDocument);
		
		//엔티티 -> dto
		//엔티티 EMPLOYEE 부분 조금 더 줄일 수 있는데, 너무 늘려놔서 수정 요망
		ApprovalDocument_ApprovlaDocumentDTO saveApprovalDocumentDTO = new ApprovalDocument_ApprovlaDocumentDTO();
		saveApprovalDocumentDTO.setApprovalPathString(approvalDocument.getApprovalPathString());
		saveApprovalDocumentDTO.setApprovalStage(approvalDocument.getApprovalStage());
		saveApprovalDocumentDTO.setContent(approvalDocument.getContent());
		saveApprovalDocumentDTO.setCreatedAt(approvalDocument.getCreatedAt());
		saveApprovalDocumentDTO.setDepartmentName(approvalDocument.getDepartmentName());
		saveApprovalDocumentDTO.setDocumentId(approvalDocument.getDocumentId());
		saveApprovalDocumentDTO.setDocumentType(approvalDocument.getDocumentType());
		saveApprovalDocumentDTO.setEmployee(approvalDocument.getEmployee());
		saveApprovalDocumentDTO.setTitle(approvalDocument.getTitle());
		saveApprovalDocumentDTO.setUpdatedAt(approvalDocument.getUpdatedAt());
		**/
		return null;
	}

	
	//전자결재 리스트 (작성자가 올린 기안 문서함)
	@Override
	public List<ApprovalDocument> selectApprovalList() {
		List<ApprovalDocument> approvalDocumentList = approvalDocumentRepository.findAll();
		return approvalDocumentList;
	}
	
	//전자결재 하나 확인하기 (작성자가 올린 기안 문서함 중 작성자가 하나 선택한 기안)

	@Override
	public ApprovalDocument_ApprovalDocumentDTO checkApproval(Long approval_id) {
		
		ApprovalDocument oneApproval = approvalDocumentRepository.getById(approval_id);
		
		ApprovalDocument_ApprovalDocumentDTO oneApprovalDTO = new ApprovalDocument_ApprovalDocumentDTO();
				/**
		if (oneApproval != null) {
			oneApprovalDTO.setApprovalPathString(oneApproval.getApprovalPathString());
			oneApprovalDTO.setApprovalStage(oneApproval.getApprovalStage());
			oneApprovalDTO.setContent(oneApproval.getContent());
			oneApprovalDTO.setCreatedAt(oneApproval.getCreatedAt());
			oneApprovalDTO.setDepartmentName(oneApproval.getDepartmentName());
			oneApprovalDTO.setDocumentId(oneApproval.getDocumentId());
			oneApprovalDTO.setDocumentType(oneApproval.getDocumentType());
			oneApprovalDTO.setEmployee(oneApproval.getEmployee());
			oneApprovalDTO.setTitle(oneApproval.getTitle());
			oneApprovalDTO.setUpdatedAt(oneApproval.getUpdatedAt());
		}
		**/
		return null;
	}
	
	//전자결재 수정하기 _ 결재자가 결재하기 전에 수정 (결재자가 결재하기 전이라는 조건문 달아야함)
	public ApprovalDocument_ApprovalDocumentDTO updateApproval(Long approval_id, ApprovalDocument_ApprovalDocumentDTO approvalDocumentDTO) {
		
		//조회하기
		ApprovalDocument oneApproval = approvalDocumentRepository.getById(approval_id);
		/**
		//엔티티에 수정된 값 세팅
		ApprovalDocument updateApprovalDocument = new ApprovalDocument();
		if (oneApproval != null) {
			updateApprovalDocument.setApprovalPathString(approvalDocumentDTO.getApprovalPathString());
			updateApprovalDocument.setApprovalStage(approvalDocumentDTO.getApprovalStage());
			updateApprovalDocument.setContent(approvalDocumentDTO.getContent());
			updateApprovalDocument.setCreatedAt(approvalDocumentDTO.getCreatedAt());
			updateApprovalDocument.setDepartmentName(approvalDocumentDTO.getDepartmentName());
			updateApprovalDocument.setDocumentId(approvalDocumentDTO.getDocumentId());
			updateApprovalDocument.setDocumentType(approvalDocumentDTO.getDocumentType());
			updateApprovalDocument.setEmployee(approvalDocumentDTO.getEmployee());
			updateApprovalDocument.setTitle(approvalDocumentDTO.getTitle());
			updateApprovalDocument.setUpdatedAt(approvalDocumentDTO.getUpdatedAt());
		}
		
		//엔티티 -> dto로 객체 변환
		ApprovalDocument_ApprovlaDocumentDTO updateApprovalDocumentDTO = new ApprovalDocument_ApprovlaDocumentDTO();
		updateApprovalDocumentDTO.setApprovalPathString(updateApprovalDocument.getApprovalPathString());
		updateApprovalDocumentDTO.setApprovalStage(updateApprovalDocument.getApprovalStage());
		updateApprovalDocumentDTO.setContent(updateApprovalDocument.getContent());
		updateApprovalDocumentDTO.setCreatedAt(updateApprovalDocument.getCreatedAt());
		updateApprovalDocumentDTO.setDepartmentName(updateApprovalDocument.getDepartmentName());
		updateApprovalDocumentDTO.setDocumentId(updateApprovalDocument.getDocumentId());
		updateApprovalDocumentDTO.setDocumentType(updateApprovalDocument.getDocumentType());
		updateApprovalDocumentDTO.setEmployee(updateApprovalDocument.getEmployee());
		updateApprovalDocumentDTO.setTitle(updateApprovalDocument.getTitle());
		updateApprovalDocumentDTO.setUpdatedAt(updateApprovalDocument.getUpdatedAt());
		**/
		return null;
	
	}
	
	//전자결재 삭제하기 _ 결재자가 결재하기 전에 삭제 (결재자가 결재하기 전이라는 조건문 달아야함)
	public String deleteApproval(Long approval_id) {
		//조회하기
		ApprovalDocument oneApproval = approvalDocumentRepository.getById(approval_id);
		
		String result = "";
		if (oneApproval != null) {
			approvalDocumentRepository.deleteById(approval_id);
			result = "전자결재 삭제 완료";
		}else {
			result = "조회된 전자결재 내역이 없어 전자결재 삭제 실패";
		}
		
		return result;	
	}

}
