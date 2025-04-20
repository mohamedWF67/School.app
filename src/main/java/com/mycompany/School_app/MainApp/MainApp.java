package com.mycompany.School_app.MainApp;

import com.mycompany.School_app.*;
import com.mycompany.School_app.AuthSystem.AuthManager;
import com.mycompany.School_app.LibrarySystem.Librarian;
import com.mycompany.School_app.Module;
import com.mycompany.School_app.StatusSystem.Status;
import com.mycompany.School_app.StatusSystem.StatusManager;
import com.mycompany.School_app.User.Student;
import com.mycompany.School_app.User.Teacher;
import com.mycompany.School_app.User.User;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class MainApp {
    static StatusManager statusManager = new StatusManager();
    static Status status;
    static User user;
    static School school;

    public MainApp(User user){
        this.user = user;
        //TODO fix the handler
        this.school = Data_Handler.getSchool();
        if (user instanceof Admin){
            new AdminAppUI((Admin) user).setVisible(true);
        } else if (user instanceof Student) {
            new StudentAppUI((Student) user);
        } else if (user instanceof Teacher) {
            new TeacherAppUI().setVisible(true);
        } else if (user instanceof Librarian) {
            new LibrarianAppUI().setVisible(true);
        }
    }

    public static void logout() {
        MainApp.user = null;
        Data_Handler.StartAuthManager();
    }

    public static void deleteAccount() {
        school.removeUser(user.getId());
        logout();
    }

    protected static void setStatus(int errorCode,String statusMessage) {
        status = statusManager.addStatus(new Status(errorCode,statusMessage));
        System.out.println(statusMessage);
        if (status.getErrorCode() == 0){
            JOptionPane.showMessageDialog(null,statusMessage,"Error",JOptionPane.ERROR_MESSAGE);
        }

        if (status.getErrorCode() == -2){
            JOptionPane.showMessageDialog(null,statusMessage,"Success",JOptionPane.INFORMATION_MESSAGE);
        }
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

        if (school.emailExists(email) && !email.equals(student.getEmail())){
            setStatus(4,"Email already exists");
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

    protected static ArrayList<Grade> getStudentGrades(Student student) {
       return school.getStudentGrades(student.getId());
    }

    protected static ArrayList<Module> getCompatibleModules(Student student) {
        return school.getCompatibleModules(student);
    }

    protected static ArrayList<Student> getStudents() {
        return school.getStudents();
    }

    protected static HashSet<Module> getStudentModules(Student student) {
        return school.getStudentModules(student);
    }

    protected static boolean addModule(Student student, int moduleIndex) {
        return school.enrollStudent(student, moduleIndex);
    }

    protected static boolean cancelModule(Student student, String moduleName) {
        return school.cancelEnrollment(student, moduleName);
    }

    protected static User getThisUser(int id) {
        User user = school.getUser(id);
        if (user == null){
            user = school.getLibrary().getLibrarian(id);
        }

        return user;
    }

    protected static void editAdmin(Admin admin,String name, String email, String password){
        if (name.isEmpty()){
            setStatus(1,"Please enter a name");
            return;
        }

        if (email.isEmpty()){
            setStatus(2,"Please enter an email");
            return;
        }
        if (school.emailExists(email) && !email.equals(admin.getEmail())){
            setStatus(2,"Email already exists");
            return;
        }

        if (!password.isEmpty()){
            admin.setHashedPassword(password);
        }

        admin.setName(name);
        admin.setEmail(email);

        setStatus(-1,"Admin Updated Successfully");
    }

    public static void deleteUser(int id) {
        school.removeUser(id);
        setStatus(-2,"Deleted Successfully");
    }

}
