package com.employee.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.app.dto.Employee;
import com.employee.app.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/")
	public String loginPage1() {
		
		return "login";
	}

	@SuppressWarnings("unused")
	@PostMapping("/login")
	public String login(HttpServletRequest req,Model model) {

		String email = req.getParameter("email");
		String pass = req.getParameter("password");

		Employee e=employeeService.getByEmail(email);
		
		if(e==null) 
		{
			return "login";
		}
		
		if(e!=null) 
		{
			
			if(e.getEmail().equalsIgnoreCase(email) && e.getPassword().equalsIgnoreCase(pass)) 
			{
				List<Employee> elist = employeeService.findAllEmp();
				model.addAttribute("elist", elist);
				return "result";
			}
			else 
			{
				return "login";
			}
		}
		else 
		{
			return "login";
		}
		
	}

	@GetMapping("/signin")
	public String loginPage() {

		return "registration";
	}
	
	@GetMapping("/logine")
	public String loginPageE() {

		return "login";
	}

	@PostMapping("/empsave")
	public String empRegister(Employee ep, Model model) {

		employeeService.saveEmp(ep);

		return "login";
	}

	@GetMapping("/logout")
	public String getRegistPage(HttpServletRequest req) {

		HttpSession ses=req.getSession();
		ses.invalidate();
		return "login";
	}

}
