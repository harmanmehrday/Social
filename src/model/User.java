package model;

import db.PostDBUtil;
import db.UserDBUtil;

public class User {
	String fname;
	String lname;
	String email;
	String password;
	
	public User() {
		
	}
	public User(String fname, String lname, String email, String password) {
		this.fname=fname;
		this.lname=lname;
		this.email=email;
		this.password=password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean createUser(UserDBUtil userdb) {
		try {
			 userdb.insertUser(this);
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public User loginUser(UserDBUtil userdb) {
		try {
			User tempUser = userdb.loginUser(this);
			if(tempUser !=null ) {
				return tempUser;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public void clearUserData(UserDBUtil userdb) {
		try {
			userdb.clearUser(this.email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(UserDBUtil userdb) {
		try {
			userdb.deleteUser(this.email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changePassword(UserDBUtil userdb,String newPwd) {
		try {
			userdb.changePassword(newPwd,this.email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean likePost(PostDBUtil postdb,int postid) {
		postdb.likePost(postid, this.getEmail());
		return false;
		
	}
	
	@Override
	public String toString() {
		return "My name is " + this.fname + " " + this.lname +"\n";
	}	
}
