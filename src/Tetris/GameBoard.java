package Tetris;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import static Tetris.Main.*;
import static Tetris.Game.*;

public class GameBoard extends JPanel {

    Image wallTexture;
    Image iBlockTexture;
    Image jBlockTexture;
    Image lBlockTexture;
    Image oBlockTexture;
    Image sBlockTexture;
    Image tBlockTexture;
    Image zBlockTexture;

    boolean gameOver = false;
    boolean clearingRows = false;

    public Point currentPosition = new Point(defaultSpawn.x, defaultSpawn.y);
    public int[][] boardMap = new int[GAMEBOARD_WIDTH][GAMEBOARD_HEIGHT];
    public TetrominoFactory factory = new TetrominoFactory();
    public Tetromino currentTetromino;

    public Point currentGhostPosition = new Point(defaultSpawn.x, defaultSpawn.y);
    public Point[] ghostTetrominoShape;


    void init() {
        GridLayout gameBoardLayout = new GridLayout(1, 1);
        this.setLayout(gameBoardLayout);

        initTextures();

        restartBoardMap();

    }

    void restartBoardMap() {
        //set boardMap's borders to WALL Texture
        for (int x = 0; x < GAMEBOARD_WIDTH; x++) {
            for (int y = 0; y < GAMEBOARD_HEIGHT; y++) {
                if (x == 0 || x == GAMEBOARD_WIDTH - 1 || y == GAMEBOARD_HEIGHT - 1) {
                    boardMap[x][y] = 1;
                } else {
                    boardMap[x][y] = 0;
                }
            }
        }
    }

    void initTextures() {
        try {
            System.out.println(System.getProperty("user.dir"));
            wallTexture = ImageIO.read(getClass().getClassLoader().getResource("wall.png"));
            iBlockTexture = ImageIO.read(getClass().getClassLoader().getResource("itext.png"));
            jBlockTexture = ImageIO.read(getClass().getClassLoader().getResource("jtext.png"));
            lBlockTexture = ImageIO.read(getClass().getClassLoader().getResource("ltext.png"));
            oBlockTexture = ImageIO.read(getClass().getClassLoader().getResource("otext.png"));
            sBlockTexture = ImageIO.read(getClass().getClassLoader().getResource("stext.png"));
            tBlockTexture = ImageIO.read(getClass().getClassLoader().getResource("ttext.png"));
            zBlockTexture = ImageIO.read(getClass().getClassLoader().getResource("ztext.png"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void drawTetromino(Graphics g) {
        for (Point singlePoint : currentTetromino.getCurrentShape()) {
            switch (currentTetromino.getColorInt()) {
                case 2:
                    g.drawImage(iBlockTexture,(currentPosition.x + singlePoint.x) * BLOCK_SIZE,
                            (currentPosition.y + singlePoint.y - 3) * BLOCK_SIZE, this);
                    break;
                case 3:
                    g.drawImage(jBlockTexture,(currentPosition.x + singlePoint.x) * BLOCK_SIZE,
                            (currentPosition.y + singlePoint.y - 3) * BLOCK_SIZE, this);
                    break;
                case 4:
                    g.drawImage(lBlockTexture,(currentPosition.x + singlePoint.x) * BLOCK_SIZE,
                            (currentPosition.y + singlePoint.y - 3) * BLOCK_SIZE, this);
                    break;
                case 5:
                    g.drawImage(oBlockTexture,(currentPosition.x + singlePoint.x) * BLOCK_SIZE,
                            (currentPosition.y + singlePoint.y - 3) * BLOCK_SIZE, this);
                    break;
                case 6:
                    g.drawImage(sBlockTexture,(currentPosition.x + singlePoint.x) * BLOCK_SIZE,
                            (currentPosition.y + singlePoint.y - 3) * BLOCK_SIZE, this);
                    break;
                case 7:
                    g.drawImage(zBlockTexture,(currentPosition.x + singlePoint.x) * BLOCK_SIZE,
                            (currentPosition.y + singlePoint.y - 3) * BLOCK_SIZE, this);
                    break;
                case 8:
                    g.drawImage(tBlockTexture,(currentPosition.x + singlePoint.x) * BLOCK_SIZE,
                            (currentPosition.y + singlePoint.y - 3) * BLOCK_SIZE, this);
                    break;
            }

            //draw outline
//            g.setColor(Color.DARK_GRAY);
//            g.drawRect( (currentPosition.x + singlePoint.x) * BLOCK_SIZE,
//                    (currentPosition.y + singlePoint.y - 3) * BLOCK_SIZE,
//                    BLOCK_SIZE ,
//                    BLOCK_SIZE
//            );

        }
    }

    private void drawGhost(Graphics g) {
        g.setColor(Color.WHITE);
        for (Point singlePoint : ghostTetrominoShape) {
            g.drawRect((currentGhostPosition.x + singlePoint.x) * BLOCK_SIZE,
                    (currentGhostPosition.y + singlePoint.y - 3) * BLOCK_SIZE,
                    BLOCK_SIZE,
                    BLOCK_SIZE );
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < GAMEBOARD_WIDTH; x++) {
            for (int y = 3; y < GAMEBOARD_HEIGHT; y++) {
                switch(boardMap[x][y]) {
                    case 0:
                        g.setColor(Color.BLACK);
                        g.fillRect(x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                        break;
                    case 1:
                        g.drawImage(wallTexture, x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, this);
                        break;
                    case 2:
                        g.drawImage(iBlockTexture,x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, this);
                        break;
                    case 3:
                        g.drawImage(jBlockTexture,x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, this);
                        break;
                    case 4:
                        g.drawImage(lBlockTexture,x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, this);
                        break;
                    case 5:
                        g.drawImage(oBlockTexture,x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, this);
                        break;
                    case 6:
                        g.drawImage(sBlockTexture,x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, this);
                        break;
                    case 7:
                        g.drawImage(zBlockTexture,x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, this);
                        break;
                    case 8:
                        g.drawImage(tBlockTexture,x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, this);
                        break;
                    case 9:
                        //blink white
                        g.setColor(Color.WHITE);
                        g.fillRect(x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                        break;
                    case 10:
                        //blink gray
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillRect(x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                        break;
                }
            }
        }

        drawTetromino(g);

        if (!clearingRows) {
            drawGhost(g);
        }

    }



}
