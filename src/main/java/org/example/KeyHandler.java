package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean wPressed, aPressed, sPressed, dPressed;
    public boolean iPressed, jPressed, kPressed, lPressed;

    public KeyHandler() {
        wPressed = false;
        aPressed = false;
        sPressed = false;
        dPressed = false;

        iPressed = false;
        jPressed = false;
        kPressed = false;
        lPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            wPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            aPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            sPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            dPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_I) {
            iPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            jPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            kPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {
            lPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            wPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            aPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            sPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            dPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_I) {
            iPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            jPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            kPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {
            lPressed = false;
        }
    }
}
