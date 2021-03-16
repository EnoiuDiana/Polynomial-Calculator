package poly.calculator.Model;


import java.util.Comparator;

import static com.google.common.math.DoubleMath.isMathematicalInteger;

public class Monomial {
    private String monomial;
    private double coefficient;
    private final int power;

    public Monomial(String monomial, double coefficient, int power) {
        this.monomial = monomial;
        this.coefficient = coefficient;
        this.power = power;
    }

    protected void addCoefficient(double coefficientToAdd) {
        this.coefficient = this.coefficient + coefficientToAdd;
    }

    protected static Comparator<Monomial> compareByPower() {
        return Comparator.comparingInt(Monomial::getPower);
    }
    protected void updateMonomialString() {
        String newMonomial;
        if(isMathematicalInteger(this.coefficient)) {
            int integerCoefficient = (int) this.coefficient;
            newMonomial = buildTheNewMonomialString(integerCoefficient);
        } else {
            newMonomial = buildTheNewMonomialString(this.coefficient);
        }
        monomial = newMonomial;
    }

    private String buildTheNewMonomialString(Number coeff) {
        String newMonomial;
        if (coeff.equals(0)) {
            newMonomial = "0";
        } else if (power == 0) {
            newMonomial = coeff + "";
        } else if (coeff.equals(1)) {
            if (power == 1) {
                newMonomial = "x";
            } else {
                newMonomial = "x^" + power;
            }
        } else if (coeff.equals(-1)) {
            if (power == 1) {
                newMonomial = "-x";
            } else {
                newMonomial = "-x^" + power;
            }
        } else {
            if (power == 1) {
                newMonomial = coeff + "x";
            } else {
                newMonomial = coeff + "x^" + power;
            }
        }
        return newMonomial;
    }

    public String getMonomial() {
        return monomial;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getPower() {
        return power;
    }
}
