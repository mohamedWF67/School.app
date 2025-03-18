package com.mycompany.test2;

import java.util.ArrayList;
import java.util.Date;

public class Student extends User {
    private String parentName;
    private Date DOB;
    private String gender;
    private String mobileNo;
    private String address;
    private int classId;
    private String section;

    public Student() {
        super();
    }
    public Student(int id, String name, String email, String password, String parentName, Date DOB, String gender, String mobileNo, int classId, String section) {
        super(id, name, email, password);
        this.parentName = parentName;
        this.DOB = DOB;
        this.gender = gender;
        this.mobileNo = mobileNo;
        this.classId = classId;
        this.section = section;
    }

    public Student(String name, String email, String password, String parentName, Date DOB, String gender, String mobileNo, int classId, String section) {
        super(name, email, password);
        this.parentName = parentName;
        this.DOB = DOB;
        this.gender = gender;
        this.mobileNo = mobileNo;
        this.classId = classId;
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

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return super.toString() + " Student{" +
                "parentName='" + parentName + '\'' +
                ", DOB=" + DOB +
                ", gender='" + gender + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", classId=" + classId +
                ", section='" + section + '\'' +
                "}";
    }
}