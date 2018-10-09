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
public class Course {
    
    private String subjectName;
    private String courseLink;
    private String courseName;
    private String courseCode;
    private String UOC;
    private String conditions;
    private String exclusionCourses;
    private String faculty;
    private String school;
    private String offeringTerm;
    private int courseID;

    public Course(String subjectName, String courseLink, String courseName, String courseCode, String UOC, String conditions, String exclusionCourses, String faculty, String school, String offeringTerm, int courseID) {
        this.subjectName = subjectName;
        this.courseLink = courseLink;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.UOC = UOC;
        this.conditions = conditions;
        this.exclusionCourses = exclusionCourses;
        this.faculty = faculty;
        this.school = school;
        this.offeringTerm = offeringTerm;
        this.courseID = courseID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getUOC() {
        return UOC;
    }

    public void setUOC(String UOC) {
        this.UOC = UOC;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getExclusionCourses() {
        return exclusionCourses;
    }

    public void setExclusionCourses(String exclusionCourses) {
        this.exclusionCourses = exclusionCourses;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getOfferingTerm() {
        return offeringTerm;
    }

    public void setOfferingTerm(String offeringTerm) {
        this.offeringTerm = offeringTerm;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
    
    
    
    
}
