package mvc.beans;

public class Object {
	private int id;
	private String desctiption;
	private String type;
	private int parantId;

public Object(int id, String desctiption, String type, int parantId) {
	this.id = id;
	this.desctiption = desctiption;
	this.type = type;
	this.parantId = parantId;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getDesctiption() {
	return desctiption;
}

public void setDesctiption(String desctiption) {
	this.desctiption = desctiption;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public int getParentId() {
	return parantId;
}

public void setParantId(int parantId) {
	this.parantId = parantId;
}
}
