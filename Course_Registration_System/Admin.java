package users;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Collections;

public class Admin extends User {
	private School school;
	public Admin(String username, String password, String firstname, String lastname, School school) {
		super(username, password, firstname, lastname, school);
		this.school = school;
		this.school.addUser(this);
	}

	public void viewCourses(String id, int sectionNum) {
		for(Course course: this.courses) {
			if(course.getId().equals(id) && course.getSectionNumber( ) == sectionNum) {
				course.displayInfo();
			}
		}
	}	
	public void viewCourses() {
		for(Course course: this.courses) {
			course.displayInfo();
		}
	}
	
	public void viewCourses(String firstname, String lastname) {
		for(Course course: this.courses) {
			for(Student student: course.getStudents()) {
				if(student.firstname.equals(firstname) && student.lastname.equals(lastname)) {
					System.out.println(course.getName());
				}
			}
		}
	}
	
	public void viewFullCourses() {
		for(String courseName: this.getFullCourses()) {
			System.out.println(courseName);
			}
		}
	
	public void viewStudents(String id, int sectionNum) {
		for(Course course: this.courses) {
			if(course.getId().equals(id) && course.getSectionNumber() == sectionNum) {
				for(Student student: course.getStudents()) {
					System.out.println(student.getFullName());
				}
			}
		}
	}
	public ArrayList<String> getFullCourses() {
		ArrayList<String> fullCourses = new ArrayList<String>();
		for(Course course: this.courses) {
			if(course.getMaxStudents() == course.getCurrentStudents()) {
				fullCourses.add(course.getName());
			}
		}
		return fullCourses;
	}
	
	public void editCourseInstructor(String id, int sectionNum, String instructor) {
		for(Course course: this.courses) {
			if(course.getId().equals(id) && course.getSectionNumber() == sectionNum) {
				course.setInstructor(instructor);
			}
		}
	}
	
	public void editCourseLocation(String id, int sectionNum, String location) {
		for(Course course: this.courses) {
			if(course.getId().equals(id) && course.getSectionNumber() == sectionNum) {
				course.setLocation(location);
			}
		}
	}
	
	public void editMaxStudents(String id, int sectionNum, int maxStudents) {
		for(Course course: this.courses) {
			if(course.getId().equals(id) && course.getSectionNumber() == sectionNum) {
				course.setMaxStudents(maxStudents);
			}
		}
	}
	public void registerStudent(String username, String password, String firstname, String lastname) {
		Student student = new Student(username, password, firstname, lastname, this.school);
		this.school.addUser(student);
	}
	public void createCourse(String name, String id, String instructor, String location, int maxStudents, 
			int sectionNum, int currentStudents) {
		Course newCourse = new Course(name, id, instructor, location, maxStudents, sectionNum, currentStudents);
		this.school.addCourse(newCourse);
	}
	
	public void deleteCourse(String courseId, int section) {
		this.school.deleteCourse(courseId, section);
	}
	
	public void writeFullCourses(String filename) {
		try {
			FileWriter fileWriter = new FileWriter(filename);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for(String courseName: this.getFullCourses()) {
				bufferedWriter.write(courseName);
				System.out.println("Course "+courseName+" written");
				bufferedWriter.newLine();
			}
			System.out.println("Writer Closed");
			bufferedWriter.close();
		}
		catch(IOException exk) {
			System.out.println("Error writing file '" + filename + "'");
			exk.printStackTrace();
		}
	}
	
	public ArrayList<Course> sortCourses(){
		//TODO
		Collections.sort(this.courses, new SortByStudents());
		//Sorts in place
		return this.courses;
	}
}
class SortByStudents implements Comparator<Course> {
	public int compare(Course a, Course b) {
		return a.getCurrentStudents() - b.getCurrentStudents();
	}
}
