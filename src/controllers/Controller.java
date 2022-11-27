package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import utilities.Calculation;

public class Controller {

    @FXML
    private TextField txt_com_strength;

    @FXML
    private TextField txt_water_cement;

    @FXML
    private TextField txt_rubber_size;

    @FXML
    private TextField txt_rep_level;

    @FXML
    private Label lbl_com_strength;

    private String com_strength,water_cement,rubber_size,rep_level;

    private double com_strength_value,water_cement_value,rubber_size_value,rep_level_value;

    public void initialize() {
        com_strength = txt_com_strength.getText();
        water_cement = txt_water_cement.getText();
        rubber_size = txt_rubber_size.getText();
        rep_level = txt_rep_level.getText();
    }

    public boolean validateValues(){
        try {
            com_strength_value = Double.parseDouble(com_strength);
            water_cement_value = Double.parseDouble(water_cement);
            rubber_size_value  = Double.parseDouble(rubber_size);
            rep_level_value    = Double.parseDouble(rep_level);
        }catch (NumberFormatException e){
            return false;
        }
        if (com_strength_value >= 27 && com_strength_value <= 42.5)
            if (water_cement_value >= 0.4 && water_cement_value <= 0.7)
                if (rubber_size_value > 0 && rubber_size_value <= 5)
                    if (rep_level_value > 0 && rep_level_value <= 20)
                        return true;
                    else return false;
                else return false;
            else return false;
        else return false;
    }

    public void calculateValues(){
        initialize();
        if (validateValues()){
            Calculation calculation = new Calculation(com_strength_value,water_cement_value,rubber_size_value,rep_level_value);
            String[] values = calculation.getValues();
            showResults(values);
        }else {
            showAlert();
        }
    }

    public void showResults(String[] values){
        checkDifference(values);
        lbl_com_strength.setText("Predicted compressive strength is between " + values[3] + " - " + values[2] + " MPa");
    }

    public void checkDifference(String[] values){
        double upperValue = Double.parseDouble(values[2]);
        double lowerValue = Double.parseDouble(values[3]);
        double difference = upperValue - lowerValue;
        if (difference >= 10)
            displayWarning();
    }

    public void displayWarning(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Warning! Range exceeds tolerance of 10MPa.");
        alert.showAndWait();
    }

    public void showAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please enter valid values only!");
        alert.showAndWait();
    }

    public void resetValues(){
        txt_com_strength.clear();
        txt_water_cement.clear();
        txt_rubber_size.clear();
        txt_rep_level.clear();
        lbl_com_strength.setText("");
    }

}
