package Tetris;

import java.awt.*;

public class JTetromino extends Tetromino{
    public JTetromino() {
        super(0,
                Color.BLUE,
                0,
                new Point[][] {
                        {
                                new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0,2)
                        },
                        {
                                new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(2,1)
                        },
                        {
                                new Point(1, 0), new Point(2, 0), new Point(1, 1), new Point(1,2)
                        },
                        {
                                new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2,2)
                        },
                }
        );
    }
}