/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.test2;

import java.io.IOException;

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
        school.GenerateReport(1);
        school.GenerateReport(2);
        CLI_interface.CLI_selection(school);
    }
}
