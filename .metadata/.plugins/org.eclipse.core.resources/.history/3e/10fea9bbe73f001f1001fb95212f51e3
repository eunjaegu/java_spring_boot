package com.intrabucks.approval.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.intrabucks.approval.data.dto.reactdto.ApprovalDocument_ApprovalDocumentDTO;
import com.intrabucks.approval.data.dto.reactdto.AttachedFile_AttachedFileDTO;
import com.intrabucks.approval.data.dto.reactdto.DocumentType_DocumentTypeDTO;
import com.intrabucks.entity.ApprovalDocument;
import com.intrabucks.entity.DocumentType;

public interface ApprovalService {

	//FORM lIST 띄우기
	List<DocumentType> selectFormList();
	
	//FORM 하나 상세보기 (폼 띄우기)
	DocumentType_DocumentTypeDTO selectOneForm(Long documentTypeId);
	
	//파일 업로드
	AttachedFile_AttachedFileDTO uploadFiles(Long approvalID, MultipartFile file);
	
	//파일 업로드 취소
	String deleteFile(Long approvalID);
	
	//결재
	ApprovalDocument_ApprovalDocumentDTO saveApproval(ApprovalDocument_ApprovalDocumentDTO approvalDocumentDTO);
	
	//결재 문서 리스트
	List<ApprovalDocument> selectApprovalList(String empEmail);
	
	//결재 문서 하나 확인하기
	ApprovalDocument_ApprovalDocumentDTO checkApproval(Long approval_id);
	
	//결재 문서 수정하기
	ApprovalDocument_ApprovalDocumentDTO updateApproval(Long approval_id, ApprovalDocument_ApprovalDocumentDTO approvalDocumentDTO);
	
	//결재 문서 삭제하기 
	
}
