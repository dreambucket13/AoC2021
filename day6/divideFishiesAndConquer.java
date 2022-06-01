//Chris Carmine
//Advent of code 2021 day 6 part 2
//the idea is that each starting fish is independent, so I just need to calculate how many
//fish a fish with a given starting timer will spawn during the specified time.
//Then, use BigInteger class to add it add up.
//this works only up to ~230 days though, as even 1 fish will exceed the max VM size!
//next version will use files rather than RAM

package day6;

import java.util.ArrayList;
import Fileparsing.fileParse;
import java.math.BigInteger;


class divideFishiesAndConquer{

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



String[] startingFish = lines.get(0).split(",");

long[] totalFishies = new long[6];



//build array of number of fish a starting fish will create

for (byte initialTimer = (byte)0; initialTimer < (byte)6; initialTimer++){
    //save as much memory as possible by using byte type
    byte[] fishies = new byte[2147483640]; //gonna have to write to file...
    fishies[0] = initialTimer;
    int lastFishIndex = 0;
    for (int day = 1; day <= days; day++){
        
        for (int i = 0; i<=lastFishIndex;i++){
            fishies[i] = (byte) (fishies[i]- 1);   
        }            
        
        for (int i = 0; i<=lastFishIndex;i++){
            if (fishies[i]< (byte) 0){
                fishies[i] = (byte) 6;
                if (lastFishIndex == Integer.MAX_VALUE-1){
                    System.out.println("Integer overflow");
                    return;
                }
                lastFishIndex++;
                fishies[lastFishIndex] = (byte) 8;
            }
        }
    }

    totalFishies[(int)initialTimer] = lastFishIndex+1;
    fishies = null;
}

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