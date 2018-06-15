package mvc.beans;

import java.util.Date;

public class Lesson {
    private Date date;

    public Lesson(Date date) {
        this.date = (Date)date.clone();
    }

    public Lesson() {
        super();
    }

    public void setDate(Date date) {
        this.date = (Date)date.clone();
    }

    public Date getDate() {
        return date;
    }
}
