package users;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CourseRegstrationSystem {

	public static void main(String[] args) throws FileNotFoundException{
		School school = null;
		try {
			FileInputStream fis = new FileInputStream("School.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			school = (School)ois.readObject();
			ois.close();
			fis.close();
		}
		catch(FileNotFoundException fnf){
			ArrayList<Course> courseList= new ArrayList<Course>();
			
			String file = "MyUniversityCourses.csv";
			
			Scanner scanner= new Scanner(new File(file));
			scanner.nextLine();
			String input = scanner.useDelimiter("\\A").next();
			StringTokenizer strTokens = new StringTokenizer(input, ",|\r");
			scanner.close();
			

			while(strTokens.hasMoreTokens()) {
				String courseName = strTokens.nextToken();
				if(!strTokens.hasMoreTokens()) {
					break;
				}
				String id = strTokens.nextToken();
				int maxStudents = Integer.parseInt(strTokens.nextToken());
				strTokens.nextToken();
				strTokens.nextToken();
				String instructor = strTokens.nextToken();
				int sectionNum = Integer.parseInt(strTokens.nextToken());
				String location = strTokens.nextToken();
				Course c = new Course(courseName, id, instructor, location, maxStudents, sectionNum, 0);
				courseList.add(c);
			}
			school = new School(courseList);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			return;
		}
		
		Admin admin = new Admin("admin", "admin001", "ad", "min", school);
		Scanner scanner1 = new Scanner(System.in);
		User currentUser = null;
		while(currentUser == null) {
			System.out.println("Enter username or close");
			String username = scanner1.nextLine();
			if(username.equals("close")) {
				try {
					FileOutputStream fos = new FileOutputStream("School.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(school);
					System.out.println("Serialization Succesful");
				}
				catch(IOException ioe) {
					ioe.printStackTrace();
					System.out.println("Serialization Not Succesful");
					return;
				}
				System.exit(0);
			}
			for(User user : school.getUsers()) {
				if(user.getUsername().equals(username)) {
					System.out.println("Enter Password");
					String passwd = scanner1.nextLine();
					if (user.getPassword().equals(passwd)){
						currentUser  = user;
						System.out.println("Welcome to the CRS");
					}
					else {
						System.out.println("Incorrect Password");
						break;
					}
				}
				
			}

			boolean exit = false;
			while(exit == false && currentUser != null) {
				if(currentUser instanceof Student) {
					System.out.println("\nWhat would you like to do?");
					System.out.println("View Courses(vc)\nView not Full Courses(vnf)\nView Current Courses(vcc)\nRegsiter for a course(r, course name, course section number)\n"
							+ "Withdraw from a course(w, course id, course section number)\nexit(e)\n");
					String command = scanner1.nextLine();
					String[] commandArray = command.split(",");
					for(int i = 0; i < commandArray.length; i++) {
						commandArray[i] = commandArray[i].trim();
					}
					switch(commandArray[0]) {
					case "vc":
						((Student) currentUser).viewCourses();
						break;
					case "vnf":
						((Student) currentUser).viewNotFullCourses();
						break;
					case "vcc":
						((Student) currentUser).viewMyCourses();
						break;
					case "r":
						((Student) currentUser).register(commandArray[1], Integer.parseInt(commandArray[2]));
						break;
					case "w":
						((Student) currentUser).withdraw(commandArray[1], Integer.parseInt(commandArray[2]));
						break;
					case "e":
						currentUser = null;
						exit = true;
						break;
					}
				}
				else if(currentUser instanceof Admin) {
					System.out.println("\nWhat would you like to do?");
					System.out.println("Create a new course(c, course name, course id, course instructor, course location, max students, section number)\ndelete a course (d, course id, course section number)\n"
							+ "edit course(ed, attribute to edit(instructor, location, maxstudents), new attribute, course id, section number)\n"
							+ "display course info(di, course id, section number)\nregister student (r, username, password, firstname, lastname)\nview courses(vc)\n"
							+ "view full courses(vfc)\nwrite full courses to file(wfc, filename)\nview course's students(vcs, course id, section number)\nview student's courses(vsc, firstname, lastname)\n"
							+ "sort courses by number of students(s)\n");
					String command = scanner1.nextLine();
					String[] commandArray = command.split(",");
					for(int i = 0; i < commandArray.length; i++) {
						commandArray[i] = commandArray[i].trim();
					}
					switch(commandArray[0]) {
					case "c":
						((Admin) currentUser).createCourse(commandArray[1], commandArray[2], commandArray[3], commandArray[4], Integer.parseInt(commandArray[5]), Integer.parseInt(commandArray[6]),0);
						break;
					case "d": 
						((Admin) currentUser).deleteCourse(commandArray[1], Integer.parseInt(commandArray[2]));
						break;
					case "ed": 
						if(commandArray[1].equals("instructor")) {
							((Admin) currentUser).editCourseInstructor(commandArray[3], Integer.parseInt(commandArray[4]), commandArray[2]);
						}
						else if(commandArray[1].equals("location")) {
							((Admin) currentUser).editCourseLocation(commandArray[3], Integer.parseInt(commandArray[4]), commandArray[2]);
						}
						else if(commandArray[1].equals("maxstudents")) {
							((Admin) currentUser).editMaxStudents(commandArray[3], Integer.parseInt(commandArray[4]), Integer.parseInt(commandArray[2]));
						}
						break;
					case "di":
						((Admin) currentUser).viewCourses(commandArray[1], Integer.parseInt(commandArray[2]));
						break;
					case "r":
						((Admin) currentUser).registerStudent(commandArray[1], commandArray[2], commandArray[3], commandArray[4]);
						break;
					case "wfc":
						((Admin) currentUser).writeFullCourses(commandArray[1]);
						break;
					case "vc":
						((Admin) currentUser).viewCourses();
						break;
					case "vfc":
						((Admin) currentUser).viewFullCourses();
						break;
					case "vcs":
						((Admin)  currentUser).viewStudents(commandArray[1], Integer.parseInt(commandArray[2]));
						break;
					case "vsc":
						((Admin) currentUser).viewCourses(commandArray[1], commandArray[2]);
						break;
					case "s":
						for(Course course: ((Admin) currentUser).sortCourses()) {
							System.out.println(course.getName());
							//Exits user after sort
						}
						break;
					case "e":
						currentUser = null;
						exit = true;
						break;
					}
				}
			}
		}
	}
}




