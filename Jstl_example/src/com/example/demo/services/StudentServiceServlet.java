package com.example.demo.services;

 

import java.io.IOException;
import java.util.List;

 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 

import com.example.demo.model.Student;
/**
 * Servlet implementation class StudentServiceServlet
 */
@WebServlet("/student")
public class StudentServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentService service = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServiceServlet() {
        super();
        service = new StudentService();
    }

 

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String dept = request.getParameter("deptName");
            List<Student> studentList = this.service.getDetails(dept);
            request.setAttribute("studentList", studentList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("showStudent.jsp");
            dispatcher.forward(request, response);
    }

 

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

 

}