package com.comp301.eso_rotation.view;

import com.comp301.eso_rotation.model.ModelImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppLauncher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Rotation");
        ModelImpl model = new ModelImpl();
        VBox mainPane = new VBox();
        GridPane buttonGrid = new GridPane();
        MoveBtn arc_button = new MoveBtn(MoveBtn.Moves_Types.MoveTypes.ARCANIST,model);
        MoveBtn pot_button = new MoveBtn(MoveBtn.Moves_Types.MoveTypes.Pot,model);
        MoveBtn trap_button = new MoveBtn(MoveBtn.Moves_Types.MoveTypes.BarbedTrap,model);
        MoveBtn engulfing_button = new MoveBtn(MoveBtn.Moves_Types.MoveTypes.Engulfing,model);
        MoveBtn wall_button = new MoveBtn(MoveBtn.Moves_Types.MoveTypes.Blockade_Wall,model);
        MoveBtn off_button = new MoveBtn(MoveBtn.Moves_Types.MoveTypes.Off_Balance,model);
        buttonGrid.add(arc_button.getButton(),0,0);
        buttonGrid.add(trap_button.getButton(),1,0);
        buttonGrid.add(pot_button.getButton(),2,0);
        buttonGrid.add(engulfing_button.getButton(),3,0);
        buttonGrid.add(wall_button.getButton(),4,0);
        buttonGrid.add(off_button.getButton(),5,0);
        mainPane.getChildren().add(buttonGrid);
        HBox startStopView = new HBox();
        Label errorLabel = new Label();
        Button startBtn = new Button("START");
        startBtn.setOnMouseClicked((MouseEvent event) -> {
//            try {
                model.start();
//            } catch (Exception e) {
//                errorLabel.setText("Rotation already in progress");
//                return;
//            }
            errorLabel.setText("Rotation set");
        });
        Button stopBtn = new Button("STOP");
        stopBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                model.stopRunning();
            } catch (Exception e) {
                errorLabel.setText("Rotation already in progress");
                return;
            }
            errorLabel.setText("Rotation set");
        });
        startStopView.getChildren().add(errorLabel);
        startStopView.getChildren().add(startBtn);
        startStopView.getChildren().add(stopBtn);
        mainPane.getChildren().add(startStopView);
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.show();
    }
}
