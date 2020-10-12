package Tetris;

import javax.swing.*;

import java.awt.*;

public class Main {

    public final static int GAMEBOARD_WIDTH = 12; //playing board width 10 + 2 walls
    public final static int GAMEBOARD_HEIGHT = 24; //playing board height 20 + 4 invisible top rows + 1 bottom
    public final static int SCOREBOARD_WIDTH = 4;
    public final static int SCOREBOARD_HEIGHT = 21;
    public final static int BLOCK_SIZE = 32;

    public static void main(String[] args) {
        Game game = new Game("Tetris");
        game.setVisible(true);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //game.setSize(  GAMEBOARD_WIDTH * BLOCK_SIZE + SCOREBOARD_WIDTH * BLOCK_SIZE,
         //       (GAMEBOARD_HEIGHT - 1) * BLOCK_SIZE );
        //game.setMinimumSize(new Dimension((GAMEBOARD_WIDTH + SCOREBOARD_WIDTH) * BLOCK_SIZE, (GAMEBOARD_HEIGHT) * BLOCK_SIZE));
        //game.setExtendedState(JFrame.MAXIMIZED_BOTH);
        game.setPreferredSize(new Dimension(GAMEBOARD_WIDTH * BLOCK_SIZE + SCOREBOARD_WIDTH * BLOCK_SIZE,
                (GAMEBOARD_HEIGHT - 2) * BLOCK_SIZE ));
        game.setResizable(false);

        game.addKeyListener(game);

        game.init();
        game.start();

        game.pack();

    }

}
