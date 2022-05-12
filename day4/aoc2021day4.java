//Chris Carmine
//Advent of code 2021 day 4

package day4;
import java.util.ArrayList;


import Fileparsing.*;


class aoc2021day4{

public static void main(String args[]){

    ArrayList<String> lines = new ArrayList<>();

    lines = fileParse.parseToArrayList("C:\\Users\\carmi\\Documents\\git projects\\AoC2021\\day4\\day4data.txt");
    
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

    int winningMove=-1;
    int sumUnplayed=0;
    int move = 0;

    while (winningMove == -1 && move<moves.length){
        for (board b : boards){
            winningMove = b.playNum(moves[move]);
            if (winningMove != -1){
                sumUnplayed=b.sumUnplayed();
                System.out.printf("Board %d winning move: %d, sum of unplayed numbers is %d%n",boards.indexOf(b)+1,winningMove,sumUnplayed);
                System.out.printf("Final score: %d",winningMove*sumUnplayed);
                break;
            }

        }
    
        if (winningMove!=-1){
            break;
        }
        move++;
    }



}//main



}//class