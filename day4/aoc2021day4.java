//Chris Carmine
//Advent of code 2021 day 4

package day4;
import java.util.ArrayList;


import Fileparsing.*;


class aoc2021day4{

public static void main(String args[]){

    ArrayList<String> lines = new ArrayList<>();

    lines = fileParse.parseToArrayList("C:\\Users\\carmi\\Documents\\git projects\\AoC2021\\day4\\day4test.txt");
    
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


    for (board b : boards){
        b.printBoard();
    }



}//main



}//class