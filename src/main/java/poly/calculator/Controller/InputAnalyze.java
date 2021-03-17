package poly.calculator.Controller;

import poly.calculator.Model.Monomial;
import poly.calculator.Model.Operation;
import poly.calculator.Model.Polynomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputAnalyze {
    public static Polynomial parseInputPolynomialString(String userInputPolynomial) {
        if(userInputPolynomial.isEmpty()) return null;

        Pattern pattern = Pattern.compile("([+-]?(?:(?:\\d+x\\^[+-]?\\d+)|(?:x\\^[+-]?\\d+)|(?:\\d+x)|(?:\\d+)|(?:x)))", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userInputPolynomial);

        //verify if the polynomial is correct by rebuilding it from pattern matches
        String rebuildPolynomial = "";
        while(matcher.find()) {
            rebuildPolynomial = rebuildPolynomial.concat(matcher.group());
        }
        if(!rebuildPolynomial.equals(userInputPolynomial)) {
            return null;
        }
        return addMonomialsToPolynomial(pattern, userInputPolynomial);
    }

    private static Polynomial addMonomialsToPolynomial(Pattern pattern, String userInputPolynomial) {
        //compute the monomials and add the result to polynomial
        Polynomial polynomial = new Polynomial();
        Matcher matcher = pattern.matcher(userInputPolynomial);
        while(matcher.find()) {
            Monomial monomial = parseMonomial(matcher.group());
            polynomial.addMonomial(monomial);
        }
        return polynomial;
    }

    private static Monomial parseMonomial(String userInputMonomial) {
        Pattern pattern = Pattern.compile("(?:[+-])|(?:\\d+)|(?:[x])|(?:[\\^])|(?:\\d)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userInputMonomial);
        String signCoefficient = "+";
        int coefficient = 1;
        boolean foundX = false;
        String signPower = "+";
        int power = 0;
        while(matcher.find()) {
             if(matcher.group().equals("-")) {
                 if(!foundX) {
                     signCoefficient = "-";
                 } else {
                     signPower = "-";
                 }
            } else if (isNumeric(matcher.group())){
                 if(!foundX) {
                     coefficient = Integer.parseInt(matcher.group());
                 } else {
                     power = Integer.parseInt(matcher.group());
                 }
             } else if (matcher.group().equals("x") || matcher.group().equals("X")){
                 foundX = true;
                 power = 1;
             }
        }
        if(signCoefficient.equals("-")) {
            coefficient = coefficient * (-1);
        }
        if(signPower.equals("-")) {
            power = power * (-1);
        }
        return new Monomial(userInputMonomial, coefficient, power);
    }

    public static Polynomial identifyOperationAndPerform(String operation, Polynomial firstPoly, Polynomial secondPoly) {
        Polynomial resultPolynomial = null;
        if(operation.equals("add")) {
            resultPolynomial = Operation.addition(firstPoly, secondPoly);
        } else if(operation.equals("sub")) {
            resultPolynomial = Operation.subtraction(firstPoly, secondPoly);
        } else if(operation.equals("multi")) {
            resultPolynomial = Operation.multiplication(firstPoly, secondPoly);
        } else if(operation.equals("div")) {
            resultPolynomial = Operation.division(firstPoly, secondPoly);
        } else if(operation.equals("deriv")) {
            resultPolynomial = Operation.derivative(firstPoly);
        } else if(operation.equals("integ")) {
            resultPolynomial = Operation.integrate(firstPoly);
        }
        return resultPolynomial;
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
