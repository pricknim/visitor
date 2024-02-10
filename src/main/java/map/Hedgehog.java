package main.java.map;

import main.java.Visitor;
import main.java.controller.GameController;
import main.java.items.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Hedgehog implements Visitor {
    private Image hedgie;
    private int health;
    private String direction;
    private int speed;
    private int x;
    private int y;
    private int score;
    private boolean isDead = false;

    public Hedgehog() {
        setDirection("right");
        health = 100;
        x = 200;
        y = 650;
        speed = 12;
        score = 0;
    }

    Image getHedgie() {
        return hedgie;
    }

    public void setDirection(String direction) {
        this.direction = direction;
        loadImage();
    }

    public String getDirection() {
        return direction;
    }

    private void loadImage() {
        try {
            hedgie = ImageIO.read(new File("./src/main/resources/"+ direction + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load hedgehog image: " + e.getMessage());
        }
    }

    public void killHedgie() {
        isDead = true;
        try {
            hedgie = ImageIO.read(new File("./src/main/resources/"+ direction + "_dead1.png"));
            System.out.println("\nYOU LOST\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load hedgehog image: " + direction + "_dead ; " + e.getMessage());
        }
    }

    @Override
    public void visit(Bug bug) {
        System.out.println("[ Caught a bug -> bonus: " + bug.getBonus() +" ]");
        score += bug.getBonus();
    }

    @Override
    public void visit(Worm worm) {
        System.out.println("[ Caught a worm -> bonus: " + worm.getBonus() + " ]");
        score += worm.getBonus();
    }

    @Override
    public void visit(Slug slug) {
        System.out.println("[ Caught a slug -> bonus: " + slug.getBonus() +
                " speed: " + slug.getPositiveSpeed() + " ] ");
        score += slug.getBonus();
        speed += slug.getPositiveSpeed();
    }

    @Override
    public void visit(Millipede millipede) {
        System.out.println("[ Caught a millipede -> bonus: " + millipede.getBonus() +
                " health: " + millipede.getPositiveHealth() + " ]");
        score += millipede.getBonus();
        health += millipede.getPositiveHealth();
    }

    @Override
    public void visit(Grape grape) {
        System.out.println("[ Caught a grape -> health: -" + grape.getNegativeHealth() + " ]");
        health -= grape.getNegativeHealth();
    }

    @Override
    public void visit(Onion onion) {
        System.out.println("[ Caught an onion -> health: -" + onion.getNegativeHealth() +
                " speed: -" + onion.getNegativeSpeed() + " ]");
        speed -= onion.getNegativeSpeed();
        health -= onion.getNegativeHealth();
    }

    @Override
    public void visit(Chocolate chocolate) {
        System.out.println("[ Caught a chocolate -> you died ]");
        setHealth(0);
        GameController.isFinished = true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed > 5 && speed < 30) {
            this.speed = speed;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (health == 0) {
            killHedgie();
            GameController.isFinished = true;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isDead() {
        return isDead;
    }

}
