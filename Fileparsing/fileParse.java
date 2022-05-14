/***************************************
 * Chris Carmine
 * helper class for Advent of code
***************************************/
package Fileparsing;

import java.io.*;
import java.util.ArrayList;


public class fileParse {

    public static ArrayList<String> parseToArrayList(String filePath){

        ArrayList<String> lines = new ArrayList<String>();
        File file=new File(filePath);
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while((line=br.readLine())!=null){
                lines.add(line);
            }
        } catch (IOException e) {
            return null;
        }

        return lines;
    }

    public static void printArray(int[][] array){

        for (int row=0;row<array.length;row++){
            for (int col=0;col<array[row].length;col++){
                System.out.printf("%d ",array[row][col]);
            }
            System.out.println();
        }

    }

    public static int[][] copyArray(int[][] array){

        int[][] newArray = new int[array.length][array[0].length];

        for (int row=0;row<array.length;row++){
            for (int col=0;col<array[row].length;col++){
                newArray[row][col] = array[row][col];
            }
        }

        return newArray;

    }

    public static int[] conv1D2D(int i, int[][] r){

        int row = i;
        int col = 0;
        int[][] twoD = r;
        int[] oneD = new int[twoD[row].length];

        for (col = 0; col < twoD[row].length; col ++){

            oneD[col] = twoD[row][col];

        }

        return oneD;

    }

}

