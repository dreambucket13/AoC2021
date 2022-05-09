/***************************************
 * Chris Carmine
 * helper class for custom parse rules for Advent of code
***************************************/

import java.util.ArrayList;

public class customParseRules{

public static int[][] rules2021day25(String filePath){

ArrayList<String> lines = new ArrayList<>();

lines = fileParse.parseToArrayList(filePath);

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

}