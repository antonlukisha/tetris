package com.mycompany.tetrisnew;

import com.mycompany.tetrisnew.controller.MainController;

public class Main {
    public static void main(String[] args) {
        // Создание контроллера игрового меню и передача ему главного окна
        MainController mainController = new MainController();
        
        // Запуск главного окна и инициализация контроллеров
        mainController.startNewGame();
    }
}
