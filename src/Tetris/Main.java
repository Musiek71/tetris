package Tetris;

import javax.swing.*;

import java.awt.*;

public class Main {

    //Gameboard sizes are scalable and the game works with most of the reasonable board sizes
    public final static int GAMEBOARD_WIDTH = 12; //playing board width 10 + 2 walls
    public final static int GAMEBOARD_HEIGHT = 24; //playing board height 20 + 3 invisible top rows + 1 bottom
    public final static int SCOREBOARD_WIDTH = GAMEBOARD_WIDTH; //scoreboard width same as the gameboard width, so Swing scales right
    public final static int SCOREBOARD_HEIGHT = GAMEBOARD_HEIGHT; //scoreboard height
    public final static int BLOCK_SIZE = 32;

    public static void main(String[] args) {
        Game game = new Game("Tetris");
        game.setVisible(true);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setPreferredSize(new Dimension(GAMEBOARD_WIDTH * BLOCK_SIZE + SCOREBOARD_WIDTH * BLOCK_SIZE,
                (GAMEBOARD_HEIGHT - 2) * BLOCK_SIZE ));
        game.setResizable(false);

        //Game class implements the key listener interface and is responsible for the event handling
        game.addKeyListener(game);

        //game init sets the JFrame layout and adds the gameboard and scoreboard panels
        game.init();
        game.start();

        //pack method is for some reason necessary for the window to scale properly
        game.pack();

    }

}
