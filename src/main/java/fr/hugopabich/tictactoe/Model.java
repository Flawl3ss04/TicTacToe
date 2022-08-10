package fr.hugopabich.tictactoe;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {


    //The score for the mark, 0 for tie, 1 for computer, -1 for user.
    private static final int[] MINIMAX_SCORES = new int[]{0,1,-1};
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

    private final TicTacToe tictactoe;
    @Getter
    private byte[] board; // 012 345 678 from left to right top to bottom

    @Getter
    private boolean ended;

    private Random random = new Random();


    public Model(TicTacToe tictactoe){
        this(tictactoe, new byte[9]);
    }

    public Model(TicTacToe tictactoe, byte[] board){
        this.tictactoe = tictactoe;
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

            if(checkEnd(board)){
                ended = true;
            }

            return true;
        }
        return false;
    }

    /**
     * Plays the next move for the computer using the minimax algorithm.
     */
    public void computerPlay(){
        //Reduces the difficulty by choosing a random move 20% of the time.
        if(random.nextFloat() < 0.2){
            List<Integer> moves = new ArrayList<>(9);
            for(int i = 0; i < board.length; i++) {
                if (canPlaceMark(i)) {
                    moves.add(i);
                }
            }
            this.board[moves.get(random.nextInt(moves.size()))] = COMPUTER_MARK;
            return;
        }

        int maxScore = Integer.MIN_VALUE;
        int bestMove = -1;
        byte[] clone = new byte[board.length];
        System.arraycopy(board, 0, clone, 0, board.length);
        for(int i = 0; i < board.length; i++){
            if(canPlaceMark(i)){
                clone[i] = COMPUTER_MARK;
                int score = minimax(clone, false);
                if(score > maxScore){
                    maxScore = score;
                    bestMove = i;
                }
                clone[i] = 0;
            }
        }

        this.board[bestMove] = COMPUTER_MARK;

        if(checkEnd(board)){
            ended = true;
        }
    }

    /**
     * Give the score of the current state of the board.
     * It implements the minimax algorithm.
     * @param board the board to check its score.
     * @param maximizingPlayer if the algorithm should check to maximize the result.
     * @return the score of the state of this board.
     */
    private int minimax(byte[] board, boolean maximizingPlayer){
        if(checkEnd(board)){
            return MINIMAX_SCORES[checkWin(board)];
        }

        if(maximizingPlayer){
            int maxScore = Integer.MIN_VALUE;
            for(int i = 0; i < board.length; i++){
                if(board[i] == 0){
                    board[i] = COMPUTER_MARK;
                    int score = minimax(board, false);
                    maxScore = Math.max(score, maxScore);
                    board[i] = 0;
                }
            }
            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            for(int i = 0; i < board.length; i++){
                if(board[i] == 0){
                    board[i] = USER_MARK;
                    int score = minimax(board, true);
                    minScore = Math.min(score, minScore);
                    board[i] = 0;
                }
            }
            return minScore;
        }
    }

    /**
     * Checks if the board is full and the game ends on a tie.
     * @return true if and only if the board is not full.
     */
    public boolean checkTie(byte[] board) {
        for(byte b : board) if(b == 0) return false;
        return true;
    }

    /**
     * Checks for every possible win scenarios.
     * @return The winner mark or 0.
     */
    public byte checkWin(byte[] board) {
        for(int i = 0; i < WIN_SITUATIONS.length; i+= 3){
            if(board[WIN_SITUATIONS[i]] == board[WIN_SITUATIONS[i+1]] && board[WIN_SITUATIONS[i+1]] == board[WIN_SITUATIONS[i+2]] && board[WIN_SITUATIONS[i]] != 0){
                return board[WIN_SITUATIONS[i]];
            }
        }
        return 0;
    }

    /**
     * Checks if the board is in a win or tie state.
     * @param board the board to check.
     * @return true if the board is in a win or tie state.
     */
    private boolean checkEnd(byte[] board){
        return checkWin(board) != 0 || checkTie(board);
    }

    public void restart(){
        ended = false;
        board = new byte[board.length];
    }

}
