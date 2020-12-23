package com.assignment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Transection
 */
@WebServlet("/transection")
public class Transection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserCreditInfo info;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transection() {
        super();
        // TODO Auto-generated constructor stub
      this.info =new UserCreditInfo();
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
		int amount = Integer.parseInt(request.getParameter("plans"));
		
		if(this.info.getUserMap().containsKey(name)) {
			
		  int currentAmount = this.info.getUserMap().get(name);
		  System.out.println("CURRENT = "+currentAmount+"BUY = "+amount);
		  if(currentAmount>=amount) {
			  
			  currentAmount = currentAmount-amount;
			 
			  this.info.getUserMap().replace(name,currentAmount);
			  
			  request.setAttribute("amount",currentAmount );
			  request.setAttribute("message","Payment Successful" );
		  }
		  else
		  {
			  request.setAttribute("amount",currentAmount );
			  request.setAttribute("message","Payment Unsuccessful" );
		  }
			
		}
		else {
			request.setAttribute("message","Invalid Access" );
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
	}

}
