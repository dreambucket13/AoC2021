//Chris Carmine
//Advent of code 2021 day 5

package day5;
import java.util.ArrayList;


import Fileparsing.*;


class aoc2021day5{

public static void main(String args[]){

    ArrayList<String> lines = new ArrayList<>();

    lines = fileParse.parseToArrayList(args[0]);
    String[] points;
    String[] point1;
    String[] point2;
    
    int x1;
    int x2;
    int y1;
    int y2;
    int xMax = 0;
    int yMax = 0;
    int dangerPoints = 0;

    //build map
    int[][] map = new int[1000][1000]; //assume this is a reasonable size

    for (String l : lines){

        points = l.split(" -> ");

        point1 = points[0].split(",");
        point2 = points[1].split(",");

        x1 = Integer.parseInt(point1[0]);
        y1 = Integer.parseInt(point1[1]);
        x2 = Integer.parseInt(point2[0]);
        y2 = Integer.parseInt(point2[1]);

        // for part1, only consider non-diagonal lines
        // if (!(x1 == x2 || y1 == y2)){
        //     continue;
        // }

        //find max coordinates to print a reasonable sized map
        if (x1>xMax){
            xMax = x1;
        } if (x2>xMax){
            xMax = x2;
        }

        if (y1>yMax){
            yMax = y1;
        } else if (y2>yMax){
            yMax = y2;
        }

        //x2 is not always greater than x1! so, I have to decide direction.
        int rowIterator;
        int colIterator;

        if (x1>x2){
            colIterator = -1;
        } else if (x1<x2){
            colIterator = 1;
        } else {
            colIterator = 0;
        }

        if (y1>y2){
            rowIterator = -1;
        } else if (y1<y2){
            rowIterator = 1;
        } else {
            rowIterator = 0;
        }

        int col = x1;
        int row = y1;

        while (!(col == x2+colIterator && row == y2+rowIterator)) {

            map[row][col] = map[row][col] + 1;
                if (map[row][col] == 2) //must be exactly 2 to avoid double counting danger points
                    dangerPoints++;
            
            row = row+rowIterator;
            col = col+colIterator;
        } 
    }
 
    System.out.printf("Danger points: %d\n", dangerPoints);

}//main

private static void printMap(int[][] map,int xMax, int yMax){

    for (int row = 0; row<yMax; row++){
        for (int col = 0; col<xMax; col++){
            if (map[row][col]==0){
                System.out.printf(". ");
            } else {
                System.out.printf("%d ",map[row][col]);
            }
        }
        System.out.println();
    }
    System.out.println("--------------------------------------------");
}

}//class