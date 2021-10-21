package models;

import java.sql.Timestamp;

public class IneligiblePeriod {
    private int id;
    private Timestamp periodStart;
    private Timestamp periodEnd;
    private String createdBy;
    
    public IneligiblePeriod(int id, Timestamp periodStart, Timestamp periodEnd, String createdBy) {
        this.id = id;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.createdBy = createdBy;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Timestamp getPeriodStart() {
        return periodStart;
    }
    public void setPeriodStart(Timestamp periodStart) {
        this.periodStart = periodStart;
    }
    public Timestamp getPeriodEnd() {
        return periodEnd;
    }
    public void setPeriodEnd(Timestamp periodEnd) {
        this.periodEnd = periodEnd;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
