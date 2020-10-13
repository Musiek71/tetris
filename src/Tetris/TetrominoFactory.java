package Tetris;

import java.util.ArrayList;
import java.util.Collections;

public class TetrominoFactory {

    ArrayList<Tetromino> tetrominos;

    public TetrominoFactory() {
        this.tetrominos = getMixedTetrominosArray();
    }

    public Tetromino createRandomTetromino() {

        if (tetrominos.size() == 0) {
            tetrominos = getMixedTetrominosArray();
        }
        Tetromino next = tetrominos.get(0);
        tetrominos.remove(next);

        return next;
    }

    private ArrayList<Tetromino> getMixedTetrominosArray() {
        ArrayList<Tetromino> tetrominos = new ArrayList<>();

        tetrominos.add(new ITetromino());
        tetrominos.add(new OTetromino());
        tetrominos.add(new TTetromino());
        tetrominos.add(new JTetromino());
        tetrominos.add(new LTetromino());
        tetrominos.add(new STetromino());
        tetrominos.add(new ZTetromino());

        Collections.shuffle(tetrominos);

        return tetrominos;
    }
}
