package com.cts.swrd;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

import static org.hamcrest.Matchers.is;
import com.cts.swrd.api.EmployeeAPI;
import com.cts.swrd.model.Department;
import com.cts.swrd.model.Employee;
import com.cts.swrd.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeAPI.class)
public class EmployeeApiUnitTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private EmployeeService service;
	
	@Test
	public void whenFindAll_thenReturnJsonArray() throws Exception{
		 
		
		Employee emp=new Employee("lal","lalu",45000,LocalDate.now(),Department.DEVELOPMENT,"1234567890","lal@gmail.com");
		List<Employee> allEmployees=Arrays.asList(emp);
		
		given(service.findAll()).willReturn(allEmployees);
		
		mvc.perform(get("/emps").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",hasSize(1)))
				.andExpect(jsonPath("$[0].mobileNumber",is(emp.getMobileNumber())));
	}

}
