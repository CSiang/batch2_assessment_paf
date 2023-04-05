package paf2022.batch2_assessment.Models;

public class User {
    
    private String userId;
    private String userName;
    private String name;
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", name=" + name + "]";
    }
    
    

}
