package poly.calculator.Model;

import java.util.Iterator;

public class Operation {

    private static void addMonomialToPolynomial(Polynomial resultPoly, double coefficient, int power) {
        Monomial resultMonomial = new Monomial("", coefficient, power);
        resultMonomial.updateMonomialString();
        resultPoly.addMonomial(resultMonomial);
    }

    public static Polynomial addition(Polynomial firstPoly, Polynomial secondPoly) {
        Polynomial resultPoly = new Polynomial();
        for(Monomial monomial1 : firstPoly.polynomial) {
            int power1 = monomial1.getPower();
            double coefficient1 = monomial1.getCoefficient();
            boolean foundSamePower = false;
            Iterator<Monomial> itrMonomial = secondPoly.polynomial.iterator();
             while (itrMonomial.hasNext()) {
                 Monomial monomial2 = itrMonomial.next();
                 int power2 = monomial2.getPower();
                 if(power1 == power2) {
                     foundSamePower = true;
                     double coefficient2 = monomial2.getCoefficient();
                     //add the sum of coefficients
                     addMonomialToPolynomial(resultPoly, coefficient1 + coefficient2, power1);
                     //remove the monomial from second polynomial
                     itrMonomial.remove();
                 }
             }
            if(!foundSamePower) {
                //add monomial as it is from first polynomial
                addMonomialToPolynomial(resultPoly, coefficient1, power1);
            }
        }
        //add the remaining monomials from second polynomial
        for(Monomial monomial2 : secondPoly.polynomial) {
            addMonomialToPolynomial(resultPoly, monomial2.getCoefficient(),
                    monomial2.getPower());
        }
        return resultPoly;
    }
}
