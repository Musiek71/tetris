package Tetris;

import java.awt.*;

public class TTetromino extends Tetromino{
    public TTetromino() {
        super(0,
                Color.MAGENTA,
                0,
                new Point[][] {
                        {
                                new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1,2)
                        },
                        {
                                new Point(0, 1), new Point(1, 1), new Point(1, 0), new Point(1,2)
                        },
                        {
                                new Point(0, 1), new Point(1, 1), new Point(1, 0), new Point(2,1)
                        },
                        {
                                new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2,1)
                        },
                }
        );
    }
}