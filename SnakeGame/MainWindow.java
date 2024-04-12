package SnakeGame;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("SnakeGame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(320, 345);
        setLocation(500, 150);
        add(new SnakeGame());
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
