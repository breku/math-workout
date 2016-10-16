package com.breku.math.game.equation;

/**
 * Created by brekol on 13.10.16.
 */
public class MathEquation {

    private Integer x;
    private Integer y;
    private Integer result;
    private MathParameter mathParameter;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public MathParameter getMathParameter() {
        return mathParameter;
    }

    public void setMathParameter(MathParameter mathParameter) {
        this.mathParameter = mathParameter;
    }

    public String getStringRepresentation() {
        return String.format("%s %s %s = %s", x, mathParameter.getStringRepresentation(), y, getResult());
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public boolean hasValidParameters() {
        switch (mathParameter) {
            case DIV:
                return y != 0 && x % y == 0;
        }
        return true;
    }

    public boolean isCorrect() {
        switch (mathParameter) {
            case ADD:
                return x + y == result;
            case SUB:
                return x - y == result;
            case MUL:
                return x * y == result;
            case DIV:
                return y != 0 && x / y == result && x % y == 0;
            default:
                throw new UnsupportedOperationException();
        }
    }


}
