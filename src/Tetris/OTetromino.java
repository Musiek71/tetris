package Tetris;

import java.awt.*;

public class OTetromino extends Tetromino{
    public OTetromino() {
        super(0,
                Color.YELLOW,
                0,
                new Point[][] {
                        {
                                new Point(1, 1), new Point(2, 1), new Point(1, 2), new Point(2,2)
                        },
                        {
                                new Point(1, 1), new Point(2, 1), new Point(1, 2), new Point(2,2)
                        },
                        {
                                new Point(1, 1), new Point(2, 1), new Point(1, 2), new Point(2,2)
                        },
                        {
                                new Point(1, 1), new Point(2, 1), new Point(1, 2), new Point(2,2)
                        },
                }
        );
    }
}