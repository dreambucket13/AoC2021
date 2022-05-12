package day4;

import java.util.ArrayList;


public class board {

private int[][] board;
private int boardSize;
private int[][] playedSquares;

public board(ArrayList<String> lines){
    this.boardSize = lines.size();
    this.board = new int[boardSize][boardSize];
    this.playedSquares = new int[boardSize][boardSize];

    for (String l : lines){
        //if there is a leading space, remove it
        String trimmed = l.trim();
        String[] entries = trimmed.split("\\s+");

        for (int i = 0;i<this.boardSize;i++){
                this.board[lines.indexOf(l)][i] = Integer.parseInt(entries[i]);
        }
    }

    //initialize to 1, since the challenge asks to sum unplayed squares
    for(int row = 0; row < boardSize; row++){
        for (int col = 0; col < boardSize; col++){
                playedSquares[row][col]=1;
        }
    }

}

public int playNum(int move){

        for(int row = 0; row < boardSize; row++){
            for (int col = 0; col < boardSize; col++){
                if (board[row][col]==move){
                    playedSquares[row][col]=0;
                    if (isWinner()){
                        return move;
                    }
                }
            }
        }
    
    //return -1 for game with no winner
    return -1;
}

public int sumUnplayed(){

    int sumOfUnPlayedSquares = 0;
    for(int row = 0; row < boardSize; row++){
        for (int col = 0; col < boardSize; col++){
            sumOfUnPlayedSquares = sumOfUnPlayedSquares + board[row][col]*playedSquares[row][col];
        }
    }

    return sumOfUnPlayedSquares;
}

private boolean rowComplete(int row){
    int sum=0;
    boolean complete = false;
    for (int i=0;i<boardSize;i++){
        sum = sum + playedSquares[row][i];
    }

    if (sum==0){
        complete = true;
    }

    return complete;
}

private boolean colComplete(int col){
    int sum=0;
    boolean complete = false;
    for (int i=0;i<boardSize;i++){
        sum = sum + playedSquares[i][col];
    }

    if (sum==0){
        complete = true;
    }

    return complete;
}

public boolean isWinner(){

    boolean won = false;

    for (int row =0; row < boardSize; row++){
        if (rowComplete(row)){
            won = true;
        }
    }

    for (int col =0; col < boardSize; col++){
        if (colComplete(col)){
            won = true;
        }
    }

    return won;
}

public void printBoard(){

    for (int row = 0; row < this.boardSize; row++ ){
        for (int col = 0; col < this.boardSize; col++){
            System.out.printf("%d ",this.board[row][col]);
        }
        System.out.println();
    }
    System.out.println();
}

}