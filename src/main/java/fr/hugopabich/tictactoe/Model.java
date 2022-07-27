package fr.hugopabich.tictactoe;

import lombok.Getter;

public class Model {

    public static final byte COMPUTER_MARK = 1;
    public static final byte USER_MARK = 2;

    // Each group of 3 numbers indicate a row,column,diagonal of the same mark, in other words, a win.
    private static final int[] WIN_SITUATIONS = {0, 1, 2, // XXX 000 000
                                                 3, 4, 5, // 000 XXX 000
                                                 6, 7, 8, // 000 000 XXX
                                                 0, 3, 6, // X00 X00 X00
                                                 1, 4, 7, // 0X0 0X0 0X0
                                                 2, 5, 8, // 00X 00X 00X
                                                 0, 4, 8, // X00 0X0 00X
                                                 2, 4, 6}; // 00X 0X0 X00

    @Getter
    private byte[] board; // 012 345 678 from left to right top to bottom

    public Model(){
        this(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 });
    }

    public Model(byte[] board){
        this.board = board;
    }

    /**
     * Checks if this position is free on the board.
     * @param position The position to check.
     * @return true if and only if the position if free on the board.
     */
    public boolean canPlaceMark(int position){
        return board[position] == 0;
    }

    /**
     * Changed the state of the board in the corresponding position to be on mark.
     * @param position The position to place the mark.
     * @param mark The mark to place.
     * @return true if the placement has succeeded.
     */
    public boolean placeMark(int position, byte mark){
        if(canPlaceMark(position)){
            this.board[position] = mark;
            return true;
        }
        return false;
    }

    /**
     * Checks if the board is full and the game ends on a tie.
     * @return true if and only if the board is not full.
     */
    public boolean checkTie() {
        for(byte b : board) if(b == 0) return false;
        return true;
    }

    /**
     * Checks for every possible win scenarios
     * @return true if and only if the game should end because of a win
     */
    public boolean checkWin() {
        for(int i = 0; i < WIN_SITUATIONS.length; i+= 3){
            if(board[WIN_SITUATIONS[i]] == board[WIN_SITUATIONS[i+1]] && board[WIN_SITUATIONS[i+1]] == board[WIN_SITUATIONS[i+2]]){
                return true;
            }
        }
        return false;
    }

}
