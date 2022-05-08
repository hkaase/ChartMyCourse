package chartmycourse.chartmycourse;

import java.util.HashSet;
import java.util.Set;

public class User {
	
	public String realName;
	public String userName;
	public String email;
	public String password;
	public String secQuestionAnswer;
	int year;

	public Set<String> professorList = new HashSet<String>();
	public Set<String> courseList = new HashSet<String>();
	
	
	public boolean compare(User testUser) {
		if ((userName.compareTo(testUser.userName) == 0 || email.compareTo(testUser.userName) == 0) && (password.compareTo(testUser.password) == 0)) {
			return true;
		}
		return false;
	}
	
	public boolean equals(User testUser) {
		if (userName.compareTo(testUser.userName) == 0 || email.compareTo(testUser.email) == 0) {
			return true;
		}
		return false;
	}
	
	public User(String realName, String userName, String email, String password, String answer) throws Exception {
		if(!email.endsWith("@baylor.edu")) {
			throw new Exception("Invalid emial");
		}
		this.realName = realName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.professorList = new HashSet<>();
		this.courseList = new HashSet<>();
		this.secQuestionAnswer = answer;
	}
	
	public User() {
		
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

	public Set<String> getProfessorList() {
		return professorList;
	}

	public void setProfessorList(Set<String> professorList) {
		this.professorList = professorList;
	}

	public boolean saveProfessor(String professorName) {
		return this.professorList.add(professorName);
	}

	public void removeProfessor(String professorName) {
		this.professorList.remove(professorName);
	}

	public Set<String> getCourseList() {
		return courseList;
	}

	public void setCourseList(Set<String> courseList) {
		this.courseList = courseList;
	}

	public boolean saveCourse(String courseName) {
		return this.courseList.add(courseName);
	}

	public void removeCourse(String courseName) {
		this.courseList.remove(courseName);
	}

	public String getSecQuestionAnswer() {
		return secQuestionAnswer;
	}

	public void setSecQuestionAnswer(String secQuestionAnswer) {
		this.secQuestionAnswer = secQuestionAnswer;
	}
	
	

}
