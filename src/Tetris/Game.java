package Tetris;

import javax.swing.*;

public class Game {

    public final static int BOARD_WIDTH = 12; //playing board width 10 + 2 walls
    public final static int BOARD_HEIGHT = 25; //playing board height 20 + 4 invisible top rows + 1 bottom
    public final static int BLOCK_SIZE = 30;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(BOARD_WIDTH * BLOCK_SIZE, BOARD_HEIGHT * (BLOCK_SIZE + 1));
        frame.setVisible(true);

        Board board = new Board();
        frame.addKeyListener(board);
        frame.add(board);

        board.init();

        board.loop();
    }
}


//  Tetris.TetrominoFactory factory = new Tetris.TetrominoFactory();
//        for (int i = 0; i < 5; i++) {
//            char[][] map = new char[4][4];
//
//            Tetris.Tetromino tetromino = factory.createRandomTetromino();
//
//            System.out.println("----");
//
//            for (int z = 0; z < 4; z++) {
//
//                for (int j = 0; j < 4; j++) {
//                    for (int k = 0; k < 4; k++) {
//                        map[j][k] = ' ';
//                    }
//                }
//
//                for (Point singlePoint : tetromino.getCurrentShape()) {
//                    map[singlePoint.x][singlePoint.y] = 'X';
//                }
//
//                for (int j = 0; j < 4; j++) {
//                    for (int k = 0; k < 4; k++) {
//                        System.out.print(map[j][k]);
//                    }
//                    System.out.println();
//                }
//
//                tetromino.rotateRight();
//            }
//
//        }