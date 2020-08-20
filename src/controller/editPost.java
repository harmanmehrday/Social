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
import model.Post;

@WebServlet("/editPost")
public class editPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public editPost() {
        super();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession editid = request.getSession();
		Integer id = Integer.parseInt(request.getParameter("index"));
		editid.setAttribute("id",id);
		Post post = new Post(id);
		String postContent = post.getPost(postdb,id);
		request.setAttribute("post", postContent);
		request.setAttribute("id", id);
		String strViewPage="/editPost.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
		dispatcher.forward(request, response); 
	}


}
