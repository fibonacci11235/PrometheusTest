/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prometheustest;

import java.io.Serializable;

/**
 *
 * @author josiah
 */
public class Subject implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String subjectCode = "";
    private int subjectUnits;
    private String subjectName;
    private String prerequisites;
    private String offering;
    private String faculty; 
    private String URL;

    public Subject(String subjectCode, int subjectUnits, String subjectName, 
                    String prerequisites, String offering, String faculty, String URL) {
        this.subjectCode = subjectCode;
        this.subjectUnits = subjectUnits;
        this.subjectName = subjectName;
        this.prerequisites = prerequisites;
        this.offering = offering;
        this.faculty = faculty;
        this.URL = URL;
    }


    //Getters
    public String getSubjectCode() {
        return subjectCode;
    }
    
    public int getSubjectUnits() {
        return subjectUnits;
    }
    
    public String getSubjectName() {
        return subjectName;
    }
    
    public String getPrerequisites() {
        return prerequisites;
    }
    
    public String getOffering() {
        return offering;
    }
    
    public String getFaculty() {
        return faculty;
    }
    
    public String getURL() {
        return URL;
    }
    
    //Setters
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    
    public void setSubjectUnits(int subjectUnits) {
        this.subjectUnits = subjectUnits;
    }
    
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName; 
    }
    
    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }
    
    public void setOffering(String offering) {
        this.offering = offering;
    }
    
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    
    public void setURL(String URL) {
        this.URL = URL;
    }
    
    @Override
    public String toString() {
        return subjectCode;
    }
    
}
