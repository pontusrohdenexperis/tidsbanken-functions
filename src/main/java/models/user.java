package models;

//import com.vacationRequests.VacationRequests;

public class User{
    
    private String email;
    private String firstname;
    private String lastname;
    private String profilePic;
    private boolean isAdmin;
    
    public User( String email, String firstname, String lastname, String profilePic, boolean isAdmin) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profilePic = profilePic;
        this.isAdmin = isAdmin;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
