package paf2022.batch2_assessment.Models;

import java.sql.Date;

// TODO: Task 4

public class Task {

    private String userName;
    private String description;
    private int priority;
    private Date dueDate;
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    @Override
    public String toString() {
        return "Task [userName=" + userName + ", description=" + description + ", priority=" + priority + ", dueDate="
                + dueDate + "]";
    }

}
