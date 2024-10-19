package com.mycompany.tetrisnew.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class TetrisBoard {
    public static final int ROW_COUNT = 20;
    public static final int COL_COUNT = 10;
    public static final int BLOCK_SIZE = 30;
    private final int[][] grid;
    private Tetromino currentPiece;
    private int numFilledRows;
    private boolean isGameOver;
    private Tetromino nextPiece;

    public TetrisBoard() {
        this.grid = new int[ROW_COUNT][COL_COUNT];
        this.nextPiece = generateRandomPiece();
        this.currentPiece = generateRandomPiece();
        clearGrid();
        this.numFilledRows = 0;
        this.isGameOver = false;
    }

    public void update() {
        if (!currentPiece.isFalling()) {
            if (fixOnBoard()) {
                currentPiece = nextPiece;
                nextPiece = generateRandomPiece();
            } else {
                isGameOver = true;
            }
        } else {
            moveDown();
        }
    }

    public void moveLeft() {
        currentPiece.moveLeft();
        if (isCollision()) {
            currentPiece.moveRight();
        }
    }

    public void moveRight() {
        currentPiece.moveRight();
        if (isCollision()) {
            currentPiece.moveLeft();
        }
    }
    
    public void moveAbsDown() {
        while (!isCollision()) {
            currentPiece.moveDown();
        }
        if (isCollision()) {
            currentPiece.moveUp();
            fixAndGenerateNewPiece();
        }
    }
    
    private void fixAndGenerateNewPiece() {
        if (fixOnBoard()) {
            currentPiece = nextPiece;
            nextPiece = generateRandomPiece();
            if (isCollision()) {
                isGameOver = true;
            }
        } else {
            isGameOver = true;
        }
        List<int[]> filledRows = getFilledRows();
        if (!filledRows.isEmpty()) {
            this.numFilledRows = filledRows.size();
            for (int[] row : filledRows) {
                clearRow(row[0]);
            }
            afterClearRow(filledRows);
        }
    }
    
    public Tetromino getNextPiece() {
        return nextPiece;
    }

    public void moveDown() {
        currentPiece.moveDown();
        if (isCollision()) {
            currentPiece.moveUp();
            fixAndGenerateNewPiece(); 
        }
    }

    public void clearGrid() {
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void clearBoard() {
        currentPiece = nextPiece;
        nextPiece = generateRandomPiece();
        clearGrid();
        this.numFilledRows = 0;
        this.isGameOver = false;
    }

    public boolean fixOnBoard() {
        if (!isCollision()) {
            
            currentPiece.fixOnBoard(grid);
            return true;
        }
        return false;
    }

    public void rotateClockwise() {
        currentPiece.rotateClockwise();
        if (isCollision()) {
            currentPiece.rotateCounterClockwise();
        }
    }

    public void rotateCounterClockwise() {
        currentPiece.rotateCounterClockwise();
        if (isCollision()) {
            currentPiece.rotateClockwise();
        }
    }

    public Tetromino generateRandomPiece() {
        TetrominoType[] types = TetrominoType.values();
        TetrominoType randomType = types[new Random().nextInt(types.length)];
        Tetromino currentTetromino = new Tetromino(randomType);
        currentTetromino.setX(COL_COUNT / 2 - 2);
        currentTetromino.setY(0);
        return currentTetromino;
    }

    public void afterClearRow(List<int[]> filledRows) {
        for (int i = filledRows.size() - 1; i >= 0; i--) {
            int clearedRow = filledRows.get(i)[0];
            for (int j = clearedRow; j > 0; j--) {
                System.arraycopy(grid[j - 1], 0, grid[j], 0, COL_COUNT);
            }
            clearRow(0);
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public List<int[]> getFilledRows() {
        List<int[]> filledRows = new ArrayList<>();
        for (int i = 0; i < ROW_COUNT; i++) {
            boolean isFilled = true;
            for (int j = 0; j < COL_COUNT; j++) {
                if (grid[i][j] == 0) {
                    isFilled = false;
                    break;
                }
            }
            if (isFilled) {
                filledRows.add(new int[]{i});
            }
        }
        return filledRows;
    }

    public void clearRow(int row) {
        for (int j = 0; j < COL_COUNT; j++) {
            grid[row][j] = 0;
        }
    }

    public boolean isCollision() {
        boolean collision = currentPiece.isCollision(grid);
        if (collision) {
        }
        return collision;
    }

    public int[][] getGrid() {
        return grid;
    }

    public Tetromino getCurrentPiece() {
        return currentPiece;
    }

    public int getNumFilledRows() {
        return numFilledRows;
    }
    
    public void setNullNumFilledRows() {
        numFilledRows = 0;
    }

}
