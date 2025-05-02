package com.mycompany.School_app.Config;

import com.mycompany.School_app.CLI_interface;
import com.mycompany.School_app.Data_Handler;
import com.mycompany.School_app.StatusSystem.StatusManager;
import com.mycompany.School_app.ThemeManger.ThemeManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class InitConfig {
    public static final InitConfig INSTANCE;
    public static final boolean DEVMODE = false;

    static {
        try {
            INSTANCE = new InitConfig();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private InitConfig() throws FileNotFoundException {
        System.out.println("🔧 InitConfig started!");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> onClose()));

        ThemeManager.InitThemeManager();

        new Data_Handler("BSE").Init_School();

        Data_Handler.loadAllData();

        CLI_interface.CLI_selection(Data_Handler.getSchool());
    }

    private void onClose(){
        ThemeManager.CloseThemeManager();

        Data_Handler.saveAllData();

        StatusManager.createLogFile();

        System.out.println("👋 Good bye..");
    }
}
