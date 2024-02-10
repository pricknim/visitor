package main.java.items;

import main.java.Visitor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Onion extends FallingItem {
    private int negativeHealth = 15;
    private int negativeSpeed = 10;

    public Onion(int offset) {
        super(offset);

        Image sprite = null;
        try {
            sprite = ImageIO.read(new File("./src/main/resources/onion.png"));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ Failed to load onion image: " + e.getMessage() + " ]");
        }

        setSpeed(15);
        setSprite(sprite);
    }

    @Override
    public void acceptCatchVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    public int getNegativeHealth() {
        return negativeHealth;
    }

    public int getNegativeSpeed() {
        return negativeSpeed;
    }
}
