package controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import db.UserDBUtil;
import model.User;

@WebServlet("/changePassword")
public class changePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public changePassword() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    
    @Resource(name="jdbc/social")
    private DataSource datasource;
    private UserDBUtil userdb;

    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
    	super.init();
		
		try {
			
			userdb = new UserDBUtil(datasource);
		
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");		
		
		String oldPwd = (String)request.getParameter("oldPwd");
		String newPwd = (String)request.getParameter("newPwd");
		String result = "";
		if(oldPwd.isEmpty() || newPwd.isEmpty()) {
			result = "Please enter empty field.";
		}
		else if(user.getPassword().contentEquals(oldPwd)) {
			user.changePassword(userdb,newPwd);	
			result = "PC";
			response.sendRedirect("logout");
			return;
		}
		else {
			result = "Old Password does not match!!";
		}
		request.setAttribute("result",result);
	    String strViewPage="/changePassword.jsp";
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(strViewPage);
	    dispatcher.forward(request, response);
    }
}
