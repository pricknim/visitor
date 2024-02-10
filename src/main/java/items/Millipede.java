package main.java.items;

import main.java.Visitor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Millipede extends FallingItem {
    private int bonus = 200;
    private int positiveHealth = 15;

    public Millipede(int offset) {
        super(offset);

        Image sprite = null;
        try {
            sprite = ImageIO.read(new File("./src/main/resources/milli.png"));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ Failed to load millipede image: " + e.getMessage() + " ]");
        }

        setSpeed(25);
        setSprite(sprite);
    }

    @Override
    public void acceptCatchVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    public int getBonus() {
        return bonus;
    }

    public int getPositiveHealth() {
        return positiveHealth;
    }
}
