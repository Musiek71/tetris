package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Tetris.Game.*;

public class GameBoard extends JPanel implements KeyListener {


    public Point currentPosition = new Point(defaultSpawn.x, defaultSpawn.y);
    public Color[][] boardMap = new Color[BOARD_WIDTH][BOARD_HEIGHT];
    public TetrominoFactory factory = new TetrominoFactory();
    public Tetromino currentTetromino;
    private Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    void init() {
        //set boardMap's borders to GREY
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                if (x == 0 || x == BOARD_WIDTH - 1 || y == BOARD_HEIGHT - 1) {
                    boardMap[x][y] = Color.GRAY;
                } else {
                    boardMap[x][y] = Color.BLACK;
                }
            }
        }

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
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                g.setColor(boardMap[x][y]);
                g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                if (boardMap[x][y] != Color.BLACK) {
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }

        drawTetromino(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                game.rotateLeft();
                break;
            case KeyEvent.VK_DOWN:
                game.rotateRight();
                break;
            case KeyEvent.VK_LEFT:
                game.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                game.moveRight();
                break;
            case KeyEvent.VK_S:
                currentTetromino = factory.createRandomTetromino();
                break;
            case KeyEvent.VK_SPACE:
                game.fallDown();
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
