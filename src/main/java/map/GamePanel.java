package main.java.map;

import main.java.Visitor;
import main.java.controller.GameController;
import main.java.items.FallingItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private Image background;
    private Hedgehog hedgehog;
    private GameController gameController;
    private final ArrayList<FallingItem> items;
    private final JLabel healthLabel;
    private final JLabel scoreLabel;
    private boolean isStarted = false;

    public GamePanel(GameController gameController) {
        this.gameController = gameController;
        items = gameController.getItems();
        try {
            hedgehog = new Hedgehog();
            background = ImageIO.read(new File("./src/main/resources/bg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load background image: " + e.getMessage());
        }

        healthLabel = new JLabel(hedgehog.getHealth() + " ♥ ");
        healthLabel.setFont(new Font("Impact", Font.PLAIN, 30));
        healthLabel.setForeground(new Color(182, 18, 18));

        scoreLabel = new JLabel("Score: " + hedgehog.getScore());
        scoreLabel.setFont(new Font("Impact", Font.PLAIN, 30));
        scoreLabel.setForeground(new Color(231, 228, 228, 255));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!isStarted) {
                    isStarted = true;
                    gameController.start();
                    return;
                }

                if (GameController.isFinished) {
                    if (e.getKeyCode() == 27) {
                        System.out.println("[ Closing the game ]");
                        System.exit(0);
                    }
                }

                if (!hedgehog.isDead()) {
                    switch (e.getKeyCode()) {
                        case 65 -> {
                            if (!"left2".equals(hedgehog.getDirection())) {
                                hedgehog.setDirection("left2");
                            }
                            hedgehog.setX(hedgehog.getX() - hedgehog.getSpeed());
                        }
                        case 68 -> {
                            if (!"right".equals(hedgehog.getDirection())) {
                                hedgehog.setDirection("right");
                            }
                            hedgehog.setX(hedgehog.getX() + hedgehog.getSpeed());
                        }
                        default -> {
                        }
                    }
                }
                repaint();
            }
        });

        JPanel statPanel = new JPanel();
        statPanel.setBackground(new Color(0, 0, 0, 0));
        statPanel.setLayout(new GridBagLayout());

        GridBagConstraints d = new GridBagConstraints();
        d.fill = GridBagConstraints.CENTER;
        d.ipadx = 20;

        d.gridy = 0;
        d.gridx = 0;
        statPanel.add(healthLabel, d);
        d.gridx = 1;
        statPanel.add(scoreLabel, d);

        add(statPanel, BorderLayout.NORTH);

        setVisible(true);
        setFocusable(true);
        requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        scoreLabel.setText("Score: " + hedgehog.getScore());
        healthLabel.setText(hedgehog.getHealth() + " ♥ ");
        super.paintComponent(g);
        g.drawImage(background, 0,0, null);
        g.drawImage(hedgehog.getHedgie(), hedgehog.getX(), hedgehog.getY(), null);

        if (!isStarted) {
            Graphics2D g2d = (Graphics2D) g.create();
            Color c = new Color(1f,1f,1f,.5f );
            g2d.setColor(c);
            g2d.fillRect(0, 0, 1000, 840);
            return;
        }

        for (FallingItem item: items) {
            g.drawImage(item.getSprite(), item.getX(), item.getY(), null);
        }

        if (hedgehog.isDead()) {
            try {
                Image loseImage = ImageIO.read(new File("./src/main/resources/lose.png"));
                g.drawImage(loseImage, 0, 0, null);
            } catch (IOException e) {
                System.out.println("[ Failed to load in failure screen ]");
            }
        }

        if (GameController.isFinished && !hedgehog.isDead()) {
            try {
                Graphics2D g2d = (Graphics2D) g.create();
                Color c = new Color(1f,1f,1f,.5f );
                g2d.setColor(c);
                g2d.fillRect(0, 0, 1000, 840);
                Image loseImage = ImageIO.read(new File("./src/main/resources/asd.png"));
                g.drawImage(loseImage, 0, 0, null);
            } catch (IOException e) {
                System.out.println("[ Failed to load in win screen ]");
            }
        }
    }

    public int getHedgehogX() {
        return hedgehog.getX();
    }

    public int getHedgehogY() {
        return hedgehog.getY();
    }

    public Hedgehog getHedgehog() {
        return hedgehog;
    }

}
