package Tetris;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import static Tetris.Main.*;

public class ScoreBoard extends JPanel {

    JLabel scoreLabel;
    JLabel rowsLabel;
    JLabel levelLabel;
    Image logo;


    ScoreBoard() {
        super();
        GridLayout layout = new GridLayout(4, 1);

        this.setLayout(layout);

        try {
            logo = ImageIO.read(new File("logo.png"));

            JLabel titlePanel = new JLabel(new ImageIcon(logo));
            this.add(titlePanel);

//        titlePanel.setFont(new Font("Verdana", Font.BOLD, 24));
//        titlePanel.setForeground(Color.BLACK);
            //titlePanel.setVerticalAlignment(JLabel.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        levelLabel = new JLabel("Level:" + 0);
        levelLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        this.add(levelLabel);

        rowsLabel = new JLabel("Rows:" + 0);
        rowsLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        this.add(rowsLabel);

        scoreLabel = new JLabel("Score:" + 0);
        scoreLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        this.add(scoreLabel);



    }

    void setScore(int score) {
        scoreLabel.setText("Score:" + score);
    }

    void setLevel(int level) {
        levelLabel.setText("Level:" + level);
    }

    void setRows(int rows) {
        rowsLabel.setText("Rows:" + rows);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        g.setColor(Color.LIGHT_GRAY);
//        g.fillRect(GAMEBOARD_WIDTH * BLOCK_SIZE,
//                0,
//                SCOREBOARD_WIDTH * BLOCK_SIZE,
//                SCOREBOARD_HEIGHT * BLOCK_SIZE);
    }
}
