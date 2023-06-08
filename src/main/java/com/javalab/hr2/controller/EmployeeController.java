package com.javalab.hr2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalab.hr2.service.EmployeeService;
import com.javalab.hr2.vo.Criteria;
import com.javalab.hr2.vo.Department;
import com.javalab.hr2.vo.EmployeeCommonDto;
import com.javalab.hr2.vo.Employees;
import com.javalab.hr2.vo.Job;
import com.javalab.hr2.vo.PageDto;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/emp")
@Slf4j
public class EmployeeController {

	 @Autowired
	 private EmployeeService employeeService;
	
	 // 사원 목록 조회
	 /*
	 @GetMapping("/list1")
	 public String getEmployeeList1(EmployeeCommonDto dto, Model model){
		 List<EmployeeCommonDto> empList = employeeService.getEmployeesList(dto);
		 model.addAttribute("list", empList);
		 //return "/employee/list"; 
		 return "qlist"; 
	 }
	 */
	 
	 /*
	  * 사원 목록 조회
	  *  - @ModelAttribute("cri") Criteria cri 
	  *    : 뷰에서 cri 라는 이름으로 사용
	  */
	 @GetMapping("/list")
	 public String getEmployeeList2(EmployeeCommonDto dto, 
			 						@ModelAttribute("cri") Criteria cri, 
			 						Model model){
		 log.info("pageDto : " + dto.toString());
		 
		 List<EmployeeCommonDto> empList = employeeService.getEmployeesList(dto);
		 model.addAttribute("list", empList);
		 
		int total = employeeService.getTotalEmployees(cri); 
		PageDto pageDto = new PageDto(cri, total);
		  
		log.info("pageDto : " + pageDto.toString()); 
		model.addAttribute("pageMaker", pageDto);

		return "list"; 
	 }

	 // 사원 정보 보기
	 @GetMapping("/read")
	 public String getEmployees(@RequestParam("employeeId") int employeeIdid, Model model) {	
		 EmployeeCommonDto dto = employeeService.getEmployees(employeeIdid);
		 model.addAttribute("employee", dto);		 
		 return "view";
	 }
	 
	// 세팅 정보
	 private void settingInfo(Model model) {
		 	// 직업 아이디와 이름을 가진 리스트를 모델에 저장
			List<Job> jobList = new ArrayList<Job>();
			jobList = employeeService.getJobList();
			
			model.addAttribute("jobList", jobList);
			
			// 부서 아이디와 이름을 가진 리스트를 모델에 저장
			List<Department> departmentList = new ArrayList<Department>();
			departmentList = employeeService.getDepartmentList();
			
			model.addAttribute("departmentList", departmentList);
			
			// 매니저의 사원번호와 매니저 이름을 가져오기 위한 리스트를 모델에 저장
			List<EmployeeCommonDto> empList = new ArrayList<EmployeeCommonDto>();
			empList = employeeService.getEmployeesList(new EmployeeCommonDto());
			
			model.addAttribute("empList", empList);
	 }
	 
	// 사원 등록 폼
	@GetMapping("/register")
	public String register(Employees emp, Model model){
		settingInfo(model);		
		return "form"; 
	}	
	
	// 사원 등록 처리
	@PostMapping("/register")
	public String register(Employees emp){
		
		// 게시물 등록(저장)
		int result = employeeService.register(emp);
		
		// 저장후 목록 출력 컨트롤러 호출, redirect하면 사용자 화면의 주소창이 변경됨.
		return "redirect:list"; 
	}		 
	
	// 사원 정보 수정 폼
	@GetMapping("/edit")
	public String modify(@RequestParam("employeeId") int id, Model model) {
		settingInfo(model);
		EmployeeCommonDto employee = employeeService.getEmployees(id);
		model.addAttribute("employee", employee);
		return "editForm";
	}
	
	// 사원 정보 수정 처리
	@PostMapping("/edit")
	public String modify(@RequestParam("fullName") String fullName, Employees emp, Model model) {
		
		System.out.println("/edit modify Post 실행.");
		emp.setFirstName(fullName.split(" ")[0]);
		emp.setLastName(fullName.split(" ")[1]);
		System.out.println(emp.toString());
		employeeService.ModifyEmployees(emp);
		
		return "redirect:/emp/list";
	}
	
	// 사원 삭제 처리
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id, Model model) {
		System.out.println("사원 정보의 삭제 처리 실행.");
		employeeService.deleteEmployees(id);
		return "redirect:/emp/list";
	}
}