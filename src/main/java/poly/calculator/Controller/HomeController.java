package poly.calculator.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import poly.calculator.Model.Polynomial;

public class HomeController {

    @FXML
    private Label result;
    @FXML
    private TextField firstPolynomial;
    @FXML
    private TextField secondPolynomial;

    public void insertResultOfOperation(String resultPoly) {
        if(resultPoly.length() > 43) {
            result.setText(resultPoly.substring(0,43) + "\n" + resultPoly.substring(43));
        } else {
            result.setText(resultPoly);
        }
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        String operation = button.getId();
        String firstPolyString = firstPolynomial.getText();
        String secondPolyString;
        if(operation.equals("deriv") || operation.equals("integ")) {
            secondPolyString = "0";
        } else {
            secondPolyString = secondPolynomial.getText();
        }
        String resultPolyString = performPolynomialCalculations(firstPolyString, secondPolyString, operation);
        insertResultOfOperation(resultPolyString);
    }

    public static String performPolynomialCalculations (String firstPolyString, String secondPolyString, String operation) {
        Polynomial firstPoly = InputAnalyze.parseInputPolynomialString(firstPolyString);
        Polynomial secondPoly = InputAnalyze.parseInputPolynomialString(secondPolyString);

        if(firstPoly == null || secondPoly == null) {
            return "Error: incorrectPolynomials";
        } else {
            firstPoly.simplifyAndSort();
            secondPoly.simplifyAndSort();
            Polynomial resultPoly = InputAnalyze.identifyOperationAndPerform(operation, firstPoly, secondPoly);
            if(!operation.equals("div")) {
                resultPoly.simplifyAndSort();
            }
            String resultPolyString = resultPoly.polynomialToString();
            if(resultPolyString.isEmpty()) {
                resultPolyString = "0";
            }
            return resultPolyString;
        }
    }
}