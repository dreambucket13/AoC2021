class cucumbers {


public static void main(String args[]){

    int[][] cucumberPosition = fileParse.parseToArray("C:\\Users\\carmi\\Documents\\git projects\\AoC2021day25\\cucumbersPart2.txt");
    int[][] cucumberMoves;

    //east facing cukes (1) move first, then south (2)
    //cukes will wrap around

    int steps = 0;
    boolean moves = false;

    //while (steps<58){
    do {

        moves = false;
        int row;
        int col;
        cucumberMoves = fileParse.copyArray(cucumberPosition);

        //east (1) cukes
        for (row=0;row<cucumberPosition.length;row++){
            for (col=0;col<cucumberPosition[row].length;col++){
                if (cucumberPosition[row][col]==1){
                    if (col == cucumberPosition[row].length-1 ){
                        if (cucumberPosition[row][0]==0){
                            cucumberMoves[row][0] = cucumberPosition[row][col];
                            cucumberMoves[row][col] = 0;
                            moves = true;
                        }
                    }
                    else if (cucumberPosition[row][col+1]==0){
                        cucumberMoves[row][col+1] = cucumberPosition[row][col];
                        cucumberMoves[row][col] = 0;
                        moves = true;
                    }
                }
            }
        }

        //update positions for south cukes
        cucumberPosition = fileParse.copyArray(cucumberMoves);     

        //south (2) cukes
        for (row=0;row<cucumberPosition.length;row++){
            for (col=0;col<cucumberPosition[row].length;col++){
                if (cucumberPosition[row][col]==2){
                    if (row == cucumberPosition.length-1 ){
                        if (cucumberPosition[0][col]==0){
                            cucumberMoves[0][col] = cucumberPosition[row][col];
                            cucumberMoves[row][col] = 0;
                            moves = true;
                        }
                    }
                    else if (cucumberPosition[row+1][col]==0){
                        cucumberMoves[row+1][col] = cucumberPosition[row][col];
                        cucumberMoves[row][col] = 0;
                        moves = true;
                    }
                }
            }
        }        

        cucumberPosition = fileParse.copyArray(cucumberMoves); 
        steps++;
        System.out.printf("Step: %d\n",steps);

    } while(moves);


System.out.printf("%d steps needed.%n",steps);



}



}