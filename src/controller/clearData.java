package controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import db.UserDBUtil;
import model.User;

@WebServlet("/clearData")
public class clearData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public clearData() {
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
		user.clearUserData(userdb);
		response.sendRedirect("profile");
	}

	

}
