package codecafe.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class GoBackController {

    @FXML private Button noBtn;
    @FXML private Button yesBtn;

    private Stage dialogStage;
    private Stage mainStage;

    public void setStages(Stage dialogStage, Stage mainStage) {
        this.dialogStage = dialogStage;
        this.mainStage = mainStage;
    }

    @FXML
    private void initialize() {
        
        if (noBtn != null) {
            noBtn.setOnAction(e -> dialogStage.close());
        }

        // Go back to order_type.fxml when Yes is clicked
        if (yesBtn != null) {
            yesBtn.setOnAction(e -> {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/codecafe/view/order_type.fxml"));
                    Scene scene = new Scene(root, 1920, 1080);
                    mainStage.setScene(scene);
                    dialogStage.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }
}