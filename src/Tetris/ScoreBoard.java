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
    JLabel gameOverLabel;
    JLabel pressRLabel;
    Image logo;


    ScoreBoard() {
        super();
        GridLayout layout = new GridLayout(6, 1);

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
        levelLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        levelLabel.setForeground(Color.WHITE);
        this.add(levelLabel);

        rowsLabel = new JLabel("Rows:" + 0);
        rowsLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        rowsLabel.setForeground(Color.WHITE);
        this.add(rowsLabel);

        scoreLabel = new JLabel("Score:" + 0);
        scoreLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        scoreLabel.setForeground(Color.WHITE);
        this.add(scoreLabel);

        gameOverLabel = new JLabel("GAME OVER!");
        gameOverLabel.setFont(new Font("Verdana", Font.BOLD, 48));
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setVisible(false);
        this.add(gameOverLabel);

        pressRLabel = new JLabel("Press R to play again!");
        pressRLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        pressRLabel.setForeground(Color.WHITE);
        pressRLabel.setVisible(false);
        this.add(pressRLabel);

    }

    public void setGameOverLabelVisible(boolean flag) {
        gameOverLabel.setVisible(flag);
        pressRLabel.setVisible(flag);
    }

    public void setScore(int score) {
        scoreLabel.setText("Score:" + score);
    }

    public void setLevel(int level) {
        levelLabel.setText("Level:" + level);
    }

    public void setRows(int rows) {
        rowsLabel.setText("Rows:" + rows);
    }

}
