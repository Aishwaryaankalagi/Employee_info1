package com.example.demo.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.exception.ResouceNotFoundException;
import com.example.demo.model.Employee_info;
import com.example.demo.repository.EmployeeRepository;

@ContextConfiguration(classes = {EmployeeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {
    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    void testSaveEmployee() {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("aishwarya@gmail.com");
        employee_info.setFirstName("aishwarya");
        employee_info.setId(123L);
        employee_info.setLastName("ankalagi");
        when(employeeRepository.save((Employee_info) any())).thenReturn(employee_info);

        Employee_info employee_info1 = new Employee_info();
        employee_info1.setEmailId("aishwarya@gmail.com");
        employee_info1.setFirstName("aishwarya");
        employee_info1.setId(123L);
        employee_info1.setLastName("ankalagi");
        assertSame(employee_info, employeeServiceImpl.saveEmployee(employee_info1));
        verify(employeeRepository).save((Employee_info) any());
    }

    @Test
    void testSaveEmployee2() {
        when(employeeRepository.save((Employee_info) any())).thenThrow(new ResouceNotFoundException("An error occurred"));

        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("aishwarya@gmail.com");
        employee_info.setFirstName("aishwarya");
        employee_info.setId(123L);
        employee_info.setLastName("aishwarya");
        assertThrows(ResouceNotFoundException.class, () -> employeeServiceImpl.saveEmployee(employee_info));
        verify(employeeRepository).save((Employee_info) any());
    }

    @Test
    void testGetAllEmployees() {
        ArrayList<Employee_info> employee_infoList = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employee_infoList);
        List<Employee_info> actualAllEmployees = employeeServiceImpl.getAllEmployees();
        assertSame(employee_infoList, actualAllEmployees);
        assertTrue(actualAllEmployees.isEmpty());
        verify(employeeRepository).findAll();
    }

    @Test
    void testGetAllEmployees2() {
        when(employeeRepository.findAll()).thenThrow(new ResouceNotFoundException("An error occurred"));
        assertThrows(ResouceNotFoundException.class, () -> employeeServiceImpl.getAllEmployees());
        verify(employeeRepository).findAll();
    }
    
     @Test
    void testGetEmployeeById() {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("aishwarya@gmail.com");
        employee_info.setFirstName("aishwarya");
        employee_info.setId(123L);
        employee_info.setLastName("aishwarya");
        Optional<Employee_info> ofResult = Optional.of(employee_info);
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(employee_info, employeeServiceImpl.getEmployeeById(123L));
        verify(employeeRepository).findById((Long) any());
    }

    @Test
    void testGetEmployeeById2() {
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResouceNotFoundException.class, () -> employeeServiceImpl.getEmployeeById(123L));
        verify(employeeRepository).findById((Long) any());
    }

    @Test
    void testGetEmployeeById3() {
        when(employeeRepository.findById((Long) any())).thenThrow(new ResouceNotFoundException("An error occurred"));
        assertThrows(ResouceNotFoundException.class, () -> employeeServiceImpl.getEmployeeById(123L));
        verify(employeeRepository).findById((Long) any());
    }

    @Test
    void testUpdateEmployee() {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("aishwarya@gmail.com");
        employee_info.setFirstName("aishwarya");
        employee_info.setId(123L);
        employee_info.setLastName("ankalagi");
        Optional<Employee_info> ofResult = Optional.of(employee_info);

        Employee_info employee_info1 = new Employee_info();
        employee_info1.setEmailId("aishwarya@gmail.com");
        employee_info1.setFirstName("aishwarya");
        employee_info1.setId(123L);
        employee_info1.setLastName("ankalagi");
        when(employeeRepository.save((Employee_info) any())).thenReturn(employee_info1);
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);

        Employee_info employee_info2 = new Employee_info();
        employee_info2.setEmailId("aishwarya@gmail.com");
        employee_info2.setFirstName("aishwarya");
        employee_info2.setId(123L);
        employee_info2.setLastName("ankalagi");
        Employee_info actualUpdateEmployeeResult = employeeServiceImpl.updateEmployee(employee_info2, 123L);
        assertSame(employee_info, actualUpdateEmployeeResult);
        assertEquals("aishwarya@gmail.com", actualUpdateEmployeeResult.getEmailId());
        assertEquals("ankalagi", actualUpdateEmployeeResult.getLastName());
        assertEquals("aishwarya", actualUpdateEmployeeResult.getFirstName());
        verify(employeeRepository).save((Employee_info) any());
        verify(employeeRepository).findById((Long) any());
    }

    @Test
    void testUpdateEmployee2() {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("aishwarya@gmail.com");
        employee_info.setFirstName("aishwarya");
        employee_info.setId(123L);
        employee_info.setLastName("ankalagi");
        Optional<Employee_info> ofResult = Optional.of(employee_info);
        when(employeeRepository.save((Employee_info) any())).thenThrow(new ResouceNotFoundException("An error occurred"));
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);

        Employee_info employee_info1 = new Employee_info();
        employee_info1.setEmailId("42");
        employee_info1.setFirstName("Jane");
        employee_info1.setId(123L);
        employee_info1.setLastName("Doe");
        assertThrows(ResouceNotFoundException.class, () -> employeeServiceImpl.updateEmployee(employee_info1, 123L));
        verify(employeeRepository).save((Employee_info) any());
        verify(employeeRepository).findById((Long) any());
    }

    @Test
    void testUpdateEmployee3() {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("aishwarya@gmail.com");
        employee_info.setFirstName("aishwarya");
        employee_info.setId(123L);
        employee_info.setLastName("ankalagi");
        when(employeeRepository.save((Employee_info) any())).thenReturn(employee_info);
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.empty());

        Employee_info employee_info1 = new Employee_info();
        employee_info1.setEmailId("aishwarya@gmail.com");
        employee_info1.setFirstName("aishwarya");
        employee_info1.setId(123L);
        employee_info1.setLastName("aishwarya");
        assertThrows(ResouceNotFoundException.class, () -> employeeServiceImpl.updateEmployee(employee_info1, 123L));
        verify(employeeRepository).findById((Long) any());
    }

    @Test
    void testDeleteEmployee() {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("aishwarya@gmail.com");
        employee_info.setFirstName("aishwarya");
        employee_info.setId(123L);
        employee_info.setLastName("ankalagi");
        Optional<Employee_info> ofResult = Optional.of(employee_info);
        doNothing().when(employeeRepository).deleteById((Long) any());
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);
        employeeServiceImpl.deleteEmployee(123L);
        verify(employeeRepository).findById((Long) any());
        verify(employeeRepository).deleteById((Long) any());
        assertTrue(employeeServiceImpl.getAllEmployees().isEmpty());
    }

    @Test
    void testDeleteEmployee2() {
        Employee_info employee_info = new Employee_info();
        employee_info.setEmailId("aishwarya@gmail.com");
        employee_info.setFirstName("aishwarya");
        employee_info.setId(123L);
        employee_info.setLastName("ankalagi");
        Optional<Employee_info> ofResult = Optional.of(employee_info);
        doThrow(new ResouceNotFoundException("An error occurred")).when(employeeRepository).deleteById((Long) any());
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResouceNotFoundException.class, () -> employeeServiceImpl.deleteEmployee(123L));
        verify(employeeRepository).findById((Long) any());
        verify(employeeRepository).deleteById((Long) any());
    }

    @Test
    void testDeleteEmployee3() {
        doNothing().when(employeeRepository).deleteById((Long) any());
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResouceNotFoundException.class, () -> employeeServiceImpl.deleteEmployee(123L));
        verify(employeeRepository).findById((Long) any());
    }
}

