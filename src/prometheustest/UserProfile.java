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
    
    private String course;
    private int startingYear;
    private int completionYear;
    
    public UserProfile(String course, int startingYear, int completionYear) {
        this.course = course;
        this.startingYear = startingYear;
        this.completionYear = completionYear;
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

    public int getCompletionYear() {
        return completionYear;
    }

    public void setCompletionYear(int completionYear) {
        this.completionYear = completionYear;
    }
    
    
}
