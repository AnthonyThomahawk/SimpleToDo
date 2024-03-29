package org.simpleToDo;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static JFrame mainWindow;
    public static MainPanel mainPanel;
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        float dpi = Toolkit.getDefaultToolkit().getScreenResolution();
        String dpiStr = (int) dpi + "dpi";
        System.setProperty("flatlaf.uiScale", dpiStr);
        mainPanel = new MainPanel();
        mainWindow = new JFrame();
        mainWindow.setContentPane(mainPanel);
        mainWindow.pack();
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setTitle("Simple To-Do");
    }
}