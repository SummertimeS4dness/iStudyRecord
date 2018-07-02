package mvc.beans;

public class Object {
    private int id;
    private String description;
    private String type;
    private int parentId;

    public Object() {
        super();
    }

    public Object(int id, String description, String type, int parentId) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.parentId = parentId;
    }

//    public Object(String description, String type, int parentId) {
//        this.description = description;
//        this.type = type;
//        this.parentId = parentId;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
