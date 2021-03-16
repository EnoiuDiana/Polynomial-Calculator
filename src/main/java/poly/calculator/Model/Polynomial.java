package poly.calculator.Model;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class Polynomial {
    List<Monomial> polynomial = new ArrayList<>();

    public void addMonomial(Monomial monomial){
        this.polynomial.add(monomial);
    }

    protected void listMonomials() {
        for (Monomial monomial : polynomial) {
            System.out.println(monomial.getMonomial());
        }
    }

    protected void updatePolynomial() {
        for (Monomial monomial : polynomial) {
            monomial.updateMonomialString();
        }
    }

    public void simplifyAndSort() {
        //simplify
        for(int i=0; i<polynomial.size(); i++) {
            int power_1 = polynomial.get(i).getPower();
            for(int j=i+1; j<polynomial.size();j++) {
                int power_2 = polynomial.get(j).getPower();
                if(power_2 == power_1) {
                    polynomial.get(i).addCoefficient(polynomial.get(j).getCoefficient());
                    this.polynomial.remove(j);
                }
            }
        }
        //sort
        this.polynomial.sort(Monomial.compareByPower());
        Collections.reverse(this.polynomial);

        //i have modified the coefficients of the monomials
        //so i  have to update the string
        updatePolynomial();
    }

    public String polynomialToString() {
        String polyString = "";
        for (Monomial monomial : polynomial) {
            double coefficient = monomial.getCoefficient();
            if(coefficient >= 0 && !polyString.isEmpty()) {
                polyString = polyString.concat("+");
            }
            polyString = polyString.concat(monomial.getMonomial());
        }
        return polyString;
    }
}
