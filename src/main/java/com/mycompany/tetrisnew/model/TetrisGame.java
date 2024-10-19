package com.mycompany.tetrisnew.model;

import java.util.ArrayList;

public class TetrisGame {
    private final TetrisBoard board;
    private final ArrayList<Tetromino> tetrominos;
    private int score;
    private boolean isGameOver;
    private boolean isPaused;

    public TetrisGame() {
        board = new TetrisBoard();
        tetrominos = new ArrayList<>();
        tetrominos.add(board.getCurrentPiece());
        score = 0;
        isGameOver = false;
    }

    public void update() {
        if (!isGameOver && !isPaused) {
            board.update();
            int linesCleared = board.getNumFilledRows();
            updateScore(linesCleared);
            board.setNullNumFilledRows();
            if (board.isGameOver()) {
                isGameOver = true;
            }
            if (tetrominos.getLast() != board.getCurrentPiece()) {
                tetrominos.add(board.getCurrentPiece());
            }
        }
    }
    
    public void updateScore(int linesCleared) {
        switch (linesCleared) {
            case 1 -> score += 100;
            case 2 -> score += 300;
            case 3 -> score += 500;
            case 4 -> score += 800;
        }
    }
    
    public Tetromino getNextTetromino() {
        return board.getNextPiece();
    }

    public void moveLeft() {
        if (!isGameOver) {
            board.moveLeft();
        }
    }
    
    public void moveAbsDown() {
        if (!isGameOver) {
            board.moveAbsDown();
        }
    }

    public void moveRight() {
        if (!isGameOver) {
            board.moveRight();
        }
    }

    public void rotateClockwise() {
        if (!isGameOver) {
            board.rotateClockwise();
        }
    }

    public void rotateCounterClockwise() {
        if (!isGameOver) {
            board.rotateCounterClockwise();
        }
    }

    public TetrisBoard getBoard() {
        return board;
    }

    public ArrayList<Tetromino> getTetrominos() {
        return tetrominos;
    }

    public int getScore() {
        return score;
    }

    public void startGame() {
        isGameOver = false;
        isPaused = false;
        score = 0;
        board.clearBoard();
        tetrominos.clear();
        tetrominos.add(board.getCurrentPiece());
    }

    public void pauseGame() {
        isPaused = true;
    }

    public void resumeGame() {
        isPaused = false;
    }

    public void exitGame() {
        System.exit(0);
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
