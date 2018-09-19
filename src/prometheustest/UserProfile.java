/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prometheustest;

/**
 *
 * @author josiah
 */
public class UserProfile {
    
    private String course = "";
    private int startingYear;
    private String duration;
    
    public UserProfile(String course, int startingYear, String duration) {
        this.course = course;
        this.startingYear = startingYear;
        this.duration = duration;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getStartingYear() {
        return startingYear;
    }

    public void setStartingYear(int startingYear) {
        this.startingYear = startingYear;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    

    
}
