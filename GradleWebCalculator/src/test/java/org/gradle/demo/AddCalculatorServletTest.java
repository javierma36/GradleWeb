/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gradle.demo;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author mauriciobedoya
 */
public class AddCalculatorServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void doGetWithValues() throws ServletException, IOException{
        when(request.getParameter("value1")).thenReturn("1");
        when(request.getParameter("value2")).thenReturn("2");
        when(request.getRequestDispatcher("response.jsp")).thenReturn(dispatcher);
        
        new AddCalculatorServlet().doGet(request, response);
        
        verify(request).setAttribute("addResult",3.0);
        verify(dispatcher).forward(request, response);
    }
}
