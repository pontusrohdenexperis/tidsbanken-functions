package models;

import java.util.Date;

public class VacationRequest {
    
    private String id;
    private String title;
    private Date periodStart;
    private Date periodEnd;
    private String requestStatus;
    private Comment[] comments;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
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
    public String getRequestStatus() {
        return requestStatus;
    }
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }
    public Comment[] getComments() {
        return comments;
    }
    public void setComments(Comment[] comments) {
        this.comments = comments;
    }
    
}
