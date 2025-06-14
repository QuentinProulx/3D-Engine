package org.example;

import Entities.Point;

import javax.swing.*;

public class Game {
    public static Point camera = new Point(0, -4, 0, 0, 0, 0);

    public static GamePanel gamePanel = new GamePanel();
    public static KeyHandler keyHandler = new KeyHandler();

    public static JFrame frame = new JFrame();

    /**
     * Starts the Game
     */
    public static void start() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("3D Fucking Engine");
        frame.setSize(516, 539);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.add(gamePanel);

        gamePanel.startPanel();
        frame.setVisible(true);
    }
}
