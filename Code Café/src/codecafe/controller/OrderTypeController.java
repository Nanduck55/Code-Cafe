package codecafe.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class OrderTypeController {

    @FXML
    private AnchorPane click_dine_In;

    @FXML
    private AnchorPane click_take_out;

    @FXML
    private AnchorPane rootPane; 

    @FXML
    public void initialize() {
    click_dine_In.setOnMouseClicked(e -> openMenu(" Dine-In", e));
    click_take_out.setOnMouseClicked(e -> openMenu("Take-Out", e));
    }

private void openMenu(String type, MouseEvent event) {
    try {

        // SAVE ORDER TYPE
        codecafe.model.OrderData.getInstance().setOrderType(type);

        Parent menuRoot = FXMLLoader.load(getClass().getResource("/codecafe/view/menu.fxml"));


        Scene scene = new Scene(menuRoot, 1920, 1080);

        Stage stage = (Stage) ((AnchorPane) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

    } catch (IOException e) {
        e.printStackTrace();
    }
}
}