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

    public Subject(String subjectCode, int subjectUnits, String subjectName, 
                    String prerequisites, String offering) {
        this.subjectCode = subjectCode;
        this.subjectUnits = subjectUnits;
        this.subjectName = subjectName;
        this.prerequisites = prerequisites;
        this.offering = offering;
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
    
    @Override
    public String toString() {
        return subjectCode;
    }
    
}
