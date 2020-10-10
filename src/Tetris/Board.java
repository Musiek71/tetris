package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Tetris.Game.BOARD_HEIGHT;
import static Tetris.Game.BOARD_WIDTH;
import static Tetris.Game.BLOCK_SIZE;

public class Board extends JPanel implements KeyListener {

    final static Point defaultSpawn = new Point(5,0);

    TetrominoFactory factory = new TetrominoFactory();
    Tetromino currentTetromino;

    int score = 0;
    Point currentPosition = new Point(defaultSpawn.x, defaultSpawn.y);
    Color[][] board = new Color[BOARD_WIDTH][BOARD_HEIGHT];

    void init() {
        //set board's borders to GREY
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                if (x == 0 || x == BOARD_WIDTH - 1 || y == BOARD_HEIGHT - 1) {
                    board[x][y] = Color.GRAY;
                } else {
                    board[x][y] = Color.BLACK;
                }
            }
        }

        currentTetromino = factory.createRandomTetromino();
    }

    void loop() {
        while (true) {
            try {
                Thread.sleep(300);
                if (!collidesWith(currentPosition.x, currentPosition.y + 1, currentTetromino.getCurrentShape())) {
                    fallDown();
                } else {
                    addToBoard();
                    newTetromino();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void newTetromino() {
        currentTetromino = factory.createRandomTetromino();
        currentPosition = new Point(defaultSpawn.x, defaultSpawn.y);
    }

    private void addToBoard() {
        for (Point singlePoint : currentTetromino.getCurrentShape()) {
            board[currentPosition.x + singlePoint.x][currentPosition.y + singlePoint.y] = currentTetromino.getColor();
        }
    }

    private void fallDown() {
        currentPosition.y++;
        repaint();
    }

    private boolean collidesWith(int x, int y, Point[] shape) {
        for (Point singlePoint : shape) {
            //check whether every place is empty
            if (board[x + singlePoint.x][y + singlePoint.y] != Color.BLACK) {
                return true;
            }
        }

        return false;
    }

    private void rotateLeft() {
        if (!collidesWith(currentPosition.x, currentPosition.y, currentTetromino.getLeftRotationShape())) {
            currentTetromino.rotateLeft();
        }
        repaint();
    }

    private void rotateRight() {
        if (!collidesWith(currentPosition.x, currentPosition.y, currentTetromino.getRightRotationShape())) {
            currentTetromino.rotateRight();
        }
        repaint();
    }

    private void moveLeft() {
        if (!collidesWith(currentPosition.x - 1, currentPosition.y, currentTetromino.getCurrentShape())) {
            currentPosition.x--;
        }
        repaint();
    }

    private void moveRight() {
        if (!collidesWith(currentPosition.x + 1, currentPosition.y, currentTetromino.getCurrentShape())) {
            currentPosition.x++;
        }
        repaint();
    }

    private void drawTetromino(Graphics g) {
        g.setColor(currentTetromino.getColor());
        for (Point singlePoint : currentTetromino.getCurrentShape()) {
            g.fillRect( (currentPosition.x + singlePoint.x) * BLOCK_SIZE,
                        (currentPosition.y + singlePoint.y) * BLOCK_SIZE,
                            BLOCK_SIZE ,
                            BLOCK_SIZE
            );
        }
        //draw grid
//        g.setColor(Color.GRAY);
//        for (Point singlePoint : currentTetromino.getCurrentShape()) {
//            g.drawRect( (currentPosition.x + singlePoint.x) * BLOCK_SIZE,
//                    (currentPosition.y + singlePoint.y) * BLOCK_SIZE,
//                    BLOCK_SIZE ,
//                    BLOCK_SIZE
//            );
//        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                g.setColor(board[x][y]);
                g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }
        }
        //draw grid
//        g.setColor(Color.GRAY);
//        for (int x = 0; x < BOARD_WIDTH; x++) {
//            for (int y = 0; y < BOARD_HEIGHT; y++) {
//                g.drawRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
//            }
//        }


        drawTetromino(g);
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
            case KeyEvent.VK_SPACE:
                currentTetromino = factory.createRandomTetromino();
                break;
            case KeyEvent.VK_E:
                fallDown();
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
