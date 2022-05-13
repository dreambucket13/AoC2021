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

    
    int[][] map = new int[10000][10000]; //I'll shrink this when I know the real size

    for (String l : lines){

        points = l.split(" -> ");

        point1 = points[0].split(",");
        point2 = points[1].split(",");

        x1 = Integer.parseInt(point1[0]);
        y1 = Integer.parseInt(point1[1]);
        x2 = Integer.parseInt(point2[0]);
        y2 = Integer.parseInt(point2[1]);


        //x2 is not always greater than x1!

        int rowIterator = 1;
        int colIterator = 1;

        if (x1>x2){
            colIterator = -1;
        }

        if (y1>y2){
            rowIterator = -1;
        }

        for (int col = x1; col!=x2; col=col+colIterator){
            for (int row = y1; row!=y2;row=row+rowIterator){
                map[row][col] = map[row][col] + 1;
                    if (map[row][col] > 1) 
                        dangerPoints++;
            }
        }
        



        //find max coordinates to build proper sized map
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

    }
        
    printMap(map,xMax,yMax);        




    

}//main

private static void printMap(int[][] map,int xMax, int yMax){

    for (int row = 0; row<=yMax; row++){
        for (int col = 0; col<=xMax; col++){
            if (map[row][col]==0){
                System.out.printf(". ");
            } else {
                System.out.printf("%d ",map[row][col]);
            }
        }
        System.out.println();
    }

}

}//class