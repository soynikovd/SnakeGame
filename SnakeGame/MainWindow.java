import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("SnakeGame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 370);
        setLocation(500, 150);
        add(new SnakeGame());
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
