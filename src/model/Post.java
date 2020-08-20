package model;

import java.util.Date;

import db.PostDBUtil;

public class Post {
	int id;
	String email;
	String lname;
	String fname;
	String date;
	String PostContent;
	String likes;
	public Post(int id) {
		this.id = id;
		this.email = "";
		Date dNow = new Date();
		date = dNow.toString();
		PostContent = "";
		this.likes = "";
	}
	public Post(int id,String postContent,String email) {
		this.id = id;
		this.email = email;
		Date dNow = new Date();
		date = dNow.toString();
		PostContent = postContent;
		this.likes = "";
	}
	public Post(String postContent,String email) {
		this.id = 0;
		this.email = email;
		Date dNow = new Date();
		date = dNow.toString();
		PostContent = postContent;
		this.likes = "";
	}
	public Post(int id, String email, String date, String postContent, String likes) {
		super();
		this.id = id;
		this.email = email;
		this.date = date;
		PostContent = postContent;
		this.likes = likes;
	}
	public Post(int id, String date,String fname,String lname, String postContent, String likes) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.date = date;
		PostContent = postContent;
		this.likes = likes;
	}
	
	public String getPostContent() {
		return PostContent;
	}
	public void setPostContent(String postContent) {
		PostContent = postContent;
	}
	public int getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return fname + " "+lname;
	}
	
	public String getDate() {
		return date;
	}
	public String getLikes() {
		return likes;
	}
	public boolean createPost(PostDBUtil postdb) {
		try {
			 postdb.insertPost(this);
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public boolean updatePost(PostDBUtil postdb) {
		try {
			 postdb.updateEditPost(this);
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public String getPost(PostDBUtil postdb,int id) {
		try {
			 return postdb.getPostContent(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}
}
