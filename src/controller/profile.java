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

import db.PostDBUtil;
import model.User;

@WebServlet("/profile")
public class profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public profile() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    
    @Resource(name="jdbc/social")
    private DataSource datasource;
    private PostDBUtil postdb;

    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
    	super.init();
		
		try {
			
			postdb = new PostDBUtil(datasource);
		
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
		int postNo,saveNo,likes;
    	postNo = postdb.getTotalPosts(user.getEmail());
    	saveNo = postdb.getTotalSavedPosts(user.getEmail());
    	likes = postdb.getTotalLikes(user.getEmail());
    	request.setAttribute("posts", postNo);
    	request.setAttribute("savedposts", saveNo);
    	request.setAttribute("likes", likes);
    	String strViewPage="/profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
		dispatcher.forward(request, response);
	}

	

}
