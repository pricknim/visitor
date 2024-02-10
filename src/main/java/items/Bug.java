package main.java.items;

import main.java.Visitor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bug extends FallingItem {
    private int bonus = 100;

    public Bug(int offset) {
        super(offset);

        Image sprite = null;
        try {
            sprite = ImageIO.read(new File("./src/main/resources/beetle.png"));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ Failed to load bug image: " + e.getMessage() + " ]");
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
