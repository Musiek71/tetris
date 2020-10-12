package Tetris;

import javax.swing.*;
import java.awt.*;

import static Tetris.Main.*;
import static Tetris.Game.*;

public class GameBoard extends JPanel {


    public Point currentPosition = new Point(defaultSpawn.x, defaultSpawn.y);
    public Color[][] boardMap = new Color[GAMEBOARD_WIDTH][GAMEBOARD_HEIGHT];
    public TetrominoFactory factory = new TetrominoFactory();
    public Tetromino currentTetromino;


    void init() {
        //set boardMap's borders to GREY
        for (int x = 0; x < GAMEBOARD_WIDTH; x++) {
            for (int y = 0; y < GAMEBOARD_HEIGHT; y++) {
                if (x == 0 || x == GAMEBOARD_WIDTH - 1 || y == GAMEBOARD_HEIGHT - 1) {
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
                        (currentPosition.y + singlePoint.y - 3) * BLOCK_SIZE,
                            BLOCK_SIZE ,
                            BLOCK_SIZE
            );
            g.setColor(Color.DARK_GRAY);
            g.drawRect( (currentPosition.x + singlePoint.x) * BLOCK_SIZE,
                    (currentPosition.y + singlePoint.y - 3) * BLOCK_SIZE,
                    BLOCK_SIZE ,
                    BLOCK_SIZE
            );
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < GAMEBOARD_WIDTH; x++) {
            for (int y = 3; y < GAMEBOARD_HEIGHT; y++) {
                g.setColor(boardMap[x][y]);
                g.fillRect(x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                if (boardMap[x][y] != Color.BLACK) {
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }

        drawTetromino(g);
    }



}
