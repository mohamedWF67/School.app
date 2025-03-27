package com.mycompany.School_app;

public class Teacher extends User{

    //Attributes and collections
    private String qualification;
    private int salary;
    private String mobileNo;
    private String address;

    //Empty Constructor
    public Teacher() {
        super();
    }

    //Constructor with id as an input
    public Teacher(int id, String name, String email, String password, String qualification, int salary, String mobileNo, String address) {
        super(id, name, email, password);
        this.qualification = qualification;
        this.salary = salary;
        this.mobileNo = mobileNo;
        this.address = address;
    }

    //Constructor with id automatic
    public Teacher(String name, String email, String password, String qualification, int salary, String mobileNo, String address) {
        super(name, email, password);
        this.qualification = qualification;
        this.salary = salary;
        this.mobileNo = mobileNo;
        this.address = address;
    }

    //Getter for qualification
    public String getQualification() {
        return qualification;
    }

    //Setter for qualification
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    //Getter for salary
    public int getSalary() {
        return salary;
    }

    //Setter for salary
    public void setSalary(int salary) {
        this.salary = salary;
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

    //Setter for address
    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return super.toString() + " Teacher{" +
                "Qualification='" + qualification + '\'' +
                ", Salary=" + salary +
                ", MobileNo='" + mobileNo + '\'' +
                ", Address='" + address + '\'' +
                "}";
    }
}
