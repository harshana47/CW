package org.example.util;

import javafx.scene.paint.Paint;
import org.example.util.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text){
        String filed = "";

        switch (textField){
            case DURATION :
                filed = "^[1-9][0-9]*(?:[mMyY])$";
                break;
            case ADDRESS:
                filed = "^[A-z|\\\\s]{3,}$";
                break;
            case ADVANCE:
                filed = "^[0-9]+(\\.[0-9]{1,2})?$";
                break;
            case ID:
                filed = "^[1-9][0-9]*$";
                break;
            case NAME:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)?$";
                break;
            case EMAIL:
                filed = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case CONTACT:
                filed = "^(?:\\\\+94|94|0)(7[0-9]|0[0-9])[0-9]{7,8}$";
                break;

        }

        Pattern pattern = Pattern.compile(filed);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField){
        if(Regex.isTextFieldValid(location,textField.getText())){
            textField.setStyle("-fx-text-fill: green;");
            textField.setStyle("-fx-text-fill: green;");

            return true;
        }else{
            textField.setStyle("-fx-text-fill: red;");
            textField.setStyle("-fx-text-fill: red;");
            return false;
        }
    }
}