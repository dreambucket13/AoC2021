//Chris Carmine
//Advent of code 2021 day 6

package day6;
import java.util.ArrayList;


import Fileparsing.*;


class aoc2021day6{

public static void main(String args[]){

    if (args[0] == null){
        System.out.printf("Cannot find file %s",args[0]);
        return;
    }

    if (args.length !=2){
        System.out.printf("Must supply days as second argument");
        return;
    }

    ArrayList<String> lines = new ArrayList<>();
    lines = fileParse.parseToArrayList(args[0]);
    int days = Integer.parseInt(args[1]);



    String[] initialTimers = lines.get(0).split(",");

    ArrayList<lanternfish> fishies = new ArrayList<>();

    for (int i=0; i<initialTimers.length;i++){
        fishies.add(new lanternfish(Integer.parseInt(initialTimers[i])));
    }

    int totalFishies = 0;

    //printList(fishies, 0);


   for (int day = 1; day <= days; day++){

        totalFishies = fishies.size();
        for (int i = 0; i<totalFishies;i++){
            lanternfish spawn = fishies.get(i).nextDay();
            if (spawn != null){
                fishies.add(spawn);
            }
            
        }
    //printList(fishies, day);
   }

   System.out.printf("Final number of fish: %d",fishies.size());

}//main

// private static void printList(ArrayList<lanternfish> list, int day){

//     if (day !=0){
//         System.out.printf("After %d day: ", day);
//     } else {
//         System.out.printf("Initial state: ");       
//     }


//     for (lanternfish f : list){
//         System.out.printf("%d, ", f.timer);
//     }

//     System.out.println();

// }



}//class