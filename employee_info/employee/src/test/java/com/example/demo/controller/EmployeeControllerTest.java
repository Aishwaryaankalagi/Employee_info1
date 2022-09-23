package com.example.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.demo.model.Employee_info;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
class EmployeeControllerTest {
    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllEmployees2() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("aishwarya@gmail.com");
        employee_info.setFirstName("aishwarya");
        employee_info.setId(123L);
        employee_info.setLastName("ankalagi");
        when(employeeService.getEmployeeById(anyLong())).thenReturn(employee_info);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/{id}", 123L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":123,\"firstName\":\"aishwarya\",\"lastName\":\"ankalagi\",\"emailId\":\"aishwarya@gmail.com\"}"));
    }
    //updateEmployee(long, Employee_info)}
    @Test
    void testUpdateEmployee() throws Exception {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("aishwarya@gmail.com");
        employee_info.setFirstName("aishwarya");
        employee_info.setId(123L);
        employee_info.setLastName("ankalagi");
        when(employeeService.updateEmployee((Employee_info) any(), anyLong())).thenReturn(employee_info);

        Employee_info employee_info1 = new Employee_info();
        employee_info1.setEmailId("aishwarya@gmail.com");
        employee_info1.setFirstName("aishwarya");
        employee_info1.setId(123L);
        employee_info1.setLastName("ankalagi");
        String content = (new ObjectMapper()).writeValueAsString(employee_info1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":123,\"firstName\":\"aishwarya\",\"lastName\":\"ankalagi\",\"emailId\":\"aishwarya@gmail.com\"}"));
    }
              //deleteEmployee(long)
    @Test
    void testDeleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployee(anyLong());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/{id}", 123L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Employee deleted successfully!."));
    }
                                     //deleteEmployee(long)}
    @Test
    void testDeleteEmployee2() throws Exception {
        doNothing().when(employeeService).deleteEmployee(anyLong());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/{id}", 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Employee deleted successfully!."));
    }
                           //saveEmployee(Employee_info)}
    @Test
    void testSaveEmployee() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(new ArrayList<>());

        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("aishwarya@gmail.com");
        employee_info.setFirstName("aishwarya");
        employee_info.setId(123L);
        employee_info.setLastName("ankalagi");
        String content = (new ObjectMapper()).writeValueAsString(employee_info);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

