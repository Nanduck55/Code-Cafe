package codecafe.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ClickStartController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    public void initialize() {
        rootPane.setOnMouseClicked(this::handleClick);
    }

    private void handleClick(MouseEvent event) {
        try {
            Parent orderTypeRoot = FXMLLoader.load(getClass().getResource("/codecafe/view/order_type.fxml"));

            Scene scene = new Scene(orderTypeRoot, 1920, 1080);
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}