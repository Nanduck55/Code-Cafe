package codecafe.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrintOrShowReceiptController {

    @FXML
    private Button showReceiptBtn;

    @FXML
    public void initialize() {

        showReceiptBtn.setOnAction(e -> {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/codecafe/view/showreceipt.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Receipt");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.centerOnScreen();

                stage.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
    }


    @FXML
    private Button next_btn;

    @FXML
    private void goNext() {

        try {

            // RESET ORDER DATA
            codecafe.model.OrderData.getInstance().clearData();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/codecafe/view/start_screen.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) next_btn.getScene().getWindow();

            stage.setScene(new Scene(root, 1920, 1080));
            stage.centerOnScreen();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}