package com.javalab.hr2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javalab.hr2.vo.Criteria;
import com.javalab.hr2.vo.Department;
import com.javalab.hr2.vo.EmployeeCommonDto;
import com.javalab.hr2.vo.Employees;
import com.javalab.hr2.vo.Job;



/*
 * 매퍼 인터페이스 : Service Layer와 매퍼xml(sql쿼리문)을 연결해주는 역할(bridge)
 */
@Mapper
public interface EmployeeDao {
	
	List<EmployeeCommonDto> getEmployeesList(EmployeeCommonDto dto);
	EmployeeCommonDto getEmployees(int employeeId);
	int getTotalEmployees(Criteria cri);	// 페이징을 위한 사원숫자 조회	
	int register(Employees emp);
	List<Department> getDepartmentList();
	List<Job> getJobList();
	void ModifyEmployees(Employees emp);
	void deleteEmployees(int employeeId);

}