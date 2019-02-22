package sample;

import javafx.scene.control.ChoiceBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ControllerButton extends fButton {

    private CodeBox codeBox;

    private static String controllerButtonStyle = "-fx-background-color: #FF8000;" + "-fx-text-fill:snow;" +
            "-fx-padding: 6 6 6 6;" + "-fx-border-color: darkblue;" +
            "fx-border-radius: 4;"+ "-fx-font-weight: bold;"+
            "-fx-alignment: center-left";

    public ControllerButton(CodeBox codeBox1) {
        codeBox = codeBox1;
    }

    public static fButton spriteClickedButton() {
        fButton button = new fButton("যখন তুমি বিড়ালের উপর ক্লিক করো", false);
        button.setStyle(controllerButtonStyle);
        return button;
    }


    public static fButton thisClickedButton() {
        fButton button = new fButton("যখন তুমি এই বোতামের উপর ক্লিক করো", false);
        button.setStyle(controllerButtonStyle);
        return button;
    }

    public static fButton keyPressedButton() {
        ChoiceBox<String> stringChoiceBox = new ChoiceBox<>();
        Character ch;

        /*ChoiceBox<Rectangle> shapeChoiceBox = new ChoiceBox<>();
        shapeChoiceBox.getItems().add(new Rectangle(10,10));
        shapeChoiceBox.getItems().add(new Rectangle(5, 5));*/

        for(ch = 'A'; ch <= 'Z'; ++ch){
            stringChoiceBox.getItems().add(ch.toString());
        }

        fButton button = new fButton("যখন তুমি এই বোতাম চাপো", stringChoiceBox);
        button.setStyle(controllerButtonStyle);
        //button.setGraphic(stringChoiceBox);

        return button;
    }

}
