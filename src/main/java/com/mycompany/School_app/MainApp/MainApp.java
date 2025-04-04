package com.mycompany.School_app.MainApp;

import com.mycompany.School_app.Admin;
import com.mycompany.School_app.LibrarySystem.Librarian;
import com.mycompany.School_app.User.Student;
import com.mycompany.School_app.User.Teacher;
import com.mycompany.School_app.User.User;

public class MainApp {
    User user;

    public MainApp(User user){
        this.user = user;
        if (user instanceof Admin){
            new AdminAppUI().setVisible(true);
        } else if (user instanceof Student) {
            new StudentAppUI(user);
        } else if (user instanceof Teacher) {
            new StudentAppUI().setVisible(true);
        } else if (user instanceof Librarian) {
            new StudentAppUI().setVisible(true);
        }
    }
}
