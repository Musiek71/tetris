package Tetris;

import javax.swing.*;
import java.awt.*;

import static Tetris.Main.*;

public class ScoreBoard extends JPanel {


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillRect(0,
                0,
                100,
                100);
    }
}
