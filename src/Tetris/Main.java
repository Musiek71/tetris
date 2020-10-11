package Tetris;

import javax.swing.*;

import static Tetris.Game.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(BOARD_WIDTH * BLOCK_SIZE , BOARD_HEIGHT * BLOCK_SIZE + BLOCK_SIZE);
        frame.setVisible(true);

        GameBoard gameBoard = new GameBoard();
        frame.addKeyListener(gameBoard);
        frame.add(gameBoard);

        gameBoard.init();

        Game game = new Game(gameBoard);
        gameBoard.setGame(game);
        game.start();

    }

}
