package main.java;

import main.java.controller.GameController;
import main.java.map.GamePanel;

import javax.swing.*;

public class GameFrame extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 840;

    public GameFrame() {
        GameController controller = new GameController();

        setBounds(0, 0, WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Sonic.exe");

        GamePanel panel = new GamePanel(controller);
        controller.setPanel(panel);
        setContentPane(controller.getPanel());

        setVisible(true);
    }

    public static void main(String[] args) {
        new GameFrame();
    }
}
