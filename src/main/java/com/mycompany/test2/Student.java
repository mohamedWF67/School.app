package com.mycompany.test2;

import java.util.Date;

public class Student extends User {
    private String parentName;
    private Date DOB;
    private String gender;
    private String mobileNo;
    private String address;
    private String section;

    public Student() {
        super();
    }
    public Student(int id, String name, String email, String password, String parentName, Date DOB, String gender, String mobileNo,String address, String section) {
        super(id, name, email, password);
        this.parentName = parentName;
        this.DOB = DOB;
        this.gender = gender;
        this.mobileNo = mobileNo;
        this.section = section;
        this.address = address;
    }

    public Student(String name, String email, String password, String parentName, Date DOB, String gender, String mobileNo,String address, String section) {
        super(name, email, password);
        this.parentName = parentName;
        this.DOB = DOB;
        this.gender = gender;
        this.mobileNo = mobileNo;
        this.address = address;
        this.section = section;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

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
        return "Student{" +
                "parentName='" + parentName + '\'' +
                ", DOB=" + DOB +
                ", gender='" + gender + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", address='" + address + '\'' +
                ", section='" + section + '\'' +
                "} " + super.toString();
    }
}