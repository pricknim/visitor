package main.java.items;

import main.java.Visitor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Chocolate extends FallingItem {
    public Chocolate(int offset) {
        super(offset);

        Image sprite = null;
        try {
            sprite = ImageIO.read(new File("./src/main/resources/choco.png"));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ Failed to load chocolate image: " + e.getMessage() + " ]");
        }

        setSpeed(25);
        setSprite(sprite);
    }

    @Override
    public void acceptCatchVisitor(Visitor visitor) {
        visitor.visit(this);
    }
}
