package fr.hugopabich.tictactoe;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Classe principale du jeu
 */
public class TicTacToe implements Runnable {


    @Getter
    private final Controller controller;
    @Getter
    private final View view;
    @Getter
    private final Model model;

    @Getter @Setter
    private boolean running;



    public TicTacToe(){
        Images.init();

        this.model = new Model(this);
        this.view = new View(this);
        this.controller = new Controller(this);

        this.view.createWindow();
        new Thread(this, "TicTacToe").start();
    }

    public void run() {
        running = true;
        while(running){
            view.clear();
            view.draw();
            try {
                Thread.sleep(17);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
