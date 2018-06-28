package mvc.beans;

public class Subject {
	private int id;
	private String shortName;
	private String fullName;
	private String info;
	private int lecturerId;

public Subject(int id, String shortName, String fullName, String info, int lecturerId) {
	this.id = id;
	this.shortName = shortName;
	this.fullName = fullName;
	this.info = info;
	this.lecturerId = lecturerId;
}
public Subject(){}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getShortName() {
	return shortName;
}

public void setShortName(String shortName) {
	this.shortName = shortName;
}

public String getFullName() {
	return fullName;
}

public void setFullName(String fullName) {
	this.fullName = fullName;
}

public String getInfo() {
	return info;
}

public void setInfo(String info) {
	this.info = info;
}

public int getLecturerId() {
	return lecturerId;
}

public void setLecturerId(int lecturerId) {
	this.lecturerId = lecturerId;
}
}
