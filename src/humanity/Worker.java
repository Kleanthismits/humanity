package humanity;

public class Worker {
	Connect db = new Connect();
	private String fname;
	private String lname;
	private int wSalary;
	private int wHours;
	private double earnPDay;

	public Worker() {
		// TODO Auto-generated constructor stub
	}
	
	public Worker(String fname, String lname, int wSalary, int wHours) {
		this.fname = fname.substring(0,1).toUpperCase()+ fname.substring(1);
		this.lname = lname.substring(0, 1).toUpperCase()+ lname.substring(1);
		this.wSalary = wSalary;
		this.wHours = wHours;
	
	}


	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public int getwSalary() {
		return wSalary;
	}

	public int getwHours() {
		return wHours;
	}

	public double getEarnPDay() {
		return earnPDay;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setwSalary(int wSalary) {
		this.wSalary = wSalary;
	}

	public void setwHours(int wHours) {
		this.wHours = wHours;
	}

	public void setEarnPDay(double earnPDay) {
		this.earnPDay = earnPDay;
	}

}
