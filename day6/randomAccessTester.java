//Chris Carmine
//Advent of code 2021 day 6


package day6;

import java.io.*;

class randomAccessTester{

public static void main(String args[]) throws IOException{

    File test = new File("day6\\test.txt");

    File temp = new File("day6\\temp.txt");
    
    RandomAccessFile tempRA = new RandomAccessFile(temp, "rw");
    RandomAccessFile testRA = new RandomAccessFile(test, "rw");

    long fishToAdd = 0;
    byte i = 0;



        testRA.seek(0);
        tempRA.seek(0);

        while (testRA.getFilePointer()<testRA.length()){
            i = testRA.readByte();
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

        System.out.printf("Length: %d\n",tempRA.length());
        tempRA.close();
        testRA.close();
        test.delete();
        temp.renameTo(test);



}//main



}//class