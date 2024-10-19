package com.mycompany.tetrisnew.model;

import java.awt.Color;

public enum TetrominoType {
    I_TETROMINO(new int[][] {
            { 1, 1, 1, 1 }
        },
        Color.CYAN),
    J_TETROMINO(new int[][] {
            { 1, 0, 0 },
            { 1, 1, 1 }
        },
        Color.BLUE),
    L_TETROMINO(new int[][] {
            { 0, 0, 1 },
            { 1, 1, 1 }
        },
        Color.ORANGE),
    O_TETROMINO(new int[][] {
            { 1, 1 },
            { 1, 1 }
        },
        Color.YELLOW),
    S_TETROMINO(new int[][] {
            { 0, 1, 1 },
            { 1, 1, 0 }
        },
        Color.GREEN),
    T_TETROMINO(new int[][] {
            { 0, 1, 0 },
            { 1, 1, 1 }
        },
        Color.MAGENTA),
    Z_TETROMINO(new int[][] {
            { 1, 1, 0 },
            { 0, 1, 1 }
        },
        Color.RED);

    private final int[][] shape;
    private Color color;

    private TetrominoType(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    public int[][] getShape() {
        return shape;
    }
    
    public Color getColor() {
        return color;
    }
}
