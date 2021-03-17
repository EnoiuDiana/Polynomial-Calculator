import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import poly.calculator.Controller.HomeController;

public class TestJunit5 {

    @Test
    void TestAddition() {
        System.out.println("addition test");
        assertEquals("2x^3-14x^2+28x-16",
                HomeController.performPolynomialCalculations("x^3-8x^2+17x-10","x^3-6x^2+11x-6","add"));
        assertEquals("x^4+3x^3+4x^2+5",
                HomeController.performPolynomialCalculations("x^4+2x^2+1","3x^3+2x^2+4","add"));
        assertEquals("2x^5-18x^4+82x^3-402x^2+840x-504",
                HomeController.performPolynomialCalculations("x^5-19x^4+131x^3-401x^2+540x-252","x^5+x^4-49x^3-x^2+300x-252","add"));
    }

    @Test
    void TestSubtraction() {
        System.out.println("subtraction test");
        assertEquals("-2x^2+6x-4",
                HomeController.performPolynomialCalculations("x^3-8x^2+17x-10","x^3-6x^2+11x-6","sub"));
        assertEquals("x^4-3x^3-3",
                HomeController.performPolynomialCalculations("x^4+2x^2+1","3x^3+2x^2+4","sub"));
        assertEquals("-20x^4+180x^3-400x^2+240x",
                HomeController.performPolynomialCalculations("x^5-19x^4+131x^3-401x^2+540x-252","x^5+x^4-49x^3-x^2+300x-252","sub"));
    }

    @Test
    void TestMultiplication() {
        System.out.println("multiplication test");
        assertEquals("x^6-14x^5+76x^4-206x^3+295x^2-212x+60",
                HomeController.performPolynomialCalculations("x^3-8x^2+17x-10","x^3-6x^2+11x-6","multi"));
        assertEquals("3x^7+2x^6+6x^5+8x^4+3x^3+10x^2+4",
                HomeController.performPolynomialCalculations("x^4+2x^2+1","3x^3+2x^2+4","multi"));
        assertEquals("x^10-18x^9+63x^8+660x^7-5961x^6+13854x^5+17777x^4-141504x^3+263304x^2-211680x+63504",
                HomeController.performPolynomialCalculations("x^5-19x^4+131x^3-401x^2+540x-252","x^5+x^4-49x^3-x^2+300x-252","multi"));
    }

    @Test
    void TestDivision() {
        System.out.println("division test");
        assertEquals("Q: +1.5x-0.25  R: +54.75x^4+120.25x^3-851.25x^2+993x-315",
                HomeController.performPolynomialCalculations("3x^6+X^5-19x^4+131x^3-401x^2+540x-252","2x^5+x^4-49x^3-x^2+300x-252","div"));
        assertEquals("Error: Division by zero",
                HomeController.performPolynomialCalculations("x^4+2x^2+1","0","div"));
        assertEquals("Q: +0.33x-0.22  R: +2.44x^2-1.33x+1.89",
                HomeController.performPolynomialCalculations("x^4+2x^2+1","3x^3+2x^2+4","div"));
    }

    @Test
    void TestDerivative() {
        System.out.println("derivation test");
        assertEquals("18x^5+5x^4-76x^3+393x^2-802x+540",
                HomeController.performPolynomialCalculations("3x^6+x^5-19x^4+131x^3-401x^2+540x-252","0","deriv"));
        assertEquals("4x^3+4x",
                HomeController.performPolynomialCalculations("x^4+2x^2+1","0","deriv"));
        assertEquals("21x^6+12x^5+30x^4+32x^3+9x^2+20x",
                HomeController.performPolynomialCalculations("3x^7+2x^6+6x^5+8x^4+3x^3+10x^2+4","0","deriv"));
    }

    @Test
    void TestIntegrate() {
        System.out.println("integration test");
        assertEquals("0.43x^7+0.17x^6-3.8x^5+32.75x^4-133.67x^3+270x^2-252x",
                HomeController.performPolynomialCalculations("3x^6+x^5-19x^4+131x^3-401x^2+540x-252","0","integ"));
        assertEquals("0.2x^5+0.67x^3+x",
                HomeController.performPolynomialCalculations("x^4+2x^2+1","0","integ"));
        assertEquals("0.38x^8+0.29x^7+x^6+1.6x^5+0.75x^4+3.33x^3+4x",
                HomeController.performPolynomialCalculations("3x^7+2x^6+6x^5+8x^4+3x^3+10x^2+4","0","integ"));
    }
}
