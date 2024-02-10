package main.java;

import main.java.items.*;

public interface Visitor {
    // GOOD
    void visit(Bug bug);
    void visit(Worm worm);
    void visit(Slug slug);
    void visit(Millipede millipede);

    // BAD
    void visit(Grape grape);
    void visit(Onion onion);
    void visit(Chocolate chocolate);
}
