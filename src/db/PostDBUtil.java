package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;

import model.Post;
import model.User;

public class PostDBUtil {
	private DataSource datasource;
	
	public PostDBUtil(DataSource datasource) {
		this.datasource = datasource;
	}
	
	public void insertPost(Post post) throws Exception {
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		String email = post.getEmail();
		String date = post.getDate();
		String postContent = post.getPostContent(); 
		
		
		try {
			
			conn =  this.datasource.getConnection();
			
			String sql = String.format("INSERT INTO post (email,content,date) VALUES('%s','%s','%s');",email,postContent,date);
			
			stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);
			
		} finally {
			// TODO: handle finally clause
			close(conn,stmt,pstmt,res);
		}
	}
	
	public ArrayList<Post> displayPost(String email){
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ArrayList<Post> returnedlist = new ArrayList<>();
		
		
		try {
			
			conn =  this.datasource.getConnection();
					
			String sql = String.format("SELECT p.*, u.fname, u.lname, count(l.postid) as likes" + 
					" FROM post p" + 
					" LEFT JOIN likes l ON p.postid = l.postid" + 
					" JOIN user u ON p.email = u.email" + 
					" where u.email != ?" + 
					" GROUP BY p.postid;");
			
			pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, email);
	        result = pstmt.executeQuery();
			
			
			while(result.next()) {
				int id = result.getInt("postid");
				String postContent = result.getString("content");
				String likes = result.getString("likes").toString();
				String date = result.getString("date");
				String fname = result.getString("fname");
				String lname = result.getString("lname");
				returnedlist.add(new Post(id, date, fname, lname, postContent,likes));
				
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(conn,stmt,pstmt,result);
		}
		return returnedlist;
	}
	
	public int getTotalPosts(String email){
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		int totalposts=0;
		try {
			
			conn =  this.datasource.getConnection();
					
			String sql = String.format("Select count(*) as totalposts from post where email = ?");
			pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, email);
	        result = pstmt.executeQuery();
	        
	        while(result.next()) {
				totalposts = result.getInt("totalposts");
	        }
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(conn,stmt,pstmt,result);
		}
		return totalposts;
	}
	public int getTotalSavedPosts(String email){
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		int totalsavedposts=0;
		try {
			
			conn =  this.datasource.getConnection();
					
			String sql = String.format("Select count(*) as totalsavedposts from savepost where email = ?");
			pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, email);
	        result = pstmt.executeQuery();
	        
	        while(result.next()) {
				totalsavedposts = result.getInt("totalsavedposts");
	        }
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(conn,stmt,pstmt,result);
		}
		return totalsavedposts;
	}
	public int getTotalLikes(String email){
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		int totalLikes=0;
		try {
			
			conn =  this.datasource.getConnection();
					
			String sql = String.format("Select count(*) as totalLikes from likes where email = ?");
			pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, email);
	        result = pstmt.executeQuery();
	        
	        while(result.next()) {
				totalLikes = result.getInt("totalLikes");
	        }
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(conn,stmt,pstmt,result);
		}
		return totalLikes;
	}
	
	

	public ArrayList<Post> displayUserPost(String email){
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ArrayList<Post> returnedlist = new ArrayList<>();
		
		
		try {
			
			conn =  this.datasource.getConnection();
			String sql = "SELECT p.*, u.fname, u.lname, count(l.postid) as likes "
                    + "FROM post p "
                    + "LEFT JOIN likes l ON p.postid = l.postid "
                    + "JOIN user u ON p.email = u.email "
                    + "GROUP BY p.postid HAVING p.email = ?";
			 
			pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, email);
	        result = pstmt.executeQuery();
			
						
			while(result.next()) {
					int id = result.getInt("postid");
					String postContent = result.getString("content");
					String retEmail = result.getString("email");
					String likes = result.getString("likes").toString();
					String date = result.getString("date");
					returnedlist.add(new Post(id, retEmail, date, postContent,likes));
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(conn,stmt,pstmt,result);
		}
		return returnedlist;
	}

	public ArrayList<Post> displaySavePost(String email){
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ArrayList<Post> saveReturnedlist = new ArrayList<>();
		
		
		try {
			
			conn =  this.datasource.getConnection();
			String sql = "SELECT p.*, u.fname, u.lname, count(l.postid) as likes "
                    + "FROM post p "
                    + "LEFT JOIN likes l ON p.postid = l.postid "
                    + "JOIN user u ON p.email = u.email "
                    + "JOIN savepost s ON p.postid = s.postid "
                    + "WHERE s.email = ? "
                    + "GROUP BY p.postid";
			 
			pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, email);
	        result = pstmt.executeQuery();
			while(result.next()) {
					int id = result.getInt("postid");
					String postContent = result.getString("content");
					String retEmail = result.getString("email");
					String likes = result.getString("likes").toString();
					String date = result.getString("date");
					String fname = result.getString("fname");
					String lname = result.getString("lname");
					saveReturnedlist.add(new Post(id, date, fname, lname, postContent,likes));
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(conn,stmt,pstmt,result);
		}
		return saveReturnedlist;
	}	
	
	public boolean deletePost(int index){
		boolean result;
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			conn =  this.datasource.getConnection();
			
			
			String sql = String.format("DELETE FROM post WHERE postid = ?");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1,index);
			
			pstmt.executeUpdate();
			
			result = true;
		} 
		catch(Exception e){
			result = false;
		}
		finally {
			close(conn,stmt,pstmt,res);
		}
		return result;
	}
	
	public boolean removeSavePost(int index){
		boolean result;
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			conn =  this.datasource.getConnection();
			
			
			String sql = String.format("DELETE FROM savepost WHERE postid = ?");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1,index);
			
			pstmt.executeUpdate();
			
			result = true;
		} 
		catch(Exception e){
			result = false;
		}
		finally {
			close(conn,stmt,pstmt,res);
		}
		return result;
	}
	
	public String getPostContent(int id) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String returnedPost = null;
		try {
			
			conn =  this.datasource.getConnection();
			String sql = String.format("Select content from post where postid = ?");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1,id);
			
			result = pstmt.executeQuery();
						
			while(result.next()) {
				returnedPost = result.getString("content");
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(conn,stmt,pstmt,result);
		}
		return returnedPost;
	}
	
	public void updateEditPost(Post post) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
				
			conn =  this.datasource.getConnection();
			String sql = String.format("UPDATE post SET content = ?, date = ? where postid = ?");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,post.getPostContent());
			pstmt.setString(2,post.getDate());
			pstmt.setInt(3,post.getId());
			
			pstmt.executeUpdate();
		} 
		catch(Exception e) {
			
			e.printStackTrace();
		}
		finally {
			close(conn,stmt,pstmt,result);
		}
	}
	
	
	public void likePost(int index,String email){
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			
			conn =  this.datasource.getConnection();
			
			String sql = String.format("INSERT into likes values('%s','%s')",email,index);
			
			stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);
			
		} 
		catch(Exception e){
			removelike(index,email);
		}
		finally {
			close(conn,stmt,pstmt,res);
		}
		
	}
	
	private void removelike(int index,String email) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			
			conn =  this.datasource.getConnection();
			String sql = String.format("Delete from likes where email=? and postid =?");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,email);
			pstmt.setInt(2,index);
			pstmt.executeUpdate();
		} 
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			close(conn,stmt,pstmt,res);
		}
	}
	
	public boolean savePost(int index,User user){
		boolean result;
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			conn =  this.datasource.getConnection();
			
			
			String sql = String.format("Insert into savepost values('%s','%s')",user.getEmail(),index);
			
			stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);
			
			result = true;
		} 
		catch(Exception e){
			result = false;
		}
		finally {
			close(conn,stmt,pstmt,res);
		}
		return result;
	}
	
	private void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet res) {
		
		try {
			
			if(conn != null){				
				conn.close();
			}
			
			if(stmt != null) {				
				stmt.close();
			}
			
			if(pstmt != null) {				
				pstmt.close();
			}
			
			if(res != null) {				
				res.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
}
