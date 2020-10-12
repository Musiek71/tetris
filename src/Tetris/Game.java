package Tetris;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import static Tetris.Main.*;

public class Game extends JFrame implements KeyListener {


    final static Point defaultSpawn = new Point(4,0);

    private GameBoard gameBoard;
    private ScoreBoard scoreBoard;

    private int score = 0;
    private int totalRows = 0;
    private int level = 0;

    public Game(String s) throws HeadlessException {
        super(s);
    }

    public void init() {
        gameBoard = new GameBoard();
        scoreBoard = new ScoreBoard();
        gameBoard.setSize(GAMEBOARD_WIDTH * BLOCK_SIZE, GAMEBOARD_HEIGHT * BLOCK_SIZE);
        scoreBoard.setSize(SCOREBOARD_WIDTH * BLOCK_SIZE, SCOREBOARD_HEIGHT * BLOCK_SIZE);
        gameBoard.setBackground(Color.LIGHT_GRAY);
        scoreBoard.setBackground(Color.LIGHT_GRAY);

        this.getContentPane().add(gameBoard, BorderLayout.CENTER);
        this.getContentPane().add(scoreBoard, BorderLayout.EAST);

        scoreBoard.repaint();
        gameBoard.repaint();

    }



    public void start() {
        gameBoard.init();

        gameBoard.currentTetromino = gameBoard.factory.createRandomTetromino();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Long time1 = System.nanoTime();
                        fallDown();
                        Long time2 = System.nanoTime();
                        Long delta = (time2 - time1) / 1000000;
                        Thread.sleep(500 - delta - level * 50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    private void newTetromino() {
        gameBoard.currentTetromino = gameBoard.factory.createRandomTetromino();
        gameBoard.currentPosition = new Point(defaultSpawn.x, defaultSpawn.y);
    }

    public void fallDown() {
        if (!collidesWith(gameBoard.currentPosition.x, gameBoard.currentPosition.y + 1, gameBoard.currentTetromino.getCurrentShape())) {
            gameBoard.currentPosition.y++;
        } else {
            addToBoard();
            clearRows();
            updateLevel();
            newTetromino();
        }
        scoreBoard.setScore(score);
        scoreBoard.setLevel(level);
        scoreBoard.setRows(totalRows);
        gameBoard.repaint();
    }

    public void addToBoard() {
        for (Point singlePoint : gameBoard.currentTetromino.getCurrentShape()) {
            gameBoard.boardMap[gameBoard.currentPosition.x + singlePoint.x][gameBoard.currentPosition.y + singlePoint.y] = gameBoard.currentTetromino.getColor();
        }
    }


    public void pushTopDown(int row) {
        for (int y = row; y > 0; y--) {
            for (int x = 1; x < GAMEBOARD_WIDTH - 1; x++) {
                gameBoard.boardMap[x][y] = gameBoard.boardMap[x][y-1];
            }
        }
    }


    private void updateLevel() {
        level = totalRows / 10;
    }

    private boolean collidesWith(int x, int y, Point[] shape) {
        for (Point singlePoint : shape) {
            //check whether every point is empty
            if (gameBoard.boardMap[x + singlePoint.x][y + singlePoint.y] != Color.BLACK) {
                return true;
            }
        }

        return false;
    }

    private void clearRows() {
        boolean foundFullRow;
        int lineCounter = 0;

        //find all full rows
        for(int y = GAMEBOARD_HEIGHT - 2; y > 0; y--) {
            foundFullRow = true;
            for (int x = 1; x < GAMEBOARD_WIDTH - 1; x++) {
                if (gameBoard.boardMap[x][y] == Color.BLACK) {
                    foundFullRow = false;
                }
            }
            if (foundFullRow) {
                pushTopDown(y);
                lineCounter++;
                totalRows++;
                y++;
            }
        }

        //update score
        switch (lineCounter) {
            case 1:
                score += 40 * (level + 1);
                break;
            case 2:
                score += 100 * (level + 1);
                break;
            case 3:
                score += 300 * (level + 1);
                break;
            case 4:
                score += 1200 * (level + 1);
                break;
        }

        lineCounter = 0;

    }

    public void rotateLeft() {
        if (!collidesWith(gameBoard.currentPosition.x, gameBoard.currentPosition.y, gameBoard.currentTetromino.getLeftRotationShape())) {
            gameBoard.currentTetromino.rotateLeft();
        }
        gameBoard.repaint();
    }

    public void rotateRight() {
        if (!collidesWith(gameBoard.currentPosition.x, gameBoard.currentPosition.y, gameBoard.currentTetromino.getRightRotationShape())) {
            gameBoard.currentTetromino.rotateRight();
        }
        gameBoard.repaint();
    }

    public void moveLeft() {
        if (!collidesWith(gameBoard.currentPosition.x - 1, gameBoard.currentPosition.y, gameBoard.currentTetromino.getCurrentShape())) {
            gameBoard.currentPosition.x--;
        }
        gameBoard.repaint();
    }

    public void moveRight() {
        if (!collidesWith(gameBoard.currentPosition.x + 1, gameBoard.currentPosition.y, gameBoard.currentTetromino.getCurrentShape())) {
            gameBoard.currentPosition.x++;
        }
        gameBoard.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                rotateLeft();
                break;
            case KeyEvent.VK_DOWN:
                rotateRight();
                break;
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            case KeyEvent.VK_S:
                gameBoard.currentTetromino = gameBoard.factory.createRandomTetromino();
                break;
            case KeyEvent.VK_SPACE:
                fallDown();
                score++;
                break;
        }
        gameBoard.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
