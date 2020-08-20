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

@WebServlet("/likePost")
public class likePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public likePost() {
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
		
		Integer id = Integer.parseInt(request.getParameter("index"));
		Integer page = Integer.parseInt(request.getParameter("page"));
		//1 for userposts
		//2 for friends posts
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
    	user.likePost(postdb, id);
    	if(page==1) {
    		response.sendRedirect("userPost");
    	}
    	else {
    		response.sendRedirect("DisplayPost");
    	}
	}

	

}
