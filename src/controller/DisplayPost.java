package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import db.PostDBUtil;
import model.Post;
import model.User;

@WebServlet("/DisplayPost")
public class DisplayPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DisplayPost() {
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
    	ArrayList<Post> list = postdb.displayPost(user.getEmail());
		request.setAttribute("list",list);
		String strViewPage="/loginSuccess.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
		dispatcher.forward(request, response); 	
    }
}
