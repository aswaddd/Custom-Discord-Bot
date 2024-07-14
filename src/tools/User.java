package tools;


public class User {
    private String id;
    private String username;
    private String studentID;
    private String studentName;
    private String registrationCode;

    public User(String studentID, String registrationCode, String studentName, String id, String username) {
        this.studentID = studentID;
        this.registrationCode = registrationCode;
        this.studentName = studentName;
        this.id = id;
        this.username = username;
    }
    public User(String studentID, String registrationCode, String studentName) {
        this.studentID = studentID;
        this.registrationCode = registrationCode;
        this.studentName = studentName;
    }
    public String getID() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getStudentID() {
        return studentID;
    }
    public String getStudentName() {
        return studentName;
    }
    public String getRegistrationCode() {
        return registrationCode;
    }
    public void setID(String id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    @Override
    public String toString() {
        if (!registrationCode.equals("-"))
            return "" + studentID + "," + registrationCode + "," + studentName; // used when writing back to the file in UserManagementBot
        return "" + studentID + "," + registrationCode + "," + studentName + "," + id + "," + username;
    }
}
