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
import model.Post;
import model.User;

@WebServlet("/CreatePost")
public class CreatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CreatePost() {super();}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doGet(request, response);}
    

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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	String postContent = (String)request.getParameter("posttext");
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
		
    	Post newPost = new Post(postContent,user.getEmail());
    	
    	newPost.createPost(postdb);
    		
    	response.sendRedirect("DisplayPost");
    }
}
