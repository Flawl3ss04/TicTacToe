package fr.hugopabich.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class View {

    private static final Color BACKGROUND_COLOR = Color.WHITE;
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
        screen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        frame = new JFrame("TicTacToe - Hugo Pabich");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);

        //centrer la fenêtre
        frame.setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setBackground(BACKGROUND_COLOR);
        canvas.setSize(WIDTH, HEIGHT);

        canvas.addMouseListener(tictactoe.getController());

        frame.add(canvas);

        //rendre visible la fenêtre
        frame.setVisible(true);

        canvas.requestFocus();
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
    }

    Random random = new Random();
    public void draw(){
        Graphics g = screen.getGraphics();

        g.setColor(Color.RED);
        g.fillRect(random.nextInt(450), random.nextInt(450), 50, 50);

        graphics.drawImage(screen, 0, 0, screen.getWidth(), screen.getHeight(), null);
        bufferStrategy.show();
    }

    /**
     * Nettoie la fenêtre
     */
    public void clear() {
        Graphics g = screen.getGraphics();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

}
