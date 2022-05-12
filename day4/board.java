package day4;

import java.util.ArrayList;

public class board {

private int[][] board;
private int boardSize;

public board(ArrayList<String> lines){
    this.boardSize = lines.size();
    this.board = new int[boardSize][boardSize];
    for (String l : lines){

        String[] rawEntries = l.split("\\s+");
        String[] entries = new String[rawEntries.length];
        //if there is a leading space, remove it
        if (rawEntries[0].equals("")){
                for (int i = 1; i < this.boardSize+1;i++){
                    entries[i-1] = rawEntries[i];
                }
            } else {
                for (int i = 0; i < this.boardSize;i++){
                    entries[i] = rawEntries[i];
                }
            }
        
        for (int i = 0;i<this.boardSize;i++){
                this.board[lines.indexOf(l)][i] = Integer.parseInt(entries[i]);
        }
    }
}

public int getSquare(int row, int col){
    return this.board[row][col];
}

public int[] getRow(int row){
    return this.board[row];
}

public int[] getCol(int col){
    int[] column = new int[this.boardSize];
    for (int i=0;i<this.boardSize;i++){
        column[i] = this.board[i][col];
    }
    return column;
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