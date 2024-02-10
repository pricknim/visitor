package main.java.items;

import main.java.Visitor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Slug extends FallingItem {
    private int bonus = 100;
    private int positiveSpeed = 10;

    public Slug(int offset) {
        super(offset);

        Image sprite = null;
        try {
            sprite = ImageIO.read(new File("./src/main/resources/slug.png"));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ Failed to load slug image: " + e.getMessage() + " ]");
        }

        setSpeed(20);
        setSprite(sprite);
    }

    @Override
    public void acceptCatchVisitor(Visitor visitor) {
        // adds hedgie
        visitor.visit(this);
    }

    public int getBonus() {
        return bonus;
    }

    public int getPositiveSpeed() {
        return positiveSpeed;
    }
}
