package codecafe.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;

import codecafe.model.OrderData;

public class CheckoutController {

    @FXML
    private Button checkout_btn2;

    @FXML
    private Label order_TYPE;

    @FXML
    private VBox ordered_items_VBox2;

    @FXML
    private Label total_items_label2;

    @FXML
    private Label total_price_label2;

    @FXML
    private Button back_to_menu_btn;


    public void loadCheckoutData(HashMap<String, Node> orderedItemsMap, int totalItems, double totalPrice) {

        ordered_items_VBox2.getChildren().clear();

        for (Node item : orderedItemsMap.values()) {

            ordered_items_VBox2.getChildren().add(item);

            ImageView deleteBtn = (ImageView) item.lookup("#delete_ordered_item");

            if(deleteBtn != null){

                deleteBtn.setOnMouseClicked(e -> {

                    ordered_items_VBox2.getChildren().remove(item);

                    orderedItemsMap.entrySet().removeIf(entry -> entry.getValue() == item);

                });

            }

        }

        total_items_label2.setText("Total Item: " + totalItems);
        total_price_label2.setText("Total Price: ₱ " + String.format("%.2f", totalPrice));

        // SHOW DINE IN / TAKE OUT
        order_TYPE.setText(OrderData.getInstance().getOrderType());

    }

    @FXML
    private void goBackMenu() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/codecafe/view/menu.fxml"));
            Parent root = loader.load();

            MenuController controller = loader.getController();

            OrderData data = OrderData.getInstance();
            controller.restoreOrders(
                    data.getOrderMap(),
                    data.getTotalItems(),
                    data.getTotalPrice()
            );

            Stage stage = (Stage) back_to_menu_btn.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void initialize() {
        // Handle checkout button click
        checkout_btn2.setOnAction(e -> printMissUReceipt());
    }


    // //Print Receipt
    // private void printAndNextScene() {
    //     try {
    //         // 1. Create a printer job
    //         javafx.print.PrinterJob job = javafx.print.PrinterJob.createPrinterJob();

    //         if (job != null && job.showPrintDialog(checkout_btn2.getScene().getWindow())) {
    //             // 2. Print the VBox containing ordered items
    //             boolean printed = job.printPage(ordered_items_VBox2);

    //             if (printed) {
    //                 job.endJob();
    //             } else {
    //                 System.out.println("Printing failed.");
    //             }
    //         }

    //         // 3. Navigate to print_or_savePDF.fxml
    //         FXMLLoader loader = new FXMLLoader(getClass().getResource("/codecafe/view/print_or_savePDF.fxml"));
    //         Parent root = loader.load();

    //         Stage stage = (Stage) checkout_btn2.getScene().getWindow();
    //         Scene scene = new Scene(root);
    //         stage.setScene(scene);

    //     } catch (Exception ex) {
    //         ex.printStackTrace();
    //     }
    // }


    @FXML
    private void printMissUReceipt() {
        try {
            VBox receipt = new VBox(10);
            receipt.setStyle("-fx-padding: 20; -fx-font-size: 24; -fx-font-family: 'Arial'; -fx-alignment: center;");

            Label missU = new Label("MISSSS UUUU");
            missU.setStyle("-fx-font-size: 48; -fx-font-weight: bold; -fx-text-fill: #6B3F2A;");

            receipt.getChildren().add(missU);

            javafx.print.PrinterJob job = javafx.print.PrinterJob.createPrinterJob();
            if (job != null && job.showPrintDialog(checkout_btn2.getScene().getWindow())) {
                boolean success = job.printPage(receipt);
                if (success) job.endJob();
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/codecafe/view/print_or_savePDF.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) checkout_btn2.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}