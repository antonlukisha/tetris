package com.mycompany.tetrisnew.controller;

import com.mycompany.tetrisnew.model.HighScores;
import com.mycompany.tetrisnew.view.HighScoresView;

public class HighScoresController {
    private HighScoresView highScoresView;
    private HighScores highScores;

    public HighScoresController(HighScoresView highScoresView, HighScores highScores) {
        this.highScoresView = highScoresView;
        this.highScores = highScores;
    }
}
