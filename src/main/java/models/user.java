package models;

//import com.vacationRequests.VacationRequests;

public class user {

    private int userId;
    private String firstname;
    private String profilePic;
    private boolean isAdmin;

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
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
/*    private String id;
    private String firstname;
    private String lastname;
    private boolean isAdmin;
    private VacationRequests vacationRequests;
    private IneligiblePeriod ineligiblePeriod;

    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public VacationRequests getVacationRequests() {
        return vacationRequests;
    }
    public void setVacationRequests(VacationRequests vacationRequests) {
        this.vacationRequests = vacationRequests;
    }
    public IneligiblePeriod getIneligiblePeriod() {
        return ineligiblePeriod;
    }
    public void setIneligiblePeriod(IneligiblePeriod ineligiblePeriod) {
        this.ineligiblePeriod = ineligiblePeriod;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    */
}
