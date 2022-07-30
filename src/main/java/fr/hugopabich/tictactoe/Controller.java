package fr.hugopabich.tictactoe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements MouseListener {

    private TicTacToe tictactoe;
    public Controller(TicTacToe tictactoe) {
        this.tictactoe = tictactoe;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int slotX = e.getX()*3/View.WIDTH;
        int slotY = e.getY()*3/View.HEIGHT;
        int position = 3*slotY + slotX;
        if(tictactoe.getModel().canPlaceMark(position)){
            tictactoe.getModel().placeMark(position, Model.USER_MARK);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
