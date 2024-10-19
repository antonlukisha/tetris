package com.mycompany.tetrisnew.model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public final class Tetromino {
    private final TetrominoType type;
    private int[][] shape;
    private final Color color;
    private ArrayList<Point> blocks;
    private int x;
    private int y;

    public Tetromino(TetrominoType type) {
        this.type = type;
        this.shape = type.getShape();
        this.color = type.getColor();
        this.x = 0;
        this.y = -6;
        this.blocks = new ArrayList<>(4);
        blocks.clear();
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (this.shape[i][j] == 1) {
                    blocks.add(new Point(x + i, y + j));
                }
            }
        }
    }

    public void moveLeft() {
        for (Point block : getBlocks()) {
            block.x--;
        }
        x--;
    }

    public void moveRight() {
        for (Point block : getBlocks()) {
            block.x++;
        }
        x++;
    }

    public void moveDown() {
        for (Point block : getBlocks()) {
            block.y++;
        }
        y++;
    }
    
    void moveUp() {
        for (Point block : getBlocks()) {
            block.y--;
        }
        y--;
    }

    public void rotateClockwise() {
        int[][] newShape = new int[getHeight()][getWidth()];
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                newShape[j][getWidth() - 1 - i] = shape[i][j];
            }
        }
        shape = newShape;
        updateBlocks();
    }

    public void rotateCounterClockwise() {
        int[][] newShape = new int[getHeight()][getWidth()];
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                newShape[getHeight() - 1 - j][i] = shape[i][j];
            }
        }
        shape = newShape;
        updateBlocks();
    }

    public TetrominoType getType() {
        return type;
    }

    public int[][] getShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public void setX(int xPoint) {
        blocks.clear();
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (this.shape[i][j] == 1) {
                    blocks.add(new Point(xPoint + i, y + j));
                }
            }
        }
        this.x = xPoint;
    }

    public int getY() {
        return y;
    }
    
    public Color getColor() {
        return color;
    }

    public void setY(int yPoint) {
        blocks.clear();
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (this.shape[i][j] == 1) {
                    blocks.add(new Point(x + i, yPoint + j));
                }
            }
        }
        this.y = yPoint;
    }

    public ArrayList<Point> getBlocks() {
        return blocks;
    }

    public boolean isFalling() {
        for (Point block : getBlocks()) {
            if (block.y >= TetrisBoard.ROW_COUNT || !isValidPosition(block.x, block.y)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < TetrisBoard.COL_COUNT && y < TetrisBoard.ROW_COUNT;
    }
    
    public void fixOnBoard(int[][] grid) {
        for (Point block : getBlocks()) {
            grid[block.y][block.x] = getColorToInt();
        }
    }
    
    public boolean isCollision(int[][] grid) {
        for (Point block : getBlocks()) {
            try {
                if (!isValidPosition(block.x, block.y) || grid[block.y][block.x] != 0) {
                    return true;
                }
            } catch (java.lang.ArrayIndexOutOfBoundsException exception) {
                return block.y >= 0;
            }
        }
        return false;
    }
    
    public int getHeight() {
        return shape[0].length;
    }

    public int getWidth() {
        return shape.length;
    }

    public int getColorToInt() {
        if (color == Color.CYAN) {
            return 2;
        } else if (color == Color.BLUE) {
            return 3;
        } else if (color == Color.ORANGE) {
            return 4;
        } else if (color == Color.YELLOW) {
            return 5;
        } else if (color == Color.GREEN) {
            return 6;
        } else if (color == Color.MAGENTA) {
            return 7;
        } else if (color == Color.RED) {
            return 8;
        }
        return 1;
    }
    
    private void updateBlocks() {
        blocks.clear();
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (shape[i][j] == 1) {
                    blocks.add(new Point(x + i, y + j));
                }
            }
        }
    }

    boolean isOutside() {
        for (Point block : getBlocks()) {
            if (block.y < 0) {
                return true;
            }
        }
        return false;
    }
}
 