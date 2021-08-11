package users;
import java.util.ArrayList;

public class Student extends User {
	private ArrayList<Course> myCourses;
	
	public Student(String username, String password, String firstname, String lastname, School school) {
		super(username, password, firstname, lastname, school);
		myCourses = new ArrayList<Course>();
		}
	
	public void viewCourses() {
		for(Course course: this.courses) {
			System.out.println(course.getName());
		}
	}
	
	public void viewNotFullCourses() {
		for(Course course: this.courses) {
			if(course.getMaxStudents() > course.getCurrentStudents()) {
				System.out.println(course.getName());
			}
		}
	}
	
	public void viewMyCourses() {
		for(Course course: this.myCourses) {
			System.out.println(course.getName());
		}
	}
	
	public ArrayList<Course> getMyCourses() {
		return this.myCourses;
	}

	public void register(String courseId, int section) {
		for(Course course: this.courses) {
			if(course.getId().equals(courseId) && course.getSectionNumber() == section) {
				if(course.getCurrentStudents() < course.getMaxStudents()){
					course.addStudent(this);
					this.myCourses.add(course);
				}
				else{
					System.out.println("Course is full");
				}
			}
		}
	}
	
	public void withdraw(String courseId, int section) {//TODO solve concurrent modification exception
		for(Course course: this.myCourses) {
			if(course.getId().equals(courseId) && course.getSectionNumber() == section) {
				for(Student student: course.getStudents()) {
					if(student.getFullName().equals(this.getFullName())) {
						course.removeStudent(student);
						break;
					}
				}
				this.myCourses.remove(course);
				break;
			}
		}
		
	}
}
