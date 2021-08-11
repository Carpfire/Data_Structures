package users;
import java.util.ArrayList;
public abstract class User implements java.io.Serializable{
	protected String username, password, firstname, lastname;
	protected ArrayList<Course> courses;
	
	public User(String username, String password, String firstname, String lastname, School school) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		//Could probably remove courses attr and specify courses v. school attr for admin v. student
		this.courses = school.getCourseList();
	}
	public abstract void viewCourses();
	
	public String getFullName() {
		return this.firstname + " " + this.lastname;
	}
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
}

