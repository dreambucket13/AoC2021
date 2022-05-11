//Chris Carmine
//Advent of code 2021 day 3

package day3;
import java.util.ArrayList;
import Fileparsing.*;


class aoc2021day3{

public static void main(String args[]){

    ArrayList<String> lines = new ArrayList<>();

    lines = fileParse.parseToArrayList("C:\\Users\\carmi\\Documents\\git projects\\AoC2021\\day3\\day3data.txt");
    
    int rows = lines.size();
    int columns = lines.get(0).length();
    int ones;
    int zeros;
    String gamma = "";
    String epsilon = "";
    int power;

    for (int col=0;col<columns;col++){
        ones = 0;
        zeros = 0;
            for (int i=0;i<rows;i++){
                char digit = lines.get(i).charAt(col);

                if (digit == '1'){
                    ones++;
                } else {
                    zeros++;
                }

            }

            if (ones>zeros){
                gamma = gamma + "1";
                epsilon = epsilon + "0";
            } else {
                gamma = gamma + "0";
                epsilon = epsilon + "1";
            }
    }

    power = Integer.parseInt(gamma,2)*Integer.parseInt(epsilon,2);
    System.out.printf("Power consumption: %d%n",power);

}



}