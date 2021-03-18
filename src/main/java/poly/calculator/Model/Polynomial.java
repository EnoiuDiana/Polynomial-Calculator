package poly.calculator.Model;

import java.util.*;

public class Polynomial {
    List<Monomial> polynomial = new ArrayList<>();

    protected void addMonomial(Monomial monomial){
        this.polynomial.add(monomial);
    }

    private void updatePolynomial() {
        for (Monomial monomial : polynomial) {
            monomial.updateMonomialString();
        }
    }

    private Iterator<Monomial> iteratorFromIndex(int x) {
        if (x < 0 || this.polynomial.size() < x) {
            throw new IndexOutOfBoundsException();
        }

        Iterator<Monomial> it = polynomial.iterator();

        for (; x > 0; --x) {
            it.next(); // ignore the first x values
        }
        return it;
    }

    public void simplifyAndSort() {
        //if we have zero monomials remove them
        polynomial.removeIf(monomial -> monomial.getCoefficient() == 0);
        //simplify
        for(int i=0; i<polynomial.size(); i++) {
            int power_1 = polynomial.get(i).getPower();
            Iterator<Monomial> monomialIterator = iteratorFromIndex(i+1);
            while (monomialIterator.hasNext()) {
                Monomial monomial = monomialIterator.next();
                int power_2 = monomial.getPower();
                if(power_2 == power_1) {
                    polynomial.get(i).addCoefficient(monomial.getCoefficient());
                    monomialIterator.remove();
                }
            }
        }
        //sort
        this.polynomial.sort(Monomial.compareByPower());
        Collections.reverse(this.polynomial);

        //i have modified the coefficients of the monomials
        //so i  have to update the string
        updatePolynomial();

        if(polynomial.isEmpty()) {
            polynomial.add(new Monomial("0",0,0));
        }
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

    protected int getDegree() {  //sort the polynomial before using this function
        int p = -1;
        if(polynomial.get(0).getCoefficient() != 0) p = polynomial.get(0).getPower();
        return p;
    }
}
