package models;

import java.util.Date;

public class VacationRequest {
    
    private int id;
    private Date periodStart;
    private Date periodEnd;
    private String title;
    private int requestStatus;
    private String ownerEmail;
    
    public VacationRequest(int id,Date periodStart, Date periodEnd, String title, int requestStatus, String ownerEmail) {
        this.id = id;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.title = title;
        this.requestStatus = requestStatus;
        this.ownerEmail = ownerEmail;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getPeriodStart() {
        return periodStart;
    }
    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }
    public Date getPeriodEnd() {
        return periodEnd;
    }
    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }
    public void setRequestStatus(int requestStatus) {
        this.requestStatus = requestStatus;
    }
    public int getRequestStatus(){
        return requestStatus;
    }
    public String getOwnerEmail() {
        return ownerEmail;
    }
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
    
}
