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
        result.setText(resultPoly);
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        String operation = button.getText();
        String firstPolyString = firstPolynomial.getText();
        String secondPolyString = secondPolynomial.getText();

        Polynomial firstPoly = InputAnalyze.parseInputPolynomialString(firstPolyString);
        Polynomial secondPoly = InputAnalyze.parseInputPolynomialString(secondPolyString);

        if(firstPoly == null || secondPoly == null) {
            result.setText("Error: incorrectPolynomials");
        } else {
            firstPoly.simplifyAndSort();
            secondPoly.simplifyAndSort();
            Polynomial resultPoly = InputAnalyze.identifyOperationAndPerform(operation, firstPoly, secondPoly);
            resultPoly.simplifyAndSort();
            String resultPolyString = resultPoly.polynomialToString();
            insertResultOfOperation(resultPolyString);
        }
    }
}