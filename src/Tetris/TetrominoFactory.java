package Tetris;

import java.util.Random;

public class TetrominoFactory {

    public Tetromino createRandomTetromino() {
        Tetromino newTetromino = null; //fix this
        Random random = new Random();
        switch(random.nextInt(7)) {
            case 0:
                newTetromino = new ITetromino();
                break;
            case 1:
                newTetromino = new OTetromino();
                break;
            case 2:
                newTetromino = new TTetromino();
                break;
            case 3:
                newTetromino = new JTetromino();
                break;
            case 4:
                newTetromino = new LTetromino();
                break;
            case 5:
                newTetromino = new STetromino();
                break;
            case 6:
                newTetromino = new ZTetromino();
                break;
        }

        return newTetromino;
    }
}
