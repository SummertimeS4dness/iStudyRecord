package mvc.beans;

/**
 * Bean Object.
 */
public class Object {
    private int id;
    private String description;
    private String type;
    private int parentId;
    private String parentDescription;

    /**
     * Instantiates a new Object.
     */
    public Object() {
        super();
    }

    /**
     * Instantiates a new Object.
     *
     * @param id          bean's id
     * @param description bean's description
     * @param type        bean's type
     * @param parentId    bean's parent's id
     */
    public Object(int id, String description, String type, int parentId) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.parentId = parentId;
    }

    /**
     * Instantiates a new Object.
     *
     * @param id bean's id
     */
    public Object(int id) {
        this.id = id;
    }

    /**
     * Gets parent's description.
     *
     * @return bean's parent's description
     */
    public String getParentDescription() {
    return parentDescription;
}

    /**
     * Sets bean's parent's description.
     *
     * @param parentDescription bean's parent's description
     */
    public void setParentDescription(String parentDescription) {
    this.parentDescription = parentDescription;
}

    /**
     * Gets bean's id.
     *
     * @return bean's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets bean's id.
     *
     * @param id bean's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets bean's description.
     *
     * @return bean's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets bean's description.
     *
     * @param description bean's description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets bean's type.
     *
     * @return bean's type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets bean's type.
     *
     * @param type bean's type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets bean's parent's id.
     *
     * @return bean's parent's id
     */
    public int getParentId() {
        return parentId;
    }

    /**
     * Sets bean's parent's id.
     *
     * @param parentId bean's parent's id
     */
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
