package day2;
import java.util.ArrayList;

import Fileparsing.*;


class aoc2021day2{

public static void main(String args[]){

    ArrayList<String> lines = new ArrayList<>();

    lines = fileParse.parseToArrayList("C:\\Users\\carmi\\Documents\\git projects\\AoC2021\\day2\\day2data.txt");
    
    int rows = lines.size();
    int moveDistance;
    int horizontalDistance = 0;
    int verticalDistance = 0;
    int aim = 0; //part 2
    int depth = 0; //part2

    for (int i=0;i<rows;i++){
        String line = lines.get(i);
        //grab move distances from lines
        moveDistance=Integer.parseInt(line.substring(line.length()-1));
        
        //parse strings for direction
        if (line.substring(0,4).equals("down")){
            verticalDistance -= moveDistance; //part1
            aim+=moveDistance; //part2
        } else if (line.substring(0,2).equals("up")){
            verticalDistance+=moveDistance;
            aim-=moveDistance;
        } else if (line.substring(0,7).equals("forward")){
            horizontalDistance+=moveDistance;
            depth = depth + aim*moveDistance;
        }


    }


    System.out.printf("Part1: Horizontal * vertical: %d%n",horizontalDistance*Math.abs(verticalDistance));
    System.out.printf("Part2: Horizontal * depth: %d",horizontalDistance*depth);
}



}