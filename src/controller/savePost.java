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

import db.PostDBUtil;
import model.User;

@WebServlet("/savePost")
public class savePost extends HttpServlet {
	private static final long serialVersionUID = 1L;   
    public savePost() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    
    @Resource(name="jdbc/social")
    private DataSource datasource;
    private PostDBUtil postdb;

    @Override
	public void init() throws ServletException {
    	super.init();
		
		try {			
			postdb = new PostDBUtil(datasource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Integer id = Integer.parseInt(request.getParameter("index"));
    	postdb.savePost(id,user);
    	response.sendRedirect("userPost");
	}
}
