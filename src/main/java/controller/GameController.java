package main.java.controller;

import main.java.GameFrame;
import main.java.items.*;
import main.java.map.GamePanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameController extends Thread {

    private final ArrayList<FallingItem> items = new ArrayList<>();
    public static boolean isFinished = false;
    private GamePanel panel;
    private final int THRESHOLD = 50;

    public GameController() {
        int offset = -30;
        System.out.println("[ Loading in game map ]");
        ArrayList<FallingItem> readInItems = new ArrayList<>();
        File map = new File("./src/main/resources/maps/text.txt");

        try {
            Scanner scanner = new Scanner(map);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                switch (data) {
                    case "bug" -> readInItems.add(new Bug(offset));
                    case "chocolate" -> readInItems.add(new Chocolate(offset));
                    case "grape" -> readInItems.add(new Grape(offset));
                    case "milli" -> readInItems.add(new Millipede(offset));
                    case "onion" -> readInItems.add(new Onion(offset));
                    case "slug" -> readInItems.add(new Slug(offset));
                    case "worm" -> readInItems.add(new Worm(offset));
                    default -> {
                        System.out.println("[ " + data + " is not a valid item -> ignored ]");
                        continue;
                    }
                }

                offset -= 130;
            }
            scanner.close();
            System.out.println("[ Finished reading the map file ]");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        items.addAll(readInItems);
    }

    public ArrayList<FallingItem> getItems() {
        return items;
    }

    public void setPanel(GamePanel panel) {
        this.panel = panel;
    }

    public GamePanel getPanel() {
        return panel;
    }

    @Override
    public void run() {
        while (!isFinished) {
            for (FallingItem item: items) {
                item.setY(item.getY() + item.getSpeed());
            }

            items.forEach(item -> {
                if (Math.abs(item.getY() - panel.getHedgehogY()) <= (THRESHOLD - 10) &&
                        Math.abs(item.getX() - panel.getHedgehogX()) <= THRESHOLD) {
                    item.setActive(false);
                    item.acceptCatchVisitor(panel.getHedgehog());
                }
            });

            items.removeIf(item -> item.getY() > GameFrame.HEIGHT + 100 || !item.isActive());

            panel.repaint();

            if (items.isEmpty()) {
                isFinished = true;
                System.out.println(panel.getHedgehog().isDead() ? "\nYOU LOST\n" : "\nYOU WON\n");
            }

            try {
                Thread.sleep(160);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        panel.repaint();
    }
}
