package users;
import java.util.ArrayList;

public class School implements java.io.Serializable{
	
	private ArrayList<Course> courses;
	private ArrayList<User> users = new ArrayList<User>();
	
	
	public School(ArrayList<Course> courses) {
		 this.courses = courses;
	 }
	 
	public ArrayList<Course> getCourseList(){
		 return this.courses;
	 }
	public void addCourse(Course newCourse) {
		this.courses.add(newCourse);
		
	}
	public void deleteCourse(String id, int section) {
		for(Course course: this.courses) {
			if(course.getId().equals(id) && course.getSectionNumber() == section) {
				for(Student student: course.getStudents()) {
					student.withdraw(id, section);
				}
				this.courses.remove(course);
				break;
			}

			}
		}
	public void addUser(User user) {
		this.users.add(user);
	}
	public ArrayList<User> getUsers(){
		return this.users;
	}
}

