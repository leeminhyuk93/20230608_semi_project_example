package com.javalab.hr2.service;

import java.util.List;

import com.javalab.hr2.vo.Criteria;
import com.javalab.hr2.vo.Department;
import com.javalab.hr2.vo.EmployeeCommonDto;
import com.javalab.hr2.vo.Employees;
import com.javalab.hr2.vo.Job;


public interface EmployeeService {

	List<EmployeeCommonDto> getEmployeesList(EmployeeCommonDto dto);
	EmployeeCommonDto getEmployees(int employeeId);
	int register(Employees emp);
	int getTotalEmployees(Criteria cri);
	List<Job> getJobList();
	List<Department> getDepartmentList();
	void ModifyEmployees(Employees emp);
	void deleteEmployees(int employeeId);

}