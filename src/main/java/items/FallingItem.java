package main.java.items;

import main.java.GameFrame;
import main.java.Visitor;

import java.awt.*;

public abstract class FallingItem {
    private int x, y;
    private int speed;
    private Image sprite;
    private boolean isActive;

    public FallingItem(int y) {
        this.y = y;
        x = getRandomX();
        isActive = true;
    }
    public FallingItem() {
        y = -50;
        x = 400;
        isActive = true;
    }

    public FallingItem(int x, int y, int speed, Image sprite) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.sprite = sprite;
        isActive = true;
    }

    public abstract void acceptCatchVisitor(Visitor visitor);

    private int getRandomX() {
        int max = GameFrame.WIDTH - 40;
        int min = 20;
        return (int) ((Math.random() * (max - min)) + min);
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
        this.speed = speed;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) { this.sprite = sprite; }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
