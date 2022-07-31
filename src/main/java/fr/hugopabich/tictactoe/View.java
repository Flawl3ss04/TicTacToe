package fr.hugopabich.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class View {

    private static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final int WIDTH = 600, HEIGHT = 600;
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
     * Create the game window
     */
    public void createWindow(){
        screen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        frame = new JFrame("TicTacToe - Hugo Pabich");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setIconImage(Images.getImage("icon"));

        frame.setSize(WIDTH, HEIGHT);

        //centrer la fenêtre
        frame.setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setBackground(BACKGROUND_COLOR);
        canvas.setSize(WIDTH, HEIGHT);

        canvas.addMouseListener(tictactoe.getController());

        frame.add(canvas);
        frame.pack();

        //rendre visible la fenêtre
        frame.setVisible(true);

        canvas.requestFocus();
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
    }


    /**
     * Draws on screen
     */
    public void draw(){
        Graphics g = screen.getGraphics();

        g.drawImage(Images.getImage("tictactoe_board"), 0, 0, WIDTH, HEIGHT, null);

        byte[] board = tictactoe.getModel().getBoard();
        for(int i = 0; i < board.length; i++){
            if(board[i] != 0){
                BufferedImage image = Images.getImage("tictactoe_" + (board[i] == Model.USER_MARK ? "x" : "o"));
                int s = 168; //Nouvelle taille de l'image, adaptée au board.
                g.drawImage(image, i%3 * (s+30) + 18, i/3 * (s+30) + 18, s, s, null);
            }
        }

        if(tictactoe.getModel().isEnded()){
            byte winner = tictactoe.getModel().checkWin(tictactoe.getModel().getBoard());
            if(winner != 0){
                g.drawImage(Images.getImage("tictactoe_win"), 0, 0, WIDTH, HEIGHT, null);
                BufferedImage image = Images.getImage("tictactoe_" + (winner == Model.USER_MARK ? "x" : "o"));
                g.drawImage(image, (WIDTH-image.getWidth())/2, (HEIGHT-image.getHeight())/2, null);
            } else {
                g.drawImage(Images.getImage("tictactoe_tie"), 0, 0, WIDTH, HEIGHT, null);
            }
        }

        graphics.drawImage(screen, 0, 0, screen.getWidth(), screen.getHeight(), null);
        bufferStrategy.show();
    }

    /**
     * Clears the screen
     */
    public void clear() {
        Graphics g = screen.getGraphics();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

}
