package com.comp301.eso_rotation.view;
import com.comp301.eso_rotation.model.ModelImpl;
import com.comp301.eso_rotation.model.SimpleAudioPlayer;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class MoveBtn {
    private Button btn;
    private Moves_Types.MoveTypes type;
    private boolean isOn;
    private double nextTime;
    private double reset;
    private SimpleAudioPlayer audioPlayer;
    private ModelImpl model;

    public static class Moves_Types {
        public enum MoveTypes{
            ARCANIST,
            BarbedTrap,
            Pot,
            Engulfing,
            Off_Balance,
            Blockade_Wall
        }
    }
    public MoveBtn(Moves_Types.MoveTypes type,ModelImpl model) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.type = type;
        this.model = model;
        switch(type){
            case ARCANIST:
                btn = new Button("Select Arcanist");
                this.audioPlayer = new SimpleAudioPlayer("C:\\Users\\19196\\eso\\eso_rotation_app\\src\\main\\java\\com\\comp301\\eso_rotation\\wavs\\Arcanist.wav");
                reset = 17; // should be 17 seconds
                nextTime = reset;
                break;
            case Pot:
                this.audioPlayer = new SimpleAudioPlayer("C:\\Users\\19196\\eso\\eso_rotation_app\\src\\main\\java\\com\\comp301\\eso_rotation\\wavs\\Pot.wav");
                btn = new Button("Select Pot");
                reset = 47;
                nextTime = reset;
                break;
            case BarbedTrap:
                this.audioPlayer = new SimpleAudioPlayer("C:\\Users\\19196\\eso\\eso_rotation_app\\src\\main\\java\\com\\comp301\\eso_rotation\\wavs\\Barbed_Trap.wav");
                btn = new Button("Select BarbedTrap");
                reset = 20;
                nextTime = reset;
                break;
            case Engulfing:
                this.audioPlayer = new SimpleAudioPlayer("C:\\Users\\19196\\eso\\eso_rotation_app\\src\\main\\java\\com\\comp301\\eso_rotation\\wavs\\engulfing.wav");
                btn = new Button("Select Engulfing Flames");
                reset = 24;
                nextTime = reset;
                break;
            case Off_Balance:
                this.audioPlayer = new SimpleAudioPlayer("C:\\Users\\19196\\eso\\eso_rotation_app\\src\\main\\java\\com\\comp301\\eso_rotation\\wavs\\off__balance.wav");
                btn = new Button("Select Off Balance");
                reset = 20;
                nextTime = reset;
                break;
            case Blockade_Wall:
                this.audioPlayer = new SimpleAudioPlayer("C:\\Users\\19196\\eso\\eso_rotation_app\\src\\main\\java\\com\\comp301\\eso_rotation\\wavs\\wall.wav");
                btn = new Button("Select Blockade Wall");
                reset = 15;
                nextTime = reset;
                break;
        }
        btn.setOnMouseClicked((MouseEvent event) -> {
            isOn = !isOn;
            if (isOn) {
                btn.setStyle("-fx-background-color: green;");
                model.addBtn(this);
            } else {
                btn.setStyle("-fx-background-color: red;");
                model.removeBtn(this);
            }
        });
        isOn = false;
    }
    public Button getButton(){return btn;}
    public Moves_Types.MoveTypes getType(){return type;}
    public double getNextTime(){return nextTime;}
    public double getReset(){return reset;}
    public double increment(){
        nextTime += reset;
        return nextTime;
    }
    public void playAudio(){
        try {
            audioPlayer.resetAudioStream();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("unsupported audio");
        } catch (IOException e) {
            System.out.println("IO exception");
        } catch (LineUnavailableException e) {
            System.out.println("Line unavailable exception");
        } catch (InterruptedException e) {
            System.out.println("Interruption Exception");
        }
    }
}
