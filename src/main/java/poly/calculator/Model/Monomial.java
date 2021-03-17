package poly.calculator.Model;


import java.math.BigDecimal;
import java.math.RoundingMode;
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

    protected static Monomial add(Monomial monomial1, Monomial monomial2) {
        Monomial result;
        double resultCoefficient = monomial1.getCoefficient() + monomial2.getCoefficient();
        int resultPower = monomial1.getPower();
        result = new Monomial("", resultCoefficient, resultPower);
        result.updateMonomialString();
        return result;
    }

    protected static Monomial multiply(Monomial monomial1, Monomial monomial2) {
        Monomial result;
        double resultCoefficient = monomial1.getCoefficient() * monomial2.getCoefficient();
        int resultPower = monomial1.getPower() + monomial2.getPower();
        result = new Monomial("", resultCoefficient, resultPower);
        result.updateMonomialString();
        return result;
    }

    protected static Monomial divide(Monomial monomial1, Monomial monomial2) {
        Monomial result;
        double resultCoefficient = monomial1.getCoefficient() / monomial2.getCoefficient();
        int resultPower = monomial1.getPower() - monomial2.getPower();
        result = new Monomial("", resultCoefficient, resultPower);
        result.updateMonomialString();
        return result;
    }

    protected static Monomial derive(Monomial monomial1) {
        Monomial result;
        double resultCoefficient = monomial1.getCoefficient() * monomial1.getPower();
        int resultPower = monomial1.getPower() - 1;
        result = new Monomial("", resultCoefficient, resultPower);
        result.updateMonomialString();
        return result;
    }

    protected static Monomial integral(Monomial monomial1) {
        Monomial result;
        double resultCoefficient = monomial1.getCoefficient() / (monomial1.getPower() + 1);
        int resultPower = monomial1.getPower() + 1;
        result = new Monomial("", resultCoefficient, resultPower);
        result.updateMonomialString();
        return result;
    }

    protected static Comparator<Monomial> compareByPower() {
        return Comparator.comparingInt(Monomial::getPower);
    }
    protected void updateMonomialString() {
        String newMonomial;
        if(isMathematicalInteger(this.coefficient)) {
            int integerCoefficient = (int) this.coefficient;
            newMonomial = buildTheNewMonomialString(integerCoefficient, false);
        } else {
            newMonomial = buildTheNewMonomialString(this.coefficient, true);
        }
        monomial = newMonomial;
    }

    private double computeDoubleCoeff(double coeff) {
        BigDecimal bd = new BigDecimal(coeff).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private String buildTheNewMonomialString(Number coeff, boolean isDouble) {
        String newMonomial;
        if (coeff.equals(0)) {
            newMonomial = "0";
        } else if (power == 0) {
            if(isDouble) newMonomial = computeDoubleCoeff((double)coeff) + "";
            else newMonomial = coeff + "";
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
                if(isDouble) newMonomial = computeDoubleCoeff((double)coeff) + "x";
                else newMonomial = coeff + "x";
            } else {
                if(isDouble) newMonomial = computeDoubleCoeff((double)coeff) + "x^" + power;
                else newMonomial = coeff + "x^" + power;
            }
        }
        return newMonomial;
    }

    protected void changeSignOfCoefficient() {
        coefficient = coefficient * (-1);
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
