package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    public SnakeGame() {
        setBackground(Color.BLUE);
        loadImage();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void loadImage() {
        ImageIcon imageIconApple = new ImageIcon(getClass().getResource("SnakeGame/apple.png"));
        apple = imageIconApple.getImage();
        ImageIcon imageIconDot = new ImageIcon(getClass().getResource("SnakeGame/dot.png"));
        dot = imageIconDot.getImage();
    }

    public void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 48;
        }
        timer = new Timer(250, this);
        timer.start();

        createApple();

        inGame = true;
    }

    public void createApple() {
        appleX = new Random().nextInt(20) * DOT_SIZE;
        appleY = new Random().nextInt(20) * DOT_SIZE;
    }

    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (left) {
            x[0] -= DOT_SIZE;
        }
        if (right) {
            x[0] += DOT_SIZE;
        }
        if (up) {
            y[0] -= DOT_SIZE;
        }
        if (down) {
            y[0] += DOT_SIZE;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(apple, appleX, appleY, DOT_SIZE, DOT_SIZE, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], DOT_SIZE, DOT_SIZE, this);
            }
        } else {
            String gameOver = "Game Over";
            g.setColor(Color.GREEN);
            g.drawString(gameOver, 125, SIZE / 2);
        }
    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            dots++;
            createApple();
        }
    }

    public void checkCollisions() {
        for (int i = dots; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
            }
        }
        if (x[0] >= SIZE || x[0] < 0 || y[0] >= SIZE || y[0] < 0) {
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollisions();
            move();
        }
        repaint();
    }

    private class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            int key = e.getKeyCode();

            left = false;
            right = false;
            up = false;
            down = false;

            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
            }
            if (key == KeyEvent.VK_UP && !down) {
                up = true;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                down = true;
            }
        }
    }
}
