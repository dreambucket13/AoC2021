import java.util.Arrays;

class cucumbers {


public static void main(String args[]){

    int[][] cucumberPosition = fileParse.parseToArray("C:\\Users\\carmi\\Documents\\git projects\\AoC2021day25\\cucumbersPart1.txt");
    int[][] cucumberMoves = new int[cucumberPosition.length][cucumberPosition[0].length];



    //east facing cukes (1) move first, then south (2)
    //cukes will wrap around

    int steps = 0;
    boolean moves = false;

    while(!moves){

        int row;
        int col;
        cucumberMoves = cucumberPosition.clone();
        //east (1) cukes
        for (row=0;row<cucumberPosition.length;row++){
            for (col=0;col<cucumberPosition[row].length;col++){
                if (cucumberPosition[row][col]==1){
                    if (col == cucumberPosition[row].length-1 ){
                        if (cucumberPosition[row][0]==0){
                            cucumberMoves[row][0] = cucumberPosition[row][col];
                            cucumberMoves[row][col] = 0;
                        }
                    }
                    else if (cucumberPosition[row][col+1]==0){
                        cucumberMoves[row][col+1] = cucumberPosition[row][col];
                        cucumberMoves[row][col] = 0;
                    }
                }
            }
        }

        //south (2) cukes
        for (row=0;row<cucumberPosition.length;row++){
            for (col=0;col<cucumberPosition[row].length;col++){
                if (cucumberPosition[row][col]==2){
                    if (row == cucumberPosition.length-1 ){
                        if (cucumberPosition[0][col]==0){
                            cucumberMoves[0][col] = cucumberPosition[row][col];
                            cucumberMoves[row][col] = 0;
                        }
                    }
                    else if (cucumberPosition[row+1][col]==0){
                        cucumberMoves[row+1][col] = cucumberPosition[row][col];
                        cucumberMoves[row][col] = 0;
                    }
                }
            }
        }        

        fileParse.printArray(cucumberPosition);
        System.out.println();
        fileParse.printArray(cucumberMoves);

        moves = Arrays.equals(cucumberPosition,cucumberMoves);
        cucumberPosition = cucumberMoves.clone();
        steps++;

    }

System.out.printf("%d steps needed.",steps);

}



}