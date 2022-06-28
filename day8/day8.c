/*
Chris Carmine
AoC 2021 Day 8
*/

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>

#define MAX_LINE_LENGTH 100
#define NUM_TOKENS 15
#define SEGMENTS 7
#define DIGITS 10

typedef const int nominalArray[DIGITS][SEGMENTS];
typedef int signalArray[NUM_TOKENS][SEGMENTS];
typedef int connectionsArray[SEGMENTS][SEGMENTS];

//int (*pointer)[][] is a pointer to an array of ints. int *pointer[][] is an array of pointers to ints.
void printNominals(char* desc, int digit, nominalArray (*nominals) );
void printSegments(char* desc, int seg, connectionsArray (*possibleConnections) );
void signalsToArray(char* (*tokens)[NUM_TOKENS], signalArray (*signals) );
void rosetta(int signalIndex, int nominalIndex, nominalArray (*nominals), signalArray (*signals), connectionsArray (*possibleConnections) );
int areConnectionsValid(connectionsArray (*possibleConnections));
void copyConnections(connectionsArray (*source), connectionsArray (*destination));
int decode(int signal, connectionsArray (*possibleConnections), signalArray (*signals), nominalArray (*nominals));


int main(int argc, char** argv) {

    FILE* input = fopen("day8data.txt","r");
    char line[MAX_LINE_LENGTH] = {0};
    char* tokens[NUM_TOKENS];
    //part 1 output
    int uniques = 0;

    //part 2 output
    int total = 0;

    //index 0 = a, 6 = g
    const int nominals[DIGITS][SEGMENTS] = {
                                            {1,1,1,0,1,1,1}, //0 = abcefg
                                            {0,0,1,0,0,1,0}, //1 = cf
                                            {1,0,1,1,1,0,1}, //2 = acdeg
                                            {1,0,1,1,0,1,1}, //3 = acdfg
                                            {0,1,1,1,0,1,0}, //4 = bcdf
                                            {1,1,0,1,0,1,1}, //5 = abdfg
                                            {1,1,0,1,1,1,1}, //6 = abdefg
                                            {1,0,1,0,0,1,0}, //7 = acf
                                            {1,1,1,1,1,1,1}, //8 = abcdefg
                                            {1,1,1,1,0,1,1}  //9 = abcdfg
                                           };

    
    //rows are the segments. columns are possible connections
    connectionsArray possibleConnections;



    /* Get each line until there are none left */
    while (fgets(line, MAX_LINE_LENGTH, input))
    {   
        line[strcspn(line, "\n")] = 0; //gets rid of trailing newlines
        int tokenIndex = 0;
        tokens[tokenIndex] = strtok(line," ");

        while (tokens[tokenIndex] != NULL){
            tokenIndex++;
            tokens[tokenIndex] = strtok(NULL," ");
        }
        
        //part1
        for (int i = 11; i < NUM_TOKENS; i++){

            int length = strlen(tokens[i]);

            if (length == 2 || length == 3 || length == 4 || length == 7){
                uniques++;
            }

        }

    /*part 2
    for each unique character, create array of possible connections and then remove impossible ones
    when comparing to the nominal connections for that digit
    start with length == 4 i.e. the number 4.  8 doesn't give any information, since all segments are lit anyway.

    tokens 0 though 9 are inputs
    call rosetta for digits 1, 4, 7.  remove possible connections based on that.

    after those calls, there are only a few combinations left so I will start guessing.

    */

    int signals[NUM_TOKENS][SEGMENTS] = {0};

    //convert text signal tokens to int array 
    signalsToArray(&tokens,&signals);

    int oneIndex, fourIndex, sevenIndex, eightIndex;
    int length6s[3]= {0};
    int sixesIndex = 0;


    for (int i = 0; i < 10; i++ ){

        int length = strlen(tokens[i]);

        switch (length){
            case 4:
                fourIndex = i;
                break;
            case 3:
                sevenIndex = i;
                break;
            case 2: 
                oneIndex = i;
                break;
            case 7:
                eightIndex = i;
                break;
            case 5:
                break;
            case 6:
                length6s[sixesIndex] = i;
                sixesIndex++;
                break;
        }

    }

    //start out with all connections being possible (=1)
    for (int i = 0; i < SEGMENTS; i++){
        for (int j = 0; j < SEGMENTS; j++) {
            possibleConnections[i][j] = 1;
        }
    }

    //call rosetta on the known digits based on unique token length
    rosetta(fourIndex,4,&nominals,&signals, &possibleConnections);

    rosetta(sevenIndex,7,&nominals,&signals, &possibleConnections);
    
    rosetta(oneIndex,1,&nominals,&signals, &possibleConnections);

    rosetta(eightIndex,8,&nominals,&signals,&possibleConnections);

    //start guessing with the sixes
    sixesIndex = 0;
    //these are all of the possible combinations for the length 6 digits
    int sixesGuesses[6][3] =  { 
                                {0,6,9},
                                {0,9,6},
                                {6,0,9},
                                {6,9,0},
                                {9,0,6},
                                {9,6,0} 
                               };


    #ifdef printfs
    printf("Guessing...\n");
    #endif

    //0,9 1,6 2,0 guesses work, need to automate

    int guess = 0;
    int guessRow = 0;

    connectionsArray temp;
    //cache possible connections in case I need to roll back for bad guesses
    copyConnections(&possibleConnections,&temp);

    while ( guess < 3 && guessRow < 6){

        #ifdef guesses
        printf("guess: %d, guess row: %d\n",guess,guessRow);
        #endif
        rosetta(length6s[guess],sixesGuesses[guessRow][guess],&nominals,&signals,&possibleConnections);

        int connectionStatus = areConnectionsValid(&possibleConnections);

        #ifdef guesses
        printf("%d\n",connectionStatus);
        #endif

        if (connectionStatus == -1){
            //restore possible connections
            copyConnections(&temp,&possibleConnections);
            guessRow++;
            guess = 0;
            //cache again and continue on
            copyConnections(&possibleConnections,&temp);
            continue;

        } else if (connectionStatus == 1){
            break;
        }

        guess++;

    }

//decode

    int thousands = 1000*decode(11,&possibleConnections,&signals,&nominals);
    int hundreds = 100*decode(12,&possibleConnections,&signals,&nominals);
    int tens = 10*decode(13,&possibleConnections,&signals,&nominals);
    int ones = decode(14,&possibleConnections,&signals,&nominals);
    int lineTotal = (thousands + hundreds + tens + ones);
    total +=lineTotal;

    for (int i = 0; i < NUM_TOKENS; i++){
        tokens[i][0] = '\0';
    }

   } // END while (fgets(line, MAX_LINE_LENGTH, input))

    fclose(input);
    assert(uniques == 278);
    assert(total == 986179);
    printf("Part 1, num uniques: %d\n", uniques);
    printf("Part 2, total: %d\n", total);

   return 0;
} //main

void rosetta(int signalIndex, int nominalIndex, const int (*nominals) [DIGITS][SEGMENTS], int (*signals) [NUM_TOKENS][SEGMENTS], int (*possibleConnections) [SEGMENTS][SEGMENTS]){

    //iterate over signal.  where the value in the signal matches the nominal, that is a possible connection.

    //iterate over signal (a,b,c,d,e,f,g)
    for (int i = 0; i < SEGMENTS; i++){

        int signalValue = (*signals)[signalIndex][i];

        //iterate over nominals and zero out impossible connections
        for (int j = 0; j < SEGMENTS; j++){

            int nominalValue = (*nominals)[nominalIndex][j];

            if (signalValue != nominalValue){
                (*possibleConnections)[i][j] = 0;
            }

        }

    }

    #ifdef printfs
    printSegments("a actual positions: ",0,possibleConnections);
    printSegments("b actual positions: ",1,possibleConnections);
    printSegments("c actual positions: ",2,possibleConnections);  
    printSegments("d actual positions: ",3,possibleConnections);  
    printSegments("e actual positions: ",4,possibleConnections);
    printSegments("f actual positions: ",5,possibleConnections);
    printSegments("g actual positions: ",6,possibleConnections);    
    printf("\n");
    #endif

    return;
}


void printNominals(char* desc, int digit, const int (*nominals) [DIGITS][SEGMENTS]) {

    printf("%d %s: ", digit, desc);

    for (int i = 0; i <  SEGMENTS; i++){

        if ((*nominals)[digit][i] == 0 )
            continue;
            
        switch (i){
            case 0:
                printf("a");
                break;
            case 1:
                printf("b");
                break;
            case 2:
                printf("c");
                break;
            case 3:
                printf("d");
                break;
            case 4:
                printf("e");
                break;
            case 5:
                printf("f");
                break;
            case 6:
                printf("g");
                break;
        };
        
    }
        printf("\n");
    return;
}

void printSegments(char* desc, int seg, int (*possibleConnections) [SEGMENTS][SEGMENTS]) {

    printf("%s: ", desc);

    for (int i = 0; i <  SEGMENTS; i++){

        if ((*possibleConnections)[seg][i] == 0 )
            continue;
            
        switch (i){
            case 0:
                printf("a");
                break;
            case 1:
                printf("b");
                break;
            case 2:
                printf("c");
                break;
            case 3:
                printf("d");
                break;
            case 4:
                printf("e");
                break;
            case 5:
                printf("f");
                break;
            case 6:
                printf("g");
                break;
        };
        
    }
        printf("\n");
    return;
}

void signalsToArray(char* (*tokens)[NUM_TOKENS], int (*signals) [NUM_TOKENS][SEGMENTS] ){

        int tokenIndex;
        int charIndex;

    for (tokenIndex = 0; tokenIndex < NUM_TOKENS; ++tokenIndex){

        //skip the |
        if(tokenIndex == 10) continue;

        char* token = (*tokens)[tokenIndex];
        char c;

        for (charIndex = 0; charIndex < strlen(token); ++ charIndex){

        c = token[charIndex];

        switch (c){
            case 'a':
                (*signals)[tokenIndex][0] = 1;
                break;
            case 'b':
                (*signals)[tokenIndex][1] = 1;
                break;
            case 'c':
                (*signals)[tokenIndex][2] = 1;
                break;
            case 'd':
                (*signals)[tokenIndex][3] = 1;
                break;
            case 'e':
                (*signals)[tokenIndex][4] = 1;
                break;
            case 'f':
                (*signals)[tokenIndex][5] = 1;
                break;
            case 'g':
                (*signals)[tokenIndex][6] = 1;
                break;
        };
        }
        
    }

    return;
}

int areConnectionsValid(connectionsArray (*possibleConnections)){

    int rowCount[SEGMENTS] = {0};
    int colCount[SEGMENTS] = {0};
    int row;
    int col;
    int complete = 1;
    

    for (row = 0; row < SEGMENTS; ++row){

        for (col = 0; col < SEGMENTS; ++col){

            if ( (*possibleConnections)[row][col] != 0 ) rowCount[row]++;
        }

    }

    for (col = 0; col < SEGMENTS; ++col){

        for (row = 0; row < SEGMENTS; ++row){

            if ( (*possibleConnections)[row][col] != 0 ) colCount[col]++;
        }

    }

    for (int seg = 0; seg < SEGMENTS; ++seg){

        //if there is nothing in a row, the entire thing is invalid
        //TODO: find situtations where there are two segments pointing to the same connection - i.e. both b and c only pointing to g
        if (rowCount[seg] == 0) {
            return -1;
        }

        if (rowCount[seg] > 1 || colCount[seg] > 1){
            complete = 0;
        }
    }

    //if all checks are passed return 1, else if still valid but not complete return 0
    if (complete == 1){
        return 1;
    } else {
        return 0;
    }

}

void copyConnections(connectionsArray (*source), connectionsArray (*destination)){

    for (int row = 0; row < SEGMENTS; ++row){
        for (int col = 0; col < SEGMENTS; ++col){
            (*destination)[row][col] = (*source)[row][col];
        }
    }

    return;
}

int decode(int signal, connectionsArray (*possibleConnections), signalArray (*signals), nominalArray (*nominals)){

    //descramble signal

    // for (int row = 0; row < SEGMENTS; ++row){
    //     for (int col = 0; col < SEGMENTS; ++col){
    //         if ((*possibleConnections)[row][col] == 1 ){
    //             //swap the signals
    //             int temp = (*signals)[signal][row];
    //             (*signals)[signal][row] =  (*signals)[signal][col];
    //             (*signals)[signal][col] = temp;
    //             break;
                
    //         }
    //     }
    // }

    //keep track of segments I've corrected
    int swap[7] = {0};

    for (int seg = 0; seg < SEGMENTS; ++seg){

        if ((*signals)[signal][seg] == 1 ){

            int swapSeg = 0;

            //find the segment to swap with
            for (swapSeg = 0; swapSeg < SEGMENTS; ++swapSeg){
                if ((*possibleConnections)[seg][swapSeg] == 1){
                    break;
                }
            }

            swap[swapSeg] = 1;  
            
        }
    }

    //swap it in

    for (int i = 0; i < SEGMENTS; ++i){
        (*signals)[signal][i] = swap[i];
    }



    //find the nominal

    for (int digit = 0; digit < DIGITS; ++digit){
        
    int nominalFound = 1;

        for (int col = 0; col < SEGMENTS; ++col ){

            if ( (*nominals)[digit][col] != (*signals)[signal][col]){
                nominalFound = 0;
                break;
            }

        }

        if (nominalFound == 1)
            return digit;

    }

    return -1;

}


