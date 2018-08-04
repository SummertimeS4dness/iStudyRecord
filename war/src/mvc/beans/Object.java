package mvc.beans;

public class Object {
    private int id;
    private String description;
    private String type;
    private int parentId;
    private String parentDescription;
    public Object() {
        super();
    }

    public Object(int id, String description, String type, int parentId) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.parentId = parentId;
    }

    public Object(int id) {
        this.id = id;
    }

public String getParentDescription() {
    return parentDescription;
}

public void setParentDescription(String parentDescription) {
    this.parentDescription = parentDescription;
}

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

@Override
public String toString() {
    return "Object{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", type='" + type + '\'' +
            ", parentId=" + parentId +
            ", parentDescription='" + parentDescription + '\'' +
            '}';
}

@Override
public boolean equals(java.lang.Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
   Object object = (Object)o;
    
    return id == object.id;
}

@Override
public int hashCode() {
    return id;
}
}
