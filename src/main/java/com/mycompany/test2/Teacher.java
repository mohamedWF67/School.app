package com.mycompany.test2;

public class Teacher extends User{
    private String qualification;
    private int salary;
    private String mobileNo;
    private String address;

    public Teacher() {
        super();
    }

    public Teacher(int id, String name, String email, String password, String qualification, int salary, String mobileNo, String address) {
        super(id, name, email, password);
        this.qualification = qualification;
        this.salary = salary;
        this.mobileNo = mobileNo;
        this.address = address;
    }

    public Teacher(String name, String email, String password, String qualification, int salary, String mobileNo, String address) {
        super(name, email, password);
        this.qualification = qualification;
        this.salary = salary;
        this.mobileNo = mobileNo;
        this.address = address;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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
