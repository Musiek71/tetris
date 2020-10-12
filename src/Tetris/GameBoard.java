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

    public Point currentPosition = new Point(defaultSpawn.x, defaultSpawn.y);
    public int[][] boardMap = new int[GAMEBOARD_WIDTH][GAMEBOARD_HEIGHT];
    public TetrominoFactory factory = new TetrominoFactory();
    public Tetromino currentTetromino;


    void init() {
        GridLayout gameBoardLayout = new GridLayout(1, 1);
        this.setLayout(gameBoardLayout);

        //System.out.println(System.getProperty("user.dir"));

        initTextures();

        //set boardMap's borders to GREY
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
            wallTexture = ImageIO.read(new File("cobble.png"));
            iBlockTexture = ImageIO.read(new File("itext.png"));
            jBlockTexture = ImageIO.read(new File("jtext.png"));
            lBlockTexture = ImageIO.read(new File("ltext.png"));
            oBlockTexture = ImageIO.read(new File("otext.png"));
            sBlockTexture = ImageIO.read(new File("stext.png"));
            tBlockTexture = ImageIO.read(new File("ttext.png"));
            zBlockTexture = ImageIO.read(new File("ztext.png"));
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
                }
//                if (boardMap[x][y] == 0) {
//                    g.setColor(Color.BLACK);
//                    g.fillRect(x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
//                } else if (boardMap[x][y] == 1) {
//                    g.drawImage(wallTexture, x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, this);
//                } else {
//                    //draw texture
//                    g.drawImage(blockTexture, x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, this);
//                    //outline
//                    g.setColor(Color.DARK_GRAY);
//                    g.drawRect(x * BLOCK_SIZE, (y - 3) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
//                }
            }
        }

        drawTetromino(g);
    }



}
