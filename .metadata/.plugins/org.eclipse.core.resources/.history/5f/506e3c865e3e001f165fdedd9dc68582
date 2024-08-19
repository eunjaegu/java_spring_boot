package com.intrabucks.approval.data.reactdto;

import com.intrabucks.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 결재라인 DTO:  기안자&결재자라인
 * @author 구은재
 * @version 1.0 
 * 2024-07-06
**/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Approval1_Approval1DTO {	

	 private ApprovalStepDTO[] approvalSteps; // 선택된 직원들의 결재 정보 배열

	    @Data
	    @NoArgsConstructor
	    @AllArgsConstructor
	    @Builder
	    @Getter
	    public static class ApprovalStepDTO {
	        private Employee employee; // 결재자 직원 정보
	        private String approvalState; // 결재 상태
	        private String deptCode; // 결재 상태
	        private Long empId; // 직원코드
	        private String empName;  //직원이름
	    }

	    public ApprovalStepDTO[] getApprovalSteps() {
	        return approvalSteps;	
	    }
	
}
