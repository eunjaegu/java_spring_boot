package com.intrabucks.employee.data.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.intrabucks.entity.Employee;

/**
 * 직원(Employee) Repository
 * @author 구은재
 * @version 1.0 
 * 2024-06-30
 **/
/**
 * 직원(Employee) Repository - 조건에 맞는 Employee 찾는 method 만듦
 * @author 이정윤
 * @version 1.1
 * 2024-07-03
 **/

/**
 * 직원(Employee) Repository - 조건에 맞는 Employee 찾는 method 만듦
 * @author 최유빈
 * @version 1.2
 * 2024-07-04
 **/

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	 
	@Query("SELECT e FROM Employee e WHERE LOWER(e.empName) LIKE LOWER(CONCAT('%', :empName, '%'))")
	 Page<Employee> findByEmpNameContainingIgnoreCase(String empName, Pageable pageable);
	 
	 Optional<Employee> findByEmpNameAndEmpPasswordAndEmpEmail(String empName, String empPassword, String empEmail);
	 
	//Find Emp
	Employee findByEmpId(Long empId);
	 
	 //empid로 직원 1명 조회
	 Employee findByEmpEmail(String emp_email);
	 
}

