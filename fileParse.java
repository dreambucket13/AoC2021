/***************************************
 * Chris Carmine
 * https://adventofcode.com/2020/day/7
***************************************/
import java.io.*;
import java.util.ArrayList;

public class fileParse {

    public static int[][] ruleGen(){
        ArrayList<String> bagNames = new ArrayList<String>();
        ArrayList<String> rules = new ArrayList<String>();
        File file=new File("C:\\Users\\carmi\\Documents\\git projects\\AoC2020day7\\rules.txt");
        String line;
        String[] splitLine;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while((line=br.readLine())!=null){

                splitLine = line.split(" bags contain ");
                //System.out.println(splitLine[0]);
                bagNames.add(splitLine[0]);
                rules.add(splitLine[1]);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int dimension = bagNames.size();
        int ruleArray[][] = new int[dimension][dimension]; 
        int bags=0;
        int spaces=0;
        int bagIDstart=0;
        int bagIDend=0;
        String bagID;
        boolean lineEnd = false;
        int bagIndex=0;
        String target = "shiny gold"; //someday avoid hardcoding target

        for (String e : bagNames){
        
            int i = 0;
            lineEnd = false;
            bags = 0;
            // System.out.println(bagNames.get(bagNames.indexOf(e)));
            // System.out.println(rules.get(bagNames.indexOf(e)));

            while (!lineEnd){
 
                Character c = rules.get(bagNames.indexOf(e)).charAt(i);

                if (Character.isDigit(c)){
                    spaces = 0;
                    bags = Character.getNumericValue(c);
                    //System.out.println(bags);
                } 
                
                if (Character.isSpaceChar(c)){

                    if (spaces == 0){
                        bagIDstart = i+1;
                        spaces++;
                    } else if (spaces == 2) {
                        bagIDend = i;
                        bagID = rules.get(bagNames.indexOf(e)).substring(bagIDstart,bagIDend);
                        bagIndex = bagNames.indexOf(bagID);
                        ruleArray[bagNames.indexOf(e)][bagIndex] = bags; 
                        spaces++;
                        //System.out.println(bagID);
                    } else {
                        spaces++;
                    }
                }

                if (c.equals('.')){
                    spaces = 0;
                    bags=0;
                    lineEnd = true;
                    //System.out.println("Line End");
                }

                i++;
            }




        } //for loop


        return ruleArray;
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

