package models;

import java.util.Date;

public class IneligiblePeriod {
    
    private String id;
    private Date periodStart;
    private Date periodEnd;
 
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
}
