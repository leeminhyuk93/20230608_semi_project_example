package com.javalab.hr2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalab.hr2.dao.EmployeeDao;
import com.javalab.hr2.vo.Criteria;
import com.javalab.hr2.vo.Department;
import com.javalab.hr2.vo.EmployeeCommonDto;
import com.javalab.hr2.vo.Employees;
import com.javalab.hr2.vo.Job;



@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao dao;

	public List<EmployeeCommonDto> getEmployeesList(EmployeeCommonDto dto) {
		return dao.getEmployeesList(dto);
	}

	@Override
	public EmployeeCommonDto getEmployees(int employeeId) {
		return dao.getEmployees(employeeId);
	}

	@Override
	public int register(Employees emp) {
		return dao.register(emp);
	}

	@Override
	public int getTotalEmployees(Criteria cri) {
		return dao.getTotalEmployees(cri);
	}
	
	@Override
	public List<Job> getJobList() {
		return dao.getJobList();
	}
	
	@Override
	public List<Department> getDepartmentList() {
		
		return dao.getDepartmentList();
	}
	
	@Override
	public void ModifyEmployees(Employees emp) {
		dao.ModifyEmployees(emp);
	}
	
	@Override
	public void deleteEmployees(int employeeId) {
		dao.deleteEmployees(employeeId);
	}

}