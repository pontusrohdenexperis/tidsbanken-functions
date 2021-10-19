package models;

//import com.vacationRequests.VacationRequests;

public class User{

    private int userId;
    private String firstname;
    private String lastname;
    private String profilePic;
    private boolean isAdmin;
    
    public User(int userId, String firstname, String lastname, String profilePic, boolean isAdmin) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profilePic = profilePic;
        this.isAdmin = isAdmin;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getProfilePic() {
        return profilePic;
    }
    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
