package main.java;

import main.java.controller.GameController;
import main.java.items.*;
import main.java.map.Hedgehog;

public class CatchVisitor implements Visitor {
    private Hedgehog hedgehog;

    @Override
    public void visit(Bug bug) {
        System.out.println("[ Caught a bug ]");
        hedgehog.setScore(hedgehog.getScore() + bug.getBonus());
    }

    @Override
    public void visit(Worm worm) {
        System.out.println("[ Caught a worm ]");
        hedgehog.setScore(hedgehog.getScore() + worm.getBonus());
    }

    @Override
    public void visit(Slug slug) {
        System.out.println("[ Caught a slug ]");
        hedgehog.setScore(hedgehog.getScore() + slug.getBonus());
        hedgehog.setSpeed(hedgehog.getSpeed() + slug.getPositiveSpeed());
    }

    @Override
    public void visit(Millipede millipede) {
        System.out.println("[ Caught a millipede ]");
        hedgehog.setScore(hedgehog.getScore() + millipede.getBonus());
        hedgehog.setHealth(hedgehog.getHealth() + millipede.getPositiveHealth());
    }

    @Override
    public void visit(Grape grape) {
        System.out.println("[ Caught a grape ]");
        hedgehog.setHealth(hedgehog.getHealth() - grape.getNegativeHealth());
    }

    @Override
    public void visit(Onion onion) {
        System.out.println("[ Caught an onion ]");
        hedgehog.setSpeed(hedgehog.getSpeed() - onion.getNegativeSpeed());
        hedgehog.setHealth(hedgehog.getHealth() - onion.getNegativeHealth());
    }

    @Override
    public void visit(Chocolate chocolate) {
        System.out.println("[ Caught a chocolate ]");
        hedgehog.setHealth(0);
        GameController.isFinished = true;
    }

    public void setHedgehog(Hedgehog hedgehog) {
        this.hedgehog = hedgehog;
    }
}
