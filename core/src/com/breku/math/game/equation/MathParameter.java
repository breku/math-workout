package com.breku.math.game.equation;

/**
 * Created by brekol on 15.10.16.
 */
public enum MathParameter {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/");

    private final String stringRepresentation;

    MathParameter(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public String getStringRepresentation() {
        return stringRepresentation;
    }
}
