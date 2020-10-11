package Tetris;

import javax.swing.*;

public class Game {

    public final static int BOARD_WIDTH = 12; //playing board width 10 + 2 walls
    public final static int BOARD_HEIGHT = 25; //playing board height 20 + 4 invisible top rows + 1 bottom
    public final static int BLOCK_SIZE = 32;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(BOARD_WIDTH * BLOCK_SIZE + 4 * BLOCK_SIZE, BOARD_HEIGHT * BLOCK_SIZE + BLOCK_SIZE);
        frame.setVisible(true);

        Board board = new Board();
        frame.addKeyListener(board);
        frame.add(board);

        board.init();
    }
}
