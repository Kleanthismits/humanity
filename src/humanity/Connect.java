package humanity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Connect {
	static Connect db = new Connect();
	static Scanner sc = new Scanner(System.in);
	static Connection connection;
	DriverManager dm;
	static Statement stm;

	public java.sql.Connection connect(String DB_URL, String username, String password) {
		try {
			connection = DriverManager.getConnection(DB_URL, username, password);

			return connection;
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problems with server / database");
			return null;
		}catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("Null");
			return null;
		}
	}

	public int executeStatement(String sql) {
		try {
			stm = connection.createStatement();
			return stm.executeUpdate(sql);
		}catch (SQLException | NullPointerException e) {
			System.out.println(e.getMessage());
			return -22;
		}

	}

	public static void createWorker() {
		boolean salaryAgain = false;
		boolean hoursAgain = false;
		boolean wrongFirstName = false;
		boolean wrongLastName = false;
		String fname  ="";
		String lname = "";
		int hours = 0;
		int salary = 0;
		do {
			System.out.println("Enter worker first name: ");
			fname = sc.next();
			if(fname.trim().length()<2) {
				System.out.println("First Name should be at least two characters");
				wrongFirstName = true;
			}
		}while(wrongFirstName);
		do {
			System.out.println("Enter worker last name: ");
			lname = sc.next();
			if(fname.trim().length()<3) {
				System.out.println("Last Name should be at least two characters");
				wrongLastName = true;
			}
		}while(wrongLastName);
		do {
			salaryAgain = false;
			System.out.println("Enter his weekly salary: ");
			try {
				salary = sc.nextInt();
				if (salary<11) {
					System.out.println("Salary should be more than 10");
					salaryAgain = true;

				}else {
					salaryAgain = false;
				}
			}catch (InputMismatchException e) {
				System.out.println("You must enter an integer!");
				sc.next();
				salaryAgain = true;

			}

		}while(salaryAgain);

		do {
			System.out.println("Enter how many hours he works per day");
			try {
				hours = sc.nextInt();
				if (hours<1 || hours>12) {
					System.out.println("Hours per day should be between 1 and 12");
					hoursAgain = true;

				}else {
					hoursAgain = false;
				}
			}catch (InputMismatchException e) {
				System.out.println("You must enter an integer!");
				sc.next();
				hoursAgain = true;

			}
		}while(hoursAgain);
		String name = (fname.substring(0,1).toUpperCase()+fname.substring(1));

		db.executeStatement("INSERT INTO humanitytest.worker (firstName, lastName, weekSalary,workHourspDay)\r\n" + 
				"VALUES ('"+name+"', '"+(lname.substring(0,1).toUpperCase()+lname.substring(1))+"', '"+salary+"', '"+hours+"');");
	}

	public static void createStudent(){

		System.out.println("Enter student first name: ");
		String fnames = sc.next().trim();

		System.out.println("Enter student last name: ");
		String lnames = sc.next().trim();

		System.out.println("Enter student faculty number");
		String fnum = sc.next().trim();

		db.executeStatement("INSERT INTO humanitytest.student (fname, lname, facultyNumber)\r\n" + 
				"VALUES ('"+(fnames.substring(0,1).toUpperCase()+fnames.substring(1))+"', '"+(lnames.substring(0,1).toUpperCase()+lnames.substring(1))+"', '"+fnum+"');");

	}

	public static void printStudents() {
		String query = "SELECT * FROM HUMANITYTEST.STUDENT";

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String last_name = rs.getString("lname");
				String first_name = rs.getString("fname");
				String facNumber = rs.getString("facultyNumber");

				System.out.printf("%-15s %-10s","First Name:",first_name);
				System.out.println();
				System.out.printf("%-15s %-10s","Last Name:",last_name);
				System.out.println();
				System.out.printf("%-15s %-10s","Faculty Number:",facNumber);
				System.out.println();
				System.out.println();
			}
		}catch(SQLException e) {
			e.getMessage();
		}
	}

	public static void printWorkers() {
		String query = "SELECT * FROM HUMANITYTEST.WORKER";

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String workerId = rs.getString("workerid");
				String first_name = rs.getString("firstName");
				String last_name = rs.getString("lastName");
				String weekSalary = rs.getString("weekSalary");
				String workHoursPerDay = rs.getString("workHourspDay");
				String earnByHour = rs.getString("earnByHour");

				System.out.printf("%-15s %-10s","First Name:",first_name);
				System.out.println();
				System.out.printf("%-15s %-10s","Last Name:",last_name);
				System.out.println();
				System.out.printf("%-15s %-10s","Week Salary:",weekSalary);
				System.out.println();
				System.out.printf("%-15s %-10s","Hours per day",workHoursPerDay);
				System.out.println();
				System.out.printf("%-15s %-10s","Salary per hour",earnByHour);
				System.out.println();

				System.out.println();
			}
		}catch(SQLException e) {
			e.getMessage();
		}
	}

	public static int countRows(ResultSet rs) {
		int count = 0;
		try {
			while(rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public static void countWorkers() {
		String query = "SELECT * FROM HUMANITYTEST.WORKER";

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			System.out.println("Current Number of workers: " + countRows(rs));
		}catch(SQLException e) {
			e.getMessage();
		}
	}



	public static void main(String[] args) throws SQLException {

		boolean again = false;
		String url = "jdbc:mysql://localhost:3306";
		db.connect(url, "root", "password");

		System.out.println("Welcome to project Humanity");
		do {
			System.out.println();
			System.out.println("Press");
			System.out.println("1 for creating a new Worker (Constraints checked in java)");
			System.out.println("2 for creating a new Student (Constraints checked in Mysql with trigger)1");
			System.out.println("3 for printing the students info");
			System.out.println("4 for printing the workers info");
			System.out.println("5 for seeing the number of workers");
			System.out.println("Anything else for quiting");
			System.out.println();


			String menuChoice = sc.next();
			if (menuChoice.equals("1")) {
				createWorker();
				again = true;
			}else if (menuChoice.equals("2")) {

				createStudent();
				again = true;
			}else if (menuChoice.equals("3")) {
				printStudents();
				again = true;
			}else if (menuChoice.equals("4")) {
				printWorkers();
				again = true;
			}else if (menuChoice.equals("5")) {
				countWorkers();
				again = true;
			}

		}while(again);

	}


}





