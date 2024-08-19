package com.intrabucks.employee.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.intrabucks.employee.data.reactdto.Employee_EmployeeDTO;
import com.intrabucks.employee.data.repository.DepartmentRepository;
import com.intrabucks.employee.data.repository.EmployeeRepository;
import com.intrabucks.entity.Department;
import com.intrabucks.entity.Employee;
import com.intrabucks.entity.Quitter;
import com.intrabucks.quitter.data.repository.QuitterRepository;

/**
 * 직원(Employee) ServiceImpl : deleteEmployeeAndMoveToQuitter 추가
 * @author 구은재
 * @version 1.0 
 * 2024-06-30
 **/

@Service
public class EmployeeServiceImpl implements EmployeeService {

	public final EmployeeRepository employeeRepository;
	public final DepartmentRepository departmentRepository;
	public final QuitterRepository quitterRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, QuitterRepository quitterRepository ) {
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
		this.quitterRepository = quitterRepository;
		
	}

	/**직원신규등록*/
	@Override
	public Long createEmployee(Employee_EmployeeDTO employeeDTO) {
		String deptCode = employeeDTO.getDeptCode(); // 수정된 부분: deptCode 추출
        Department department = departmentRepository.findByDeptCode(deptCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Department Code: " + deptCode));
		
		Employee employee = new Employee();
        employee.setEmpName(employeeDTO.getEmpName());
        employee.setEmpPassword(passwordEncoder.encode(employeeDTO.getEmpPassword()));
        employee.setEmpEmail(employeeDTO.getEmpEmail());
        employee.setEmpPhone(employeeDTO.getEmpPhone());
        employee.setEmpAddress(employeeDTO.getEmpAddress());
        employee.setEmpJoinDate((Date)employeeDTO.getEmpJoinDate());
        employee.setEmpPosition(employeeDTO.getEmpPosition());
        employee.setDepartment(department);
        employee.setWorkState(employeeDTO.getWorkState());

        employeeRepository.save(employee);
        
        return employee.getEmpId();
	}
	
	/**직원전체&페이징조회*/
	@Override
	public Page<Employee_EmployeeDTO> ListEmployee(String empName, Pageable pageable) {

		int page = pageable.getPageNumber();
	    if (page < 0) {
	        page = 0;
	    }
		
		final int pageSize = 10;
    	
		pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "empJoinDate"));
    	
    	Page<Employee> employeePage;
    	
    	if (empName == null || empName.isEmpty()) {
            // empName이 null이거나 빈 문자열인 경우 모든 직원을 조회
            employeePage = employeeRepository.findAll(pageable);
        } else {
            // empName이 주어진 경우 해당 이름을 포함하는 직원을 조회
            employeePage = employeeRepository.findByEmpNameContainingIgnoreCase(empName, pageable);
        }   
    	
    	return employeePage.map(employee ->
    	new Employee_EmployeeDTO(
    			employee.getEmpId(), 
    			employee.getEmpName(), 
    			employee.getEmpPassword(),
                employee.getEmpEmail(), 
                employee.getEmpPhone(), 
                employee.getEmpAddress(), 
                (Date) employee.getEmpJoinDate(),
                employee.getEmpPosition(),  
                employee.getDepartment().getDeptCode(),
                employee.getWorkState()
            )
    	);
    }
	
	/**직원ID조회*/
	@Override
	public Employee_EmployeeDTO selectOneEmployee(Long empId) {
		// 직원 ID로 직원 정보를 조회합니다.
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Employee ID: " + empId));
       
        return new Employee_EmployeeDTO(
        		employee.getEmpId(),
        		employee.getEmpName(),
        		employee.getEmpPassword(),
                employee.getEmpEmail(), 
                employee.getEmpPhone(),
                employee.getEmpAddress(),
                (Date) employee.getEmpJoinDate(),
                employee.getEmpPosition(),
                employee.getDepartment().getDeptCode(),
                employee.getWorkState());
	}
    
	/**직원정보수정*/
	@Override
	public Long updateEmployee(Employee_EmployeeDTO employeeDTO) {
		Employee employee = employeeRepository.findById(employeeDTO.getEmpId())
				.orElseThrow(()-> new IllegalArgumentException("Invalid Employee ID:" + employeeDTO.getEmpId()));
		
		String deptCode = employeeDTO.getDeptCode(); // 수정된 부분: deptCode 추출
        Department department = departmentRepository.findByDeptCode(deptCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Department Code: " + deptCode));
		
        employee.setEmpName(employeeDTO.getEmpName());
        employee.setEmpPassword(passwordEncoder.encode(employeeDTO.getEmpPassword()));
        employee.setEmpEmail(employeeDTO.getEmpEmail());
        employee.setEmpPhone(employeeDTO.getEmpPhone());
        employee.setEmpAddress(employeeDTO.getEmpAddress());
        employee.setEmpJoinDate((Date)employeeDTO.getEmpJoinDate());
        employee.setEmpPosition(employeeDTO.getEmpPosition());
        employee.setDepartment(department);
        employee.setWorkState(employeeDTO.getWorkState());

        employeeRepository.save(employee);

        return employee.getEmpId();
	}

	
	/**직원목록을 퇴사자목록으로 옮긴 후 삭제*/
	@Override
	@Transactional
	public boolean deleteEmployeeAndMoveToQuitter(Long empId) {
		
		 // 직원 정보 조회
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        if (!optionalEmployee.isPresent()) {
            return false; // 직원이 존재하지 않는 경우 처리 실패
        }
        
        Employee employee = optionalEmployee.get();
        
     // 부서 정보 조회
        Department department = employee.getDepartment();
        if (department == null) {
            throw new IllegalStateException("Employee does not have a department assigned.");
        }

        // 직원 정보를 퇴사자 DTO로 변환
        Quitter quitter = new Quitter();
        quitter.setQuitId(employee.getEmpId());
        quitter.setQuitName(employee.getEmpName());
        quitter.setQuitEmail(employee.getEmpEmail());
        quitter.setQuitPhone(employee.getEmpPhone());
        quitter.setQuitAddress(employee.getEmpAddress());
        quitter.setQuitJoindate(employee.getEmpJoinDate());
        quitter.setQuitPosition(employee.getEmpPosition());
        quitter.setDepartment(department); // 퇴사자에 부서 정보 설정
        quitter.setQuitLeavingdate(new Date(System.currentTimeMillis()));

     // 퇴사자 정보 저장
        quitterRepository.save(quitter);

        // 직원 정보 삭제
        employeeRepository.delete(employee);

        return true; // 성공적으로 처리됨
    }
	
	/**직원전체조회*/
	@Override
	public List<Employee_EmployeeDTO> listAllEmployees() {
		// 모든 직원 정보를 조회합니다.
		List<Employee> employees = employeeRepository.findAll();
		
		// Employee 엔티티 리스트를 Employee_EmployeeDTO 리스트로 변환합니다.
		   List<Employee_EmployeeDTO> employeeDTO = employees.stream().map(employee ->
           new Employee_EmployeeDTO(
           		employee.getEmpId(), 
       			employee.getEmpName(), 
       			employee.getEmpPassword(),
                   employee.getEmpEmail(), 
                   employee.getEmpPhone(), 
                   employee.getEmpAddress(), 
                   (Date) employee.getEmpJoinDate(),
                   employee.getEmpPosition(),  
                   employee.getDepartment().getDeptCode(),
                   employee.getWorkState()
           )
       ).collect(Collectors.toList());

        return employeeDTO;
	}

	
}
