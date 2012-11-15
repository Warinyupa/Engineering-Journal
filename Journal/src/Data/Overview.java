/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class Overview {
    private Date date;
    private Double homeWork;
    private Double study;
    private Double activities;
    private Double reading;

    public Date getDate() {
        return date;
    }

    public Double getHomeWork() {
        return homeWork;
    }

    public Double getStudy() {
        return study;
    }

    public Double getActivities() {
        return activities;
    }

    public void setReading(Double reading) {
        this.reading = reading;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHomeWork(Double homeWork) {
        this.homeWork = homeWork;
    }

    public void setActivities(Double activities) {
        this.activities = activities;
    }

    public void setStudy(Double study) {
        this.study = study;
    }

    public Double getReading() {
        return reading;
    }
    
    
}
