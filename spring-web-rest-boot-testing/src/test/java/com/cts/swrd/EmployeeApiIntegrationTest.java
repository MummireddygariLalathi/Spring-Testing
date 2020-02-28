package com.cts.swrd;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.swrd.dao.EmployeeRepository;
import com.cts.swrd.model.Department;
import com.cts.swrd.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK,
classes=SpringWebRestBootDemoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations ="classpath:application-test.properties")
public class EmployeeApiIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private EmployeeRepository repository;
	
	@Before
	public void setUp() {
		Employee emp=new Employee("lal","lalu",45000,LocalDate.now(),Department.DEVELOPMENT,"1234567890","lal@gmail.com");
		
		repository.save(emp);
	}
	@After
	public void tearDown() {
		repository.deleteAll();
	}
	
	@Test
	public void whenFindAll_thenReturnJsonArray() throws Exception{
		mvc.perform(get("/emps").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",hasSize(1)))
				.andExpect(jsonPath("$[0].mobileNumber",is("1234567890")));
	}

}
