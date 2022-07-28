package fr.hugopabich.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class View {

    private static final int WIDTH = 600, HEIGHT = 600;
    private final TicTacToe tictactoe;

    private JFrame frame;
    private BufferedImage screen;

    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    public View(TicTacToe tictactoe) {
        this.tictactoe = tictactoe;
    }

    /**
     * Crée la fenêtre du jeu.
     */
    public void createWindow(){
        frame = new JFrame("TicTacToe - Hugo Pabich");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        //centrer la fenêtre
        frame.setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setBackground(Color.BLACK);
        canvas.setSize(WIDTH, HEIGHT);

        //rendre visible la fenêtre
        frame.setVisible(true);

        canvas.addMouseListener(tictactoe.getController());

        frame.add(canvas);

        canvas.requestFocus();
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
    }

    public void update(){
        clear();

        graphics.drawImage(screen, 0, 0, screen.getWidth(), screen.getHeight(), null);
        bufferStrategy.show();
    }

    /**
     * Nettoie la fenêtre
     */
    public void clear() {
        Graphics g = screen.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

}
