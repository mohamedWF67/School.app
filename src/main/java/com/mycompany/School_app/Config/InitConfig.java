package com.mycompany.School_app.Config;

import com.mycompany.School_app.AuthSystem.Auth;
import com.mycompany.School_app.AuthSystem.NewJFrame;
import com.mycompany.School_app.ThemeManger.ThemeManager;
import com.mycompany.School_app.ThemeManger.ThemeUtils;

import java.io.FileNotFoundException;

public class InitConfig {
    public static final InitConfig INSTANCE;

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
        ThemeUtils.reloadLookAndFeel();
        new Auth().setVisible(true);
    }

    private void onClose(){
        ThemeManager.CloseThemeManager();
        System.out.println("👋 Good bye..");
    }
}
