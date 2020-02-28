package com.cts.swrd.service;

import java.time.LocalDate;
import java.util.List;



import com.cts.swrd.exception.EmployeeException;
import com.cts.swrd.model.Department;
import com.cts.swrd.model.Employee;

public interface EmployeeService {
	
	Employee add(Employee emp)throws EmployeeException;
	Employee save(Employee emp)throws EmployeeException;
	void deleteById(Long empId);
	
	Employee findById(Long empId);
	Employee findByMobileNumber(String mobileNumber);
	Employee findEmail(String email);
	
	List<Employee> findAll();
	List<Employee> findAllByDept(Department dept);
	List<Employee> findAllByJoinDate(LocalDate joinDate);
	List<Employee> findAllByBasicRange(double lb,double ub);

}
