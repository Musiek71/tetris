package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;

import static Tetris.Game.BOARD_HEIGHT;
import static Tetris.Game.BOARD_WIDTH;
import static Tetris.Game.BLOCK_SIZE;

public class Board extends JPanel implements KeyListener {

    final static Point defaultSpawn = new Point(4,0);

    TetrominoFactory factory = new TetrominoFactory();
    Tetromino currentTetromino;

    private int score = 0;

    int totalRows = 0;
    int level = 0;
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

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Long time1 = System.nanoTime();
                        fallDown();
                        Long time2 = System.nanoTime();
                        Long delta = (time2 - time1 ) / 1000000;
                        Thread.sleep(500 - delta - level * 50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

//    void loop() {
//        while (true) {
//            try {
//                Thread.sleep(300);
//                if (!collidesWith(currentPosition.x, currentPosition.y + 1, currentTetromino.getCurrentShape())) {
//                    fallDown();
//                } else {
//                    addToBoard();
//                    newTetromino();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

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
        if (!collidesWith(currentPosition.x, currentPosition.y + 1, currentTetromino.getCurrentShape())) {
            currentPosition.y++;
        } else {
            addToBoard();
            clearRows();
            updateLevel();
            newTetromino();
        }
        repaint();
    }

    private void updateLevel() {
        level = totalRows / 10;
    }

    private void clearRows() {
        boolean foundFullRow;
        ArrayList<Integer> fullRows = new ArrayList<Integer>();
        int lineCounter = 0;

        //find all full rows
        for(int y = BOARD_HEIGHT - 2; y > 0; y--) {
            foundFullRow = true;
            for (int x = 1; x < BOARD_WIDTH - 1; x++) {
                if (board[x][y] == Color.BLACK) {
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


        //remove all full rows
        //for (int i = 0; i < fullRows.size(); i++) {
        //    pushTopDown(fullRows.get(i));
        //}


    }



    private void pushTopDown(int row) {
        for (int y = row; y > 0; y--) {
            for (int x = 1; x < BOARD_WIDTH - 1; x++) {
                board[x][y] = board[x][y-1];
            }
        }
    }

    private boolean collidesWith(int x, int y, Point[] shape) {
        for (Point singlePoint : shape) {
            //check whether every point is empty
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
            g.setColor(currentTetromino.getColor());
            g.fillRect( (currentPosition.x + singlePoint.x) * BLOCK_SIZE,
                        (currentPosition.y + singlePoint.y) * BLOCK_SIZE,
                            BLOCK_SIZE ,
                            BLOCK_SIZE
            );
            g.setColor(Color.DARK_GRAY);
            g.drawRect( (currentPosition.x + singlePoint.x) * BLOCK_SIZE,
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
        //draw background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, BOARD_WIDTH * BLOCK_SIZE + 4 * BLOCK_SIZE, BOARD_HEIGHT * BLOCK_SIZE + BLOCK_SIZE );

        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                g.setColor(board[x][y]);
                g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                if (board[x][y] != Color.BLACK) {
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
        //draw grid
//        g.setColor(Color.GRAY);
//        for (int x = 0; x < BOARD_WIDTH; x++) {
//            for (int y = 0; y < BOARD_HEIGHT; y++) {
//                g.drawRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
//            }
//        }

        //print score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospaced", Font.BOLD, 24));
        g.drawString("Score:", BOARD_WIDTH * BLOCK_SIZE, BLOCK_SIZE);
        g.drawString("" + score, BOARD_WIDTH * BLOCK_SIZE, 2 * BLOCK_SIZE);

        //print level
        g.drawString("Level:", BOARD_WIDTH * BLOCK_SIZE, 3 * BLOCK_SIZE);
        g.drawString("" + level, BOARD_WIDTH * BLOCK_SIZE, 4 * BLOCK_SIZE);

        //print cleared rows
        g.drawString("Cleared:", BOARD_WIDTH * BLOCK_SIZE, 5 * BLOCK_SIZE);
        g.drawString("" + totalRows, BOARD_WIDTH * BLOCK_SIZE, 6 * BLOCK_SIZE);


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
            case KeyEvent.VK_S:
                fallDown();
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
