package com.assignment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ValidationSevlet
 */
@WebServlet("/validation")
public class ValidationSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidationSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		
		UserInfo user = new UserInfo(name, password);		
		
		boolean isValidUser = ValidationCheck.isValid(user);		
		
		
		RequestDispatcher dispatcher;
		
		if(isValidUser) {	
			HttpSession session = request.getSession();
			session.setAttribute("user", name);
			request.setAttribute("message","Welcome "+name );
			dispatcher = request.getRequestDispatcher("addDonor.jsp");		
		}
		else {
			request.setAttribute("message","Invalid Access" );
			dispatcher = request.getRequestDispatcher("index.jsp");
		}

		dispatcher.forward(request, response);	
	}
	

}
