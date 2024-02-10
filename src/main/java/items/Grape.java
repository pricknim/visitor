package main.java.items;

import main.java.Visitor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Grape extends FallingItem {
    private int negativeHealth = 5;

    public Grape(int offset) {
        super(offset);

        Image sprite = null;
        try {
            sprite = ImageIO.read(new File("./src/main/resources/grape.png"));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ Failed to load grape image: " + e.getMessage() + " ]");
        }

        setSpeed(20);
        setSprite(sprite);
    }

    @Override
    public void acceptCatchVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    public int getNegativeHealth() {
        return negativeHealth;
    }
}
