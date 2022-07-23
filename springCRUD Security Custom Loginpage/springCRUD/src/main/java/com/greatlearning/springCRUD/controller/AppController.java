package com.greatlearning.springCRUD.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.springCRUD.model.Employee;
import com.greatlearning.springCRUD.services.EmployeeServices;

@Controller
public class AppController {
	
	@Autowired
	private EmployeeServices service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Employee> listEmp = service.listAll();
		model.addAttribute("listEmployee",listEmp);
		return "index";
	}
	
	@RequestMapping("/new")
	public String newEmployeePage(Model model) {
		Employee emp = new Employee();
		model.addAttribute("employee",emp);
		return "new_employee";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST )
	public String saveEmployee(@ModelAttribute("employee") Employee emp) {
		service.save(emp);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{eid}")
	public ModelAndView showEditEmployeepage(@PathVariable (name="eid") int id) {
		ModelAndView mav=new ModelAndView("edit_employee");
		Employee emp=service.get(id); 
		mav.addObject("employee",emp);
		return mav;
	}
	
	@RequestMapping("/delete/{eid}")
	public String deleteEmployeepage(@PathVariable (name="eid") int id) {
		service.delete(id);
		return "redirect:/";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(authentication==null || authentication instanceof AnonymousAuthenticationToken) {
			return "/login";
		}
		return "redirect:/";
	}
}
