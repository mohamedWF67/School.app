/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.School_app;
import java.util.Date;

/**
 *
 * @author mohamed waleed
 */
public class School_app {

    public static void main(String[] args) {
        Data_Handler dataHandler = new Data_Handler("BSE").Init_School();
        CLI_interface.CLI_selection(dataHandler.getSchool());
    }
}
