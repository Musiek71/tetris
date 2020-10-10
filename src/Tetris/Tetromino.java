package Tetris;

import java.awt.*;

public abstract class Tetromino {
    private int rotation;
    private Color color;
    private Point[] currentShape;
    private Point[][] shapes;

    public Tetromino(int rotation, Color color, int shape, Point[][] shapes) {
        this.rotation = rotation;
        this.color = color;
        this.currentShape = shapes[shape];
        this.shapes = shapes;
    }

    public Color getColor() {
        return color;
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
