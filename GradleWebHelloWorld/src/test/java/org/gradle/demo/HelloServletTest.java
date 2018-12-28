/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gradle.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import junit.framework.Assert;
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
public class HelloServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doGet() throws IOException, ServletException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);
        new HelloServlet().doGet(request, response);
        Assert.assertEquals("Hello World", stringWriter.toString());
    }

    @Test
    public void doPostWithoutName() throws ServletException, IOException {
        when(request.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);
        new HelloServlet().doPost(request, response);

        verify(request).setAttribute("user", "World");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doPostWithName() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("Doly");
        when(request.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);
        
        new HelloServlet().doPost(request, response);

        verify(request).setAttribute("user", "Doly");
        verify(requestDispatcher).forward(request, response);
    }
}
