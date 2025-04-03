package com.mycompany.School_app.ThemeManger;

import javax.swing.*;
import java.awt.*;

public class ThemeUtils {
    public static void reloadLookAndFeel() {
        for (Frame frame : Frame.getFrames()) {
            SwingUtilities.updateComponentTreeUI(frame);
            frame.invalidate();
            frame.validate();
            frame.repaint();
        }
    }
}
