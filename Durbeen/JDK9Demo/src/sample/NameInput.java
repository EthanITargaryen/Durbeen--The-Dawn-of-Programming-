package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

import java.io.Serializable;

public class NameInput implements Serializable {

    private static int iName = 0;

    public static Pair<String, String> getString(String q, String r){
        Stage stage = new Stage(StageStyle.DECORATED);
        ++iName;

        stage.setTitle("Give file's name");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(200);
        stage.setMinHeight(250);

        q = "newFile(" + iName + ")";
        r = "File Description";

        Label label = new Label("ফাইলের নাম");
        TextField t = new TextField(r);
        TextField tf = new TextField(q);
        Button button = new Button("গেমটি সেভ কর");

        button.setOnAction(event -> {
            stage.close();
        });

        VBox vBox = new VBox(12, label, tf, t, button);
        vBox.setAlignment(Pos.CENTER);

        vBox.setStyle("-fx-background-color:#ccccff");

        stage.setScene(new Scene(vBox, 200, 150));
        stage.showAndWait();

        q = tf.getText();
        r = t.getText();

        System.out.println("q & r: " + q + " & " + r);

        return new Pair<>(q,r);
    }

}
