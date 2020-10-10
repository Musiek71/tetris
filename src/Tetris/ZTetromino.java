package Tetris;

import java.awt.*;

public class ZTetromino extends Tetromino{
    public ZTetromino() {
        super(0,
                Color.RED,
                0,
                new Point[][] {
                        {
                                new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2,1)
                        },
                        {
                                new Point(2, 0), new Point(2, 1), new Point(1, 1), new Point(1,2)
                        },
                        {
                                new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2,1)
                        },
                        {
                                new Point(2, 0), new Point(2, 1), new Point(1, 1), new Point(1,2)
                        },
                }
        );
    }
}