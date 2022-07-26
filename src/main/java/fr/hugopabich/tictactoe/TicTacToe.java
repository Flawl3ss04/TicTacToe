package fr.hugopabich.tictactoe;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

/**
 * Classe principale du jeu
 */
public class TicTacToe extends Thread {

    private static final int WIDTH = 600, HEIGHT = 600;

    private JFrame frame;

    @Getter @Setter
    private boolean running;

    public TicTacToe(){
        createWindow();
    }

    /**
     * Créé la fenêtre du jeu.
     */
    public void createWindow(){
        frame = new JFrame("TicTacToe - Hugo Pabich");
        frame.setSize(WIDTH, HEIGHT);

        //centrer la fenêtre
        frame.setLocationRelativeTo(null);

        //rendre visible la fenêtre
        frame.setVisible(true);
    }

    @Override
    public void run() {
        running = true;
        while(running){

        }
    }
}
