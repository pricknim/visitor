package main.java.items;

import main.java.Visitor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Worm extends FallingItem {
    private int bonus = 150;

    public Worm(int offset) {
        super(offset);

        Image sprite = null;
        try {
            sprite = ImageIO.read(new File("./src/main/resources/worm.png"));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ Failed to load worm image: " + e.getMessage() + " ]");
        }

        setSpeed(15);
        setSprite(sprite);
    }

    @Override
    public void acceptCatchVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    public int getBonus() {
        return bonus;
    }
}
