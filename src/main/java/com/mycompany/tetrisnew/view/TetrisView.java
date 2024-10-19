package com.mycompany.tetrisnew.view;

import com.mycompany.tetrisnew.model.HighScores;
import com.mycompany.tetrisnew.model.TetrisBoard;
import com.mycompany.tetrisnew.model.TetrisGame;
import com.mycompany.tetrisnew.model.Tetromino;

import java.awt.Point;
import java.io.IOException;

public class TetrisView {
    private final TetrisGame game;
    private final HighScores highScores;

    public TetrisView(TetrisGame game, HighScores highScores) {
        this.game = game;
        this.highScores = highScores;
    }

    public void display() {
        drawBoard();
        drawNextTetromino();
        drawScore();
        drawHighScores();
    }

    private void drawBoard() {
        int[][] grid = game.getBoard().getGrid();
        Tetromino currentTetromino = game.getBoard().getCurrentPiece();

        char[][] boardBuffer = new char[TetrisBoard.ROW_COUNT][TetrisBoard.COL_COUNT];
        for (int i = 0; i < TetrisBoard.ROW_COUNT; i++) {
            for (int j = 0; j < TetrisBoard.COL_COUNT; j++) {
                boardBuffer[i][j] = switch (grid[i][j]) {
                    case 0 -> ' ';
                    case 2 -> 'I';
                    case 3 -> 'J';
                    case 4 -> 'L';
                    case 5 -> 'O';
                    case 6 -> 'S';
                    case 7 -> 'T';
                    case 8 -> 'Z';
                    default -> '?';
                };
            }
        }

        for (Point block : currentTetromino.getBlocks()) {
            boardBuffer[block.y][block.x] = switch (currentTetromino.getColorToInt()) {
                case 2 -> 'I';
                case 3 -> 'J';
                case 4 -> 'L';
                case 5 -> 'O';
                case 6 -> 'S';
                case 7 -> 'T';
                case 8 -> 'Z';
                default -> '?';
            };
        }

        for (char[] row : boardBuffer) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    private void drawNextTetromino() {
        Tetromino nextTetromino = game.getNextTetromino();
        System.out.println("Next Tetromino:");
        for (int[] shape : nextTetromino.getShape()) {
            for (int j = 0; j < shape.length; j++) {
                System.out.print(shape[j] != 0 ? 'X' : ' ');
            }
            System.out.println();
        }
    }

    private void drawScore() {
        System.out.println("Score: " + game.getScore());
    }

    private void drawHighScores() {
        System.out.println("High Scores:");
        for (HighScores.ScoreEntry score : highScores.getScores()) {
            System.out.println(score.getPlayerName() + ": " + score.getScore());
        }
    }

    public void showGameOverDialog() {
        System.out.println("Game Over!");
        String playerName = System.console().readLine("Enter your name: ");
        if (playerName != null && !playerName.trim().isEmpty()) {
            highScores.addScore(playerName, game.getScore());
        }
        String playAgain = System.console().readLine("Play again? (yes/no): ");
        if ("yes".equalsIgnoreCase(playAgain)) {
            game.startGame();
        } else {
            game.exitGame();
        }
    }
    
    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error clearing console: " + ex.getMessage());
        }
    }

    public void updateView() {
        clearConsole();
        display();
    }
}
