package chartmycourse.chartmycourse;

public class user {
	
	public String realName;
	public String userName;
	public String email;
	public String password;
	int year;
	
	
	
	
	public boolean compare(user testUser) {
		if ((userName.compareTo(testUser.userName) == 0 || email.compareTo(testUser.userName) == 0) && (password.compareTo(testUser.password) == 0)) {
			return true;
		}
		return false;
	}
	
	public boolean equals(user testUser) {
		if (userName.compareTo(testUser.userName) == 0 || email.compareTo(testUser.email) == 0) {
			return true;
		}
		return false;
	}
	
	public user(String realName, String userName, String email, String password) {
		this.realName = realName;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	public user() {
		
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
}
