package org.airtribe.librarymanagement.user;

public class Librarian extends User {
    private String employeeCode;

    public Librarian(int userId, String userName, String employeeId, String email) {
        super(userId, userName, email);
        this.employeeCode = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeId(String employeeCode) {
        this.employeeCode = employeeCode;
    }

}
