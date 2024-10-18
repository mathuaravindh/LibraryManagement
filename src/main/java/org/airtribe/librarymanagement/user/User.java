package org.airtribe.librarymanagement.user;

public class User {
    private int userId;
    private String userName;
    private String email;

    public User(int userId, String userName, String email)
    {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail(){
        return email;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail()
    {
        this.email = email;
    }
}
