package com.training.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.entity.BloodDonor;

import com.training.service.BloodDonorService;
import com.training.utils.DbConnectionUtil;

/**
 * Servlet implementation class BloodDonorServlet
 */
@WebServlet("/donor")
public class BloodDonorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BloodDonorService donorServ;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Connection con;
    public BloodDonorServlet() {
        super();
        // TODO Auto-generated constructor stub
        con =DbConnectionUtil.getMySqlConnection();
        this.donorServ = new BloodDonorService(con);
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<BloodDonor> list = donorServ.findAll();
		System.out.println(list);
		
		request.setAttribute("donorList", list);
		RequestDispatcher newDispatcher=request.getRequestDispatcher("viewDonorList.jsp");
		newDispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		
		int age = Integer.parseInt(request.getParameter("age"));
		
		String gender = request.getParameter("gender");
		String bloodGroup = request.getParameter("bloodGroup");
		String phoneNumber = request.getParameter("number");
		String email = request.getParameter("email");
		
		LocalDate dateOfBirth = Date.valueOf(request.getParameter("dob")).toLocalDate();

		
		BloodDonor donor = new BloodDonor(name, age, gender, bloodGroup, phoneNumber, email, dateOfBirth);
		System.out.println(donor);
		
		
		Integer rowAdded = donorServ.add(donor);
		
		request.setAttribute("rowAdded", "Row Added:= "+rowAdded);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("addDonor.jsp");
		dispatcher.forward(request, response);
	}

}
