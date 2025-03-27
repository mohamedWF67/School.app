package com.mycompany.School_app;

import java.util.Date;

public class Student extends User {
    //Attributes and collections
    private String parentName;
    private Date DOB;//Date of birth
    private String gender;
    private String mobileNo;
    private String address;
    private String section;

    //Empty Constructor
    public Student() {
        super();
    }

    //Constructor with id as an input
    public Student(int id, String name, String email, String password, String parentName, Date DOB, String gender, String mobileNo,String address, String section) {
        super(id, name, email, password);
        this.parentName = parentName;
        this.DOB = DOB;
        this.gender = gender;
        this.mobileNo = mobileNo;
        this.section = section;
        this.address = address;
    }

    //Constructor with id automatic
    public Student(String name, String email, String password, String parentName, Date DOB, String gender, String mobileNo,String address, String section) {
        super(name, email, password);
        this.parentName = parentName;
        this.DOB = DOB;
        this.gender = gender;
        this.mobileNo = mobileNo;
        this.address = address;
        this.section = section;
    }

    //Getter for parent name
    public String getParentName() {
        return parentName;
    }

    //Setter for parent name
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    //Getter for date
    public Date getDOB() {
        return DOB;
    }

    //Setter for date
    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    //Getter for gender
    public String getGender() {
        return gender;
    }

    //Setter for gender
    public void setGender(String gender) {
        this.gender = gender;
    }

    //Getter for mobile number
    public String getMobileNo() {
        return mobileNo;
    }

    //Setter for mobile number
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    //Getter for address
    public String getAddress() {
        return address;
    }

    //Setter fro address
    public void setAddress(String address) {
        this.address = address;
    }

    //Getter for section
    public String getSection() {
        return section;
    }

    //Setter for section
    public void setSection(String section) {
        this.section = section;
    }


    //Legacy Enrollment System
    /*public Set<Module> getEnrolledModules() {
        return enrolledModules;
    }

    public void setEnrolledModules(Set<Module> enrolledModules) {
        this.enrolledModules = enrolledModules;
    }

    public void enroll(Module module) {
        if (!module.isFull() && enrolledModules.add(module)) {
            module.addStudent(this);
        }else{
            System.out.println("Failed to add student " + getName() + " in module " + module.getName());
        }
    }

    public void cancelEnrollment(Module module) {
        if (enrolledModules.remove(module)) {
            module.removeStudent(this);
        }else{
            System.out.println("Failed to remove student " + getName() + " from module " + module.getName());
        }
    }

    public void viewCourses() {
        if (!enrolledModules.isEmpty()) {
            System.out.println("Enrolled Modules: " + enrolledModules);
        }else{
            System.out.println("No Enrolled Modules");
        }
    }*/

    @Override
    public String toString() {
        return  super.toString()+"Student{" +
                "parentName='" + parentName + '\'' +
                ", DOB=" + DOB +
                ", gender='" + gender + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", address='" + address + '\'' +
                ", section='" + section + '\'' +
                "} ";
    }
}