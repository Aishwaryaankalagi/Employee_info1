package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Employee_infoTest {

    @Test
    void testConstructor() {
        Employee_info actualEmployee_info = new Employee_info();
        actualEmployee_info.setEmailId("aishwarya@gmail.com");
        actualEmployee_info.setFirstName("aishwarya");
        actualEmployee_info.setId(123L);
        actualEmployee_info.setLastName("ankalagi");
        assertEquals("aishwarya@gmail.com", actualEmployee_info.getEmailId());
        assertEquals("aishwarya", actualEmployee_info.getFirstName());
        assertEquals(123L, actualEmployee_info.getId());
        assertEquals("ankalagi", actualEmployee_info.getLastName());
    }

  
    @Test
    void testConstructor2() {
        Employee_info actualEmployee_info = new Employee_info("aishwarya", "ankalagi", "aishwarya@gmail.com");
        actualEmployee_info.setEmailId("aishwarya@gmail.com");
        actualEmployee_info.setFirstName("aishwarya");
        actualEmployee_info.setId(123L);
        actualEmployee_info.setLastName("ankalagi");
        assertEquals("aishwarya@gmail.com", actualEmployee_info.getEmailId());
        assertEquals("aishwarya", actualEmployee_info.getFirstName());
        assertEquals(123L, actualEmployee_info.getId());
        assertEquals("ankalagi", actualEmployee_info.getLastName());
    }
}

