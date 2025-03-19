/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.test2;

import java.io.IOException;
import java.util.Date;

/**
 *
 * @author mohamed waleed
 */
public class Test2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        School school = new School("BUE");
        Module M = new Module("SE",'A',20);
        Module M2 = new Module("DSA",'B',30);
        Student[] students = {new Student(),new Student(),new Student(),new Student()};
        school.addModule(M);
        school.addModule(M2);
        school.addUser(new Student("Waleed","mdxx","1234","waleed",new Date(),"M","01024983385",1,"A"));
        school.GenerateReport(1);
        school.GenerateReport(2);
        CLI_interface.CLI_selection(school);
    }
}
