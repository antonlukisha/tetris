package com.mycompany.tetrisnew.view;

import javax.swing.*;

public class MainWindow extends JFrame {
    
    public MainWindow() {
        setTitle("Tetris");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel();
        JLabel titleLabel = new JLabel("Welcome to Tetris");
        mainPanel.add(titleLabel);
        add(mainPanel);
    }
}
