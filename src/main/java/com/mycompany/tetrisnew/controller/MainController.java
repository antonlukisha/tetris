package com.mycompany.tetrisnew.controller;

import com.mycompany.tetrisnew.model.HighScores;
import com.mycompany.tetrisnew.model.TetrisGame;
import com.mycompany.tetrisnew.view.TetrisView;

public class MainController {
    private final HighScores highScores;
    
    public MainController() {
        highScores = new HighScores();
    }
    
    public void startNewGame() {
        // Создание экземпляра модели TetrisGame
        TetrisGame tetrisGame = new TetrisGame();
        // Создание экземпляра представления TetrisView и передача ему TetrisBoard и HighScores
        TetrisView tetrisView = new TetrisView(tetrisGame, highScores);
        // Создание экземпляра контроллера TetrisController и передача ему TetrisView и TetrisGame
        TetrisController tetrisController = new TetrisController(tetrisView, tetrisGame);
        tetrisController.start();
    }
    
    public HighScores getHighScores() {
        return highScores;
    }
}
