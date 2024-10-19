package com.mycompany.tetrisnew.controller;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.mycompany.tetrisnew.view.MainWindow;

public class MenuController {
    
    private final MainWindow mainWindow;
    private final MainController mainController;

    public MenuController() {
        mainWindow = new MainWindow();
        mainController = new MainController();
        initializeMenu();
    }

    private void initializeMenu() {

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        // Добавление слушателей событий для пунктов меню
        newGameMenuItem.addActionListener(event -> { 
            mainController.startNewGame();
        });

        exitMenuItem.addActionListener(event -> {
            System.exit(0);
        });

        // Добавление пунктов меню в меню
        fileMenu.add(newGameMenuItem);
        fileMenu.add(exitMenuItem);

        // Добавление меню в главное окно
        menuBar.add(fileMenu);
        mainWindow.setJMenuBar(menuBar);
    }
    
    public void startTetris() {
        mainWindow.setVisible(true);
    }
}
