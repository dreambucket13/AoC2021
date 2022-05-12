//Chris Carmine
//Advent of code 2021 day 3

package day3;
import java.util.ArrayList;
import java.util.Arrays;

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
    int lifeSupport;

    //part1

    for (int col=0;col<columns;col++){
        ones = 0;
        zeros = 0;
        int i;
            for (i=0;i<rows;i++){
                char digit = lines.get(i).charAt(col);

                if (digit == '1'){
                    ones++;
                } else {
                    zeros++;
                }

            }

            if (ones>=zeros){
                gamma = gamma + "1";
                epsilon = epsilon + "0";
            } else {
                gamma = gamma + "0";
                epsilon = epsilon + "1";
            }
    }

    
    power = Integer.parseInt(gamma,2)*Integer.parseInt(epsilon,2);
    System.out.printf("Power consumption: %d%n",power);

    //part2
    int[] oxygen = new int[lines.size()];
    int[] co2 = new int[lines.size()];

    //find o2 rating
    for (int col=0;col<columns;col++){
        ones = 0;
        zeros = 0;
        int i;

        //calculate # of ones and zeros in column
        for (i=0;i<rows;i++){

            char digit = lines.get(i).charAt(col);
            
            if (oxygen[i]==0){ //0 means line is still in play
                    if (digit == '1'){
                        ones++;
                    } else {
                        zeros++;
                    }
            }
        }

 
        for (String l : lines){
            //skip excluded lines
            if (oxygen[lines.indexOf(l)]==1) {
                continue;
            }
            //stop when only 1 line is left
            if (Arrays.stream(oxygen).sum() == rows-1){
                break;
            }

            if (ones>=zeros){
                if (l.charAt(col) == '0'){
                    oxygen[lines.indexOf(l)] = 1; //1 means I exclude
                } 
            } else {
                    if (l.charAt(col) == '1'){
                        oxygen[lines.indexOf(l)] = 1; //1 means I exclude     
                    }
            }
        }
    }
    
    //find co2 rating
    for (int col=0;col<columns;col++){
        ones = 0;
        zeros = 0;
        int i;

        //calculate # of ones and zeros in column
        for (i=0;i<rows;i++){

            char digit = lines.get(i).charAt(col);
            
            if (co2[i]==0){ //0 means line is still in play
                    if (digit == '1'){
                        ones++;
                    } else {
                        zeros++;
                    }
            }
        }

 
        for (String l : lines){
            //skip lines excluded before
            if (co2[lines.indexOf(l)]==1) {
                continue;
            }
            //stop when only 1 line remains
            if (Arrays.stream(co2).sum() == rows-1){
                break;
            }

            if (zeros<=ones){
                if (l.charAt(col) == '1'){
                    co2[lines.indexOf(l)] = 1; //1 means I exclude
                } 
            } else {
                if (l.charAt(col) == '0'){
                    co2[lines.indexOf(l)] = 1; //1 means I exclude     
                }
            }
        }
    }

    int o2index = -1;
    int co2index = -1;
    //now to find the last line, marked by the "0"
    for (int i = 0; i<oxygen.length;i++){
        if (oxygen[i] == 0) {
            o2index = i;
        } 

        if (co2[i] == 0){
            co2index = i;
        } 
    }

    lifeSupport = Integer.parseInt(lines.get(o2index),2)*Integer.parseInt(lines.get(co2index),2);
    System.out.printf("Life support rating: %d%n",lifeSupport);   

}//main



}//class