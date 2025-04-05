package com.mycompany.School_app.MainApp;

import com.mycompany.School_app.Admin;
import com.mycompany.School_app.AuthSystem.AuthManager;
import com.mycompany.School_app.Data_Handler;
import com.mycompany.School_app.LibrarySystem.Librarian;
import com.mycompany.School_app.StatusSystem.Status;
import com.mycompany.School_app.StatusSystem.StatusManager;
import com.mycompany.School_app.User.Student;
import com.mycompany.School_app.User.Teacher;
import com.mycompany.School_app.User.User;

import java.util.Date;

public class MainApp {
    static StatusManager statusManager = new StatusManager();
    static Status status;
    static User user;

    public MainApp(User user){
        this.user = user;
        if (user instanceof Admin){
            new AdminAppUI().setVisible(true);
        } else if (user instanceof Student) {
            new StudentAppUI((Student) user);
        } else if (user instanceof Teacher) {
            new StudentAppUI().setVisible(true);
        } else if (user instanceof Librarian) {
            new StudentAppUI().setVisible(true);
        }
    }

    public static void logout() {
        MainApp.user = null;
        Data_Handler.StartAuthManager();
    }

    protected static void setStatus(int errorCode,String statusMessage) {
        status = statusManager.addStatus(new Status(errorCode,statusMessage));
        System.out.println(statusMessage);
    }

    public static Status getStatus() {
        return status;
    }

    protected static void editStudent(Student student, String section, String name, String parentName, String email, String password, String mobileNo, String address, Date birthday, String Gender){
        if (section.isEmpty()){
            setStatus(1,"Please select a section");
            return;
        }
        if (name.isEmpty()){
            setStatus(2,"Please enter a name");
            return;
        }
        if (parentName.isEmpty()){
            setStatus(3,"Please enter a parent name");
            return;
        }
        if (email.isEmpty()){
            setStatus(4,"Please enter an email");
            return;
        }

        if (mobileNo.isEmpty()){
            setStatus(6,"Please enter a mobile number");
            return;
        }
        if (address.isEmpty()){
            setStatus(7,"Please enter a address");
            return;
        }
        if (birthday==null){
            setStatus(8,"Please Select a birthday");
            return;
        }

        student.setSection(section);

        student.setName(name);
        student.setParentName(parentName);

        student.setEmail(email);

        if (!password.isEmpty()){
            student.setHashedPassword(password);
        }

        student.setMobileNo(mobileNo);
        student.setAddress(address);

        student.setDOB(birthday);
        student.setGender(Gender);
        setStatus(-1,"Student Updated Successfully");
    }


}
