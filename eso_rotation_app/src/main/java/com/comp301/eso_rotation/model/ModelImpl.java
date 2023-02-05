package com.comp301.eso_rotation.model;

import com.comp301.eso_rotation.view.MoveBtn;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ModelImpl implements Runnable{
    private boolean isOn;
    private PriorityQueue<MoveBtn> pq;
    private double time;
    private boolean running;
    private Thread t;

    public ModelImpl(){
        running = false;
        isOn = false;
        Comparator<MoveBtn> compare = (a, b) -> Double.compare(a.getNextTime(), b.getNextTime());
        pq = new PriorityQueue<MoveBtn>(compare);
        time = 0;
        t = new Thread(this);

    }

    public void run(){
        System.out.println(running);
        System.out.println("model is running");
        if(running){
            System.out.println("already running. not starting");
        }
        running=true;
        while(running){
            MoveBtn move = pq.poll();
            double wait = move.getNextTime() - time;
            System.out.println("waiting for in seconds : " + wait);
            time += wait;
            move.increment();
            pq.add(move);
//            for (MoveBtn moveBtn : pq) {
//                System.out.println(moveBtn.getNextTime());
//            }
//            System.out.println(pq.toString());
            try {
                Thread.sleep((long) wait*1000);
            } catch (InterruptedException e) {
                System.out.println("Sleep was interrupted");
            }
            move.playAudio();
        }
        t = new Thread(this);
    }
    public void start(){t.start();}
    public void addBtn(MoveBtn btn){
        System.out.println(btn.getButton().getText() + " was added to queue");
        pq.add(btn);
    }
    public void removeBtn(MoveBtn btn){
        System.out.println(btn.getButton().getText() + " was removed to queue");
        pq.remove(btn);
    }
    public void stopRunning(){
        System.out.println("stopRunning was selected");
        running = false;
    }

}
