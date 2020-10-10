package Tetris;

import java.awt.*;

public class STetromino extends Tetromino{
    public STetromino() {
        super(0,
                Color.GREEN,
                0,
                new Point[][] {
                        {
                                new Point(0, 1), new Point(1, 1), new Point(1, 0), new Point(2,0)
                        },
                        {
                                new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(2,2)
                        },
                        {
                                new Point(0, 1), new Point(1, 1), new Point(1, 0), new Point(2,0)
                        },
                        {
                                new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(2,2)
                        },
                }
        );
    }
}