package com.intrabucks.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intrabucks.employee.data.reactdto.Employee_EmployeeDTO;
import com.intrabucks.employee.service.EmployeeService;
import com.intrabucks.quitter.service.QuitterService;

/**
 * 직원(Employee) Controller : deleteEmployeeAndMoveToQuitter 추가(Talend완료)
 * @author 구은재
 * @version 1.0 
 * 2024-07-01
**/

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:3000") // 클라이언트 애플리케이션의 주소
public class EmployeeController {

	private final EmployeeService employeeService;
	private final QuitterService quitterService;
	

	@Autowired
	public EmployeeController(EmployeeService employeeService, QuitterService quitterService) {
		this.employeeService = employeeService;
		this.quitterService = quitterService;
	}

	/**직원등록*/
	@PostMapping("/create")
	public ResponseEntity<Long> createEmployee(@RequestBody Employee_EmployeeDTO employeeDTO) {
		Long empId = employeeService.createEmployee(employeeDTO);
		return ResponseEntity.ok().body(empId);
	}

	/**직원전체조회 페이징*/
	@GetMapping("/select")
	public ResponseEntity<Page<Employee_EmployeeDTO>> getAllEmployees(@RequestParam(required = false) String empName,
			@RequestParam(required = false) Integer page, @RequestParam(defaultValue = "10") int size) {

		int pageNumber = (page != null && page > 0) ? page - 1 : 0;
		PageRequest pageable = PageRequest.of(pageNumber, size, Sort.by(Sort.Direction.DESC, "empJoinDate"));
		Page<Employee_EmployeeDTO> employees = employeeService.ListEmployee(empName, pageable);
		return ResponseEntity.ok().body(employees);
	}

	/**직원ID조회*/
	@GetMapping("/{empId}")
	public ResponseEntity<Employee_EmployeeDTO> selectOneEmployee(@PathVariable Long empId) {
		Employee_EmployeeDTO employeeDTO = employeeService.selectOneEmployee(empId);

		if (employeeDTO != null) {
			return ResponseEntity.ok().body(employeeDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**직원정보수정*/
	@PutMapping("/update/{empId}")
	public ResponseEntity<Long> updateEmployee(@RequestBody Employee_EmployeeDTO employeeDTO) {
		Long empId = employeeService.updateEmployee(employeeDTO);
		return ResponseEntity.ok().body(empId);
	}

	/**직원목록을 퇴사자목록으로 저장 후 삭제*/
	@DeleteMapping("/delete/{empId}")
    public ResponseEntity<Void> deleteEmployeeAndMoveToQuitter(@PathVariable Long empId) {
		// 직원 서비스를 통해 empId에 해당하는 직원 데이터를 삭제하고, 퇴직자 데이터로 이동시키는 작업을 수행
		boolean success = employeeService.deleteEmployeeAndMoveToQuitter(empId);
        
		// 삭제 작업이 성공하면 HTTP 상태 코드 200(OK)을 응답
		if (success) {
            return ResponseEntity.ok().build();

            
        } else {
        	// 삭제할 직원 데이터가 존재하지 않는 경우 HTTP 상태 코드 404(NOT FOUND)을 응답
            return ResponseEntity.notFound().build();
        }
    }
	
	/**직원전체조회*/
	@GetMapping("selectOnly")
	public ResponseEntity<List<Employee_EmployeeDTO>> getAllEmployees() {
	    List<Employee_EmployeeDTO> employees = employeeService.listAllEmployees();
	    return ResponseEntity.ok().body(employees);
	}
	
	
    
}
