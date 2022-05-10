package day1;
import java.util.ArrayList;

import Fileparsing.*;


class aoc2021day1{

public static void main(String args[]){

    ArrayList<String> lines = new ArrayList<>();

    lines = fileParse.parseToArrayList("C:\\Users\\carmi\\Documents\\git projects\\AoC2021\\day1\\part1data.txt");
    
    int rows = lines.size();
    int data[] = new int[rows];

    for (int i=0;i<rows;i++){
        data[i]=Integer.parseInt(lines.get(i));
    }

    int larger = 0;
    int slidingLarger = 0;

    for (int i=1;i<rows;i++){

        int current = data[i];
        int previous = data[i-1];

        if (current>previous){
            larger++;
        }

    }

    for (int i=3;i<rows;i++){

        int current = data[i]+data[i-1]+data[i-2];
        int previous = data[i-1]+data[i-2]+data[i-3];

        if (current>previous){
            slidingLarger++;
        }

    }

    
    System.out.printf("%d larger measurements\n",larger);
    System.out.printf("%d sliding larger measurements\n",slidingLarger);
}



}