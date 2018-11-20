package humanity;

public class Student {

	private String fname;
	private String lname;
	private String facNumber;
	
	
	public Student(String fname, String lname, String facNumber) {
		this.fname = fname.substring(0,1).toUpperCase()+ fname.substring(1);
		this.lname = lname.substring(0, 1).toUpperCase()+ lname.substring(1);
		this.facNumber = facNumber;
	
	}


	public String getFname() {
		return fname;
	}


	public String getLname() {
		return lname;
	}


	public String getFacNumber() {
		return facNumber;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public void setFacNumber(String facNumber) {
		this.facNumber = facNumber;
	}

}
