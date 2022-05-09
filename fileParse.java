/***************************************
 * Chris Carmine
 * https://adventofcode.com/2021/day/25
***************************************/
import java.io.*;
import java.util.ArrayList;

public class fileParse {

    public static int[][] parseToArray(String filePath){

        ArrayList<String> lines = new ArrayList<String>();
        File file=new File(filePath);
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while((line=br.readLine())!=null){
                lines.add(line);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }


        int rows = lines.size();
        int columns = lines.get(0).length();
        int ruleArray[][] = new int[rows][columns]; 

        for (String l : lines){
        
            for (int col=0;col<columns;col++){
 
                Character c = lines.get(lines.indexOf(l)).charAt(col);

                if (c.equals('.')){
                    ruleArray[lines.indexOf(l)][col] = 0;
                } else if (c.equals('>')){
                    ruleArray[lines.indexOf(l)][col] = 1;
                } else if (c.equals('v')){
                    ruleArray[lines.indexOf(l)][col] = 2;
                }
            }

        } //for loop


        return ruleArray;
    }

    public static void printArray(int[][] array){

        for (int row=0;row<array.length;row++){
            for (int col=0;col<array[row].length;col++){
                System.out.printf("%d ",array[row][col]);
            }
            System.out.println();
        }

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

