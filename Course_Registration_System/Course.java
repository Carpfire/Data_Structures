package users;
import java.util.ArrayList;

public class Course implements java.io.Serializable{
	private final String name, id;
	private String instructor, location;
	private int maxStudents, currentStudents, sectionNum;
	private ArrayList<Student> students;
	
	public Course(String name, String id, String instructor, String location, 
			int maxStudents, int sectionNum, int currentStudents) {
		this.name = name;
		this.id = id;
		this.instructor = instructor;
		this.location = location;
		this.maxStudents = maxStudents;
		this.students = new ArrayList<Student>();
		this.currentStudents = currentStudents;
		this.sectionNum = sectionNum;
		
		
	}
	public Course() {
		this.name = null;
		this.id = null;
		this.instructor = null;
		this.location = null;
		this.maxStudents = 0;
		this.students = null;
		this.currentStudents = 0;
		this.sectionNum = 0;
	}
	public String getName() {
		return this.name;
	}
	public String getId() {
		return this.id;
	}
	public String getInstructor() {
		return this.instructor;
	}
	public String getLocation() {
		return this.instructor;
	}
	public int getMaxStudents() {
		return this.maxStudents;
	}
	public int getCurrentStudents() {
		return this.currentStudents;
	}
	public int getSectionNumber() {
		return this.sectionNum;
	}
	public String[] getStudentsName() {
		String[] studentArray = new String[this.students.size()];
		for(int i = 0; i < this.students.size(); i++) {
			String firstName = students.get(i).firstname;
			String lastName = students.get(i).lastname;
			studentArray[i] = firstName + " " + lastName;
		}
		return studentArray;
	}
	public ArrayList<Student> getStudents(){
		return this.students;
		
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}
	public void addStudent(Student student) {
		this.students.add(student);
		this.currentStudents++;
	}
	public void removeStudent(Student student) {
		this.students.remove(student);
		this.currentStudents--;
	}
	public void displayInfo() {
		System.out.println(this.name);
		System.out.println(this.id);
		System.out.println(this.instructor);
		System.out.println(this.location);
		System.out.println(this.maxStudents);
		System.out.println(this.sectionNum);
		for(Student student: this.students) {
			System.out.println("Students:");
			System.out.print(student.getFullName()+" ");
		}
		System.out.println();
	}

	

}
