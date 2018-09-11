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
    private String subjectName = "";

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    @Override
    public String toString() {
        return subjectName;
    }
    
}
