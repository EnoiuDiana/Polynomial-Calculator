package poly.calculator.Model;

import java.util.Iterator;

public class Operation {

    public static Polynomial addition(Polynomial firstPoly, Polynomial secondPoly) {
        Polynomial resultPoly = new Polynomial();
        for(Monomial monomial1 : firstPoly.polynomial) {
            int power1 = monomial1.getPower();
            boolean foundSamePower = false;
            Iterator<Monomial> itrMonomial = secondPoly.polynomial.iterator();
             while (itrMonomial.hasNext()) {
                 Monomial monomial2 = itrMonomial.next();
                 int power2 = monomial2.getPower();
                 if(power1 == power2) { //add the sum of the monomials
                     foundSamePower = true;
                     resultPoly.addMonomial(Monomial.add(monomial1, monomial2));
                     itrMonomial.remove();//remove the monomial from second polynomial
                 }
             }
            if(!foundSamePower) { //add monomial as it is from first polynomial
                resultPoly.addMonomial(monomial1);
            }
        }
        for(Monomial monomial2 : secondPoly.polynomial) {  //add the remaining monomials from second polynomial
            resultPoly.addMonomial(monomial2);
        }
        return resultPoly;
    }

    public static Polynomial subtraction(Polynomial firstPoly, Polynomial secondPoly) {
        for(Monomial monomial : secondPoly.polynomial) {
            monomial.changeSignOfCoefficient();
        }
        return addition(firstPoly, secondPoly);
    }

    public static Polynomial multiplication(Polynomial firstPoly, Polynomial secondPoly) {
        Polynomial resultPoly = new Polynomial();
        for(Monomial monomial1 : firstPoly.polynomial) {
            for(Monomial monomial2 : secondPoly.polynomial) {
                resultPoly.addMonomial(Monomial.multiply(monomial1, monomial2));
            }
        }
        return resultPoly;
    }

    private static Monomial computeNewQuotientTerm(Polynomial remainder, Polynomial divisorPoly) {
        Monomial dividendMonomial = remainder.polynomial.get(0);
        Monomial divisorMonomial = divisorPoly.polynomial.get(0);
        Monomial newMonomialInQuotient = Monomial.divide(dividendMonomial, divisorMonomial);
        newMonomialInQuotient.updateMonomialString();
        return newMonomialInQuotient;
    }

    private static Polynomial computeDivisionResult(Polynomial quotient, Polynomial remainder) {
        Polynomial resultPoly = new Polynomial();
        resultPoly.addMonomial(new Monomial("Q: ", -1, 0));
        for (Monomial monomial : quotient.polynomial) {
            resultPoly.addMonomial(monomial);
        }
        resultPoly.addMonomial(new Monomial("  R: ", -1, 0));
        for (Monomial monomial : remainder.polynomial) {
            resultPoly.addMonomial(monomial);
        }
        return resultPoly;
    }

    public static Polynomial division(Polynomial dividendPoly, Polynomial divisorPoly) {
        Polynomial resultPoly = new Polynomial();
        if(divisorPoly.polynomial.size() == 1 && divisorPoly.polynomial.get(0).getCoefficient() == 0) {
            resultPoly.addMonomial(new Monomial("Error: Division by zero",-1,0));
            return resultPoly;
        }
        Polynomial quotient = new Polynomial();
        Polynomial remainder = dividendPoly;
        if (dividendPoly.getDegree() < divisorPoly.getDegree()) {
            quotient.addMonomial(new Monomial("0", 0, 0));
        } else {
            while (remainder.getDegree() >= divisorPoly.getDegree()) {
                Monomial newMonomialInQuotient = computeNewQuotientTerm(remainder, divisorPoly);
                quotient.addMonomial(newMonomialInQuotient);
                Polynomial temporary = new Polynomial();
                temporary.addMonomial(newMonomialInQuotient);
                remainder = subtraction(remainder, multiplication(temporary, divisorPoly));
                remainder.simplifyAndSort();
            }
        }
        resultPoly = computeDivisionResult(quotient, remainder);
        return resultPoly;
    }

    public static Polynomial derivative(Polynomial inputPolynomial) {
        Polynomial resultPoly = new Polynomial();
        for(Monomial monomial : inputPolynomial.polynomial) {
            resultPoly.addMonomial(Monomial.derive(monomial));
        }
        return resultPoly;
    }

    public static Polynomial integrate(Polynomial inputPolynomial) {
        Polynomial resultPoly = new Polynomial();
        for(Monomial monomial : inputPolynomial.polynomial) {
            resultPoly.addMonomial(Monomial.integral(monomial));
        }
        return resultPoly;
    }
 }
