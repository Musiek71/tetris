package Tetris;

import java.awt.*;

public abstract class Tetromino {
    private int rotation;
    private int colorInt;
    private Point[] currentShape;
    private Point[][] shapes;

    public Tetromino(int rotation, int colorInt, int shape, Point[][] shapes) {
        this.rotation = rotation;
        this.colorInt = colorInt;
        this.currentShape = shapes[shape];
        this.shapes = shapes;
    }

    public int getColorInt() {
        return colorInt;
    }

    public Point[] getCurrentShape() {
        return currentShape;
    }

    public Point[] getLeftRotationShape() {
        int nextRotation = (rotation + 1) % 4;
        return shapes[nextRotation];
    }

    public Point[] getRightRotationShape() {
        int nextRotation = rotation - 1;
        if (nextRotation < 0) {
            nextRotation = 3;
        }
        return shapes[nextRotation];
    }

    private void setCurrentShape(int rotation) {
        this.currentShape = shapes[rotation];
    }

    public Tetromino rotateLeft() {
        int nextRotation = (rotation + 1) % 4;
        setCurrentShape(nextRotation);
        this.rotation = nextRotation;
        return this;
    }

    public Tetromino rotateRight() {
        int nextRotation = rotation - 1;
        if (nextRotation < 0) {
            nextRotation = 3;
        }
        setCurrentShape(nextRotation);
        this.rotation = nextRotation;
        return this;
    }
}
