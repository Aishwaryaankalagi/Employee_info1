package com.example.demo.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;

class GlobalExceptionHandlerTest {
   
    @Test
    void testGlobalExceptionHandling() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        Exception exception = new Exception("foo");
        ResponseEntity<?> actualGlobalExceptionHandlingResult = globalExceptionHandler.globalExceptionHandling(exception,
                new ServletWebRequest(new MockHttpServletRequest()));
        assertTrue(actualGlobalExceptionHandlingResult.hasBody());
        assertTrue(actualGlobalExceptionHandlingResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.NOT_FOUND, actualGlobalExceptionHandlingResult.getStatusCode());
        assertEquals("uri=", ((ErrorDetails) actualGlobalExceptionHandlingResult.getBody()).getDetails());
        assertEquals("foo", ((ErrorDetails) actualGlobalExceptionHandlingResult.getBody()).getMessage());
    }

}