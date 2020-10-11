package Tetris;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game {

    public final static int BOARD_WIDTH = 12; //playing board width 10 + 2 walls
    public final static int BOARD_HEIGHT = 25; //playing board height 20 + 4 invisible top rows + 1 bottom
    public final static int BLOCK_SIZE = 32;

    final static Point defaultSpawn = new Point(4,0);

    private GameBoard gameBoard;

    private int score = 0;
    private int totalRows = 0;
    private int level = 0;

    public Game(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void start() {
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
                        Thread.sleep(500 - level * 50);
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
        gameBoard.repaint();
    }

    public void addToBoard() {
        for (Point singlePoint : gameBoard.currentTetromino.getCurrentShape()) {
            gameBoard.boardMap[gameBoard.currentPosition.x + singlePoint.x][gameBoard.currentPosition.y + singlePoint.y] = gameBoard.currentTetromino.getColor();
        }
    }


    public void pushTopDown(int row) {
        for (int y = row; y > 0; y--) {
            for (int x = 1; x < BOARD_WIDTH - 1; x++) {
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
        ArrayList<Integer> fullRows = new ArrayList<Integer>();
        int lineCounter = 0;

        //find all full rows
        for(int y = BOARD_HEIGHT - 2; y > 0; y--) {
            foundFullRow = true;
            for (int x = 1; x < BOARD_WIDTH - 1; x++) {
                if (gameBoard.boardMap[x][y] == Color.BLACK) {
                    foundFullRow = false;
                }
            }
            if (foundFullRow) {
                //fullRows.add(y);
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

    public void playClip(File file, int loop) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Clip clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        clip.open(ais);
        clip.loop(loop);

    }

}
