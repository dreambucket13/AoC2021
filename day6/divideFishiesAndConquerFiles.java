//Chris Carmine
//Advent of code 2021 day 6 part 
//the idea is that each starting fish is independent, so I just need to calculate how many
//fish a fish with a given starting timer will spawn during the specified time.
//Then, use BigInteger class to add it add up.
//this works only up to ~230 days though, as even 1 fish will exceed the max VM size!
//next version will use files rather than RAM

package day6;

import java.util.ArrayList;
import Fileparsing.fileParse;
import java.math.BigInteger;
import java.io.*;

class divideFishiesAndConquerFiles{

public static void main(String args[]) throws IOException{

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



    String[] startingFish = lines.get(0).split(",");
    long[] totalFishies = new long[6];



//build array of number of fish a starting fish will create

for (byte initialTimer = (byte) 1; initialTimer < (byte) 6; initialTimer++){

    String fileName = "day6\\fish" + Integer.toString(initialTimer) + ".txt";

    try {
        FileWriter initialize = new FileWriter(fileName,false);
        byte initialFish = (byte) Integer.toString(initialTimer).charAt(0);
        initialize.write(initialFish);
        initialize.close();
    } catch (IOException e1) {
        e1.printStackTrace();
    }


    for (int day = 1; day <= days; day++){
        long fishToAdd = 0;
        byte i = 0;
    
            File fishies = new File(fileName);
            RandomAccessFile fishiesRA = new RandomAccessFile(fishies,"rw");
            File temp = new File("day6\\temp.txt");
            RandomAccessFile tempRA = new RandomAccessFile(temp, "rw");

            fishiesRA.seek(0);
            tempRA.seek(0);

            while (fishiesRA.getFilePointer()<fishiesRA.length()){
                i = fishiesRA.readByte();
                //I will mark the next char after 0 to be x to avoid minus sign
                byte buf = (byte)'e';
                switch (i){
                    case '8': 
                        buf = (byte) '7';
                        break;
                    case '7': 
                        buf  = (byte)'6';
                        break;
                    case '6': 
                        buf  = (byte)'5';
                        break;
                    case '5': 
                        buf  = (byte)'4';
                        break;
                    case '4': 
                        buf  = (byte)'3';
                        break;
                    case '3': 
                        buf  = (byte)'2';
                        break;
                    case '2': 
                        buf  = (byte)'1';
                        break;
                    case '1': 
                        buf = (byte)'0';
                        break;
                    case '0': 
                        // buf =(byte) 'x';
                        buf = (byte)'6';
                        fishToAdd++;
                        break;
                    case 'x': 
                        buf = (byte)'6';
                        fishToAdd++;
                        break;
                    default:
                        System.out.println("Case default?!?!?");
                        return;
                }

                tempRA.write(buf);
                //totalFishies[initialTimer]++;

            } // while for file read and copy

            //add in the extra fish
            while (fishToAdd > 0){
                tempRA.write((byte) '8');
                fishToAdd--;
                //totalFishies[initialTimer]++;
            }
            
            System.out.printf("Fish %d day: %d complete\n", (int) initialTimer, day);
            totalFishies[(int)initialTimer] = tempRA.length();
            tempRA.close();
            fishiesRA.close();
            fishies.delete();
            temp.renameTo(fishies);
            
    } // days for loop
        
} //initial timer for loop

for (int i =0; i<totalFishies.length;i++){
    System.out.printf("Fish with initial timer of %d will spawn %d fish\n",i,totalFishies[i]);
}


BigInteger grandTotal = new BigInteger("0");

for (int i = 0; i < startingFish.length; i++){

    BigInteger fish = new BigInteger(Long.toString(totalFishies[Integer.parseInt(startingFish[i])]));
    grandTotal = grandTotal.add(fish);
    //System.out.printf("Fish %s adds: %s\n",startingFish[i],grandTotal.toString());

}

System.out.printf("Grand total: %s\n",grandTotal.toString());



}//main



}//class