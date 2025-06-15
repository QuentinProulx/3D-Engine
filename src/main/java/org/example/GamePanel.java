package org.example;

import ExtraMath.Matrix;
import GraphicsHandlers.GraphicsHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public static Thread gameThread;

    public static final int FPS = 60;

    /**
     * Starts the GamePanel
     */
    public void startPanel() {
        gameThread = new Thread(this);
        gameThread.start();

        this.addKeyListener(Game.keyHandler);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.requestFocusInWindow();

        start();
    }

    @Override
    public void run() {
        while (gameThread.isAlive()) {
            long waitingTime = System.currentTimeMillis() + 1000 / FPS;

            update();
            repaint();

            try {
                if (waitingTime > System.currentTimeMillis() + 5) {
                    Thread.sleep(waitingTime - System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Updates everything and makes all the values change
     */
    private void update() {
        double speed = 0.02;

        if (Game.keyHandler.wPressed) {
            Game.camera.move(0, speed, 0);
        }
        if (Game.keyHandler.sPressed) {
            Game.camera.move(0, -speed, 0);
        }
        if (Game.keyHandler.aPressed) {
            Game.camera.move(-speed, 0, 0);
        }
        if (Game.keyHandler.dPressed) {
            Game.camera.move(speed, 0, 0);
        }

        if (Game.keyHandler.iPressed) {
            Game.camera.rotateX(1, Game.camera.position);
        }
        if (Game.keyHandler.jPressed) {
            Game.camera.rotateZ(-1.2, Game.camera.position);
        }
        if (Game.keyHandler.kPressed) {
            Game.camera.rotateX(-1, Game.camera.position);
        }
        if (Game.keyHandler.lPressed) {
            Game.camera.rotateZ(1.3, Game.camera.position);
        }

        Matrix origin = new Matrix(new double[][]{{2}, {0}, {0}});

        double angle = 1;

        Main.point1.position = Main.point1.position.rotateX3(angle, origin);
        Main.point2.position = Main.point2.position.rotateX3(angle, origin);
        Main.point3.position = Main.point3.position.rotateX3(angle, origin);
        Main.point4.position = Main.point4.position.rotateX3(angle, origin);
        Main.point5.position = Main.point5.position.rotateX3(angle, origin);
        Main.point6.position = Main.point6.position.rotateX3(angle, origin);
        Main.point7.position = Main.point7.position.rotateX3(angle, origin);
        Main.point8.position = Main.point8.position.rotateX3(angle, origin);
//
//        Main.point1.position = Main.point1.position.rotateY3(1, origin);
//        Main.point2.position = Main.point2.position.rotateY3(1, origin);
//        Main.point3.position = Main.point3.position.rotateY3(1, origin);
//        Main.point4.position = Main.point4.position.rotateY3(1, origin);
//        Main.point5.position = Main.point5.position.rotateY3(1, origin);
//        Main.point6.position = Main.point6.position.rotateY3(1, origin);
//        Main.point7.position = Main.point7.position.rotateY3(1, origin);
//        Main.point8.position = Main.point8.position.rotateY3(1, origin);
    }

    /**
     * Run once when the Game starts
     */
    private void start() {

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        GraphicsHandler.renderPoints(g2d);
        GraphicsHandler.renderLines(g2d);
    }
}
