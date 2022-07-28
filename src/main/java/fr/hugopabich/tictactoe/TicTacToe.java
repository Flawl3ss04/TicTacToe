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
public class TicTacToe {


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

    }

    public void start(){
        running = true;
        this.view.createWindow();
    }

    public void stop(){
        running = false;
    }

}
