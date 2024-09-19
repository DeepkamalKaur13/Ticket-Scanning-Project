import javafx.scene.input.KeyCode;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Use this template to create Apps with Graphical User Interfaces.
 *
 * @author Deepkamal Kaur
 */
public class TicketScanningApp extends Application {

    // TODO: Instance Variables for View Components and Model
    private Table t;
    private Label ticketLabel;
    private Button redeemButton;
    private TextField userValue;
    private Label resultLabel;

    // TODO: Private Event Handlers and Helper Methods

    public void validCheck(ActionEvent e) {
        String userInputValue = userValue.getText();
        int rowIndex = t.lookup(userInputValue);
        if (userInputValue.toLowerCase().equals("reset")) {
            for (int r = 0; r < t.getNumRows(); r++) {
                t.change(r, 2, "N");
            }
            t.save();
            resultLabel.setText("All tickets have been reset.");
            resultLabel.setStyle("-fx-text-fill:green;");
            return;
        }

        if (rowIndex == -1) {
            resultLabel.setText("Invalid Ticket");
            resultLabel.setStyle("-fx-text-fill:red;");
        } else {
            if (t.grid[rowIndex][1].equals("N")) {
                resultLabel.setText(userInputValue + " is not purchased yet");
                resultLabel.setStyle("-fx-text-fill:red;");
            } else if (t.grid[rowIndex][2].equals("Y")) {
                resultLabel.setText(userInputValue + " is a duplicate");
                resultLabel.setStyle("-fx-text-fill:red;");
            } else {
                resultLabel.setText(userInputValue + " is a valid! Welcome");
                resultLabel.setStyle("-fx-text-fill:green;");
                t.change(rowIndex, 2, "Y");
            }
        }
        userValue.requestFocus();
    }

    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 225); // set the size here
        stage.setTitle("Ticket Scanning App"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model
        t = new Table("codes.txt");

        // 2. Create the GUI components
        ticketLabel = new Label("Ticket Scanning App");
        redeemButton = new Button("Redeem");
        userValue = new TextField();
        resultLabel = new Label();

        // 3. Add components to the root
        root.getChildren().addAll(ticketLabel, redeemButton, userValue, resultLabel);
        // 4. Configure the components (colors, fonts, size, location)
        ticketLabel.relocate(10, 20);
        ticketLabel.setFont(new Font("System", 40));
        ticketLabel.setStyle("-fx-alignment:center; -fx-text-fill:blue;");

        redeemButton.relocate(200, 100);
        redeemButton.setStyle("-fx-font-size:25;");

        userValue.relocate(50, 100);
        userValue.setFont(new Font("System", 25));
        userValue.setPrefWidth(130);

        resultLabel.relocate(50, 160);
        resultLabel.setFont(new Font("System", 25));



        // 5. Add Event Handlers and do final setup
        redeemButton.setOnAction(this::validCheck);
        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}