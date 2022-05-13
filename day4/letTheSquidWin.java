//Chris Carmine
//Advent of code 2021 day 4 part 2

package day4;
import java.util.ArrayList;
import java.util.Arrays;

import Fileparsing.*;


class letTheSquidWin{

public static void main(String args[]){

    ArrayList<String> lines = new ArrayList<>();

    lines = fileParse.parseToArrayList(args[0]);
    
    //build moves array
    String[] result = lines.get(0).split(",");
    int[] moves = new int[result.length];
    for (int x=0; x<result.length; x++)
        moves[x]=Integer.parseInt(result[x]);

    //build boards

    int row = 2;
    ArrayList<board> boards = new ArrayList<>();

    try {
        while (!lines.get(row).isEmpty()){
                ArrayList<String> boardLines = new ArrayList<>(lines.subList(row, row+5));
                boards.add(new board(boardLines));
                row=row+6;
            }
    } catch (IndexOutOfBoundsException e){
        
    }

    //play the games
    int winningMove=-1;
    int sumUnplayed=0;
    int move = 0;
    int[] boardsWon = new int [boards.size()];
    int lastBoard = 0;


    while (move<moves.length){
        for (board b : boards){
            if (boardsWon[boards.indexOf(b)]==0){
                winningMove = b.playNum(moves[move]);
            }

            if (winningMove!=-1){
                boardsWon[boards.indexOf(b)]=1;
            }

            if (Arrays.stream(boardsWon).sum() == boardsWon.length){
                lastBoard = boards.indexOf(b);
                sumUnplayed = b.sumUnplayed();
                break;
            }

        }

        if (Arrays.stream(boardsWon).sum() == boardsWon.length){
            break;
        }       

        move++;
    }

    System.out.printf("Last board won: %d, winning move: %d, sum unplayed: %d%n",lastBoard+1,winningMove,sumUnplayed);
    System.out.printf("The score is: %d",winningMove*sumUnplayed);

}//main



}//class