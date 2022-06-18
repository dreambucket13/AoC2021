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


//use these to clean up later
typedef const int nominalArray[DIGITS][SEGMENTS];
typedef int signalArray[NUM_TOKENS][SEGMENTS];
typedef int connectionsArray[SEGMENTS][SEGMENTS];

//int (*pointer)[][] is a pointer to an array of ints. int *pointer[][] is an array of pointers to ints.
void printNominals(char* desc, int digit, const int (*nominals) [DIGITS][SEGMENTS]);
void printSegments(char* desc, int seg, int (*possibleConnections) [SEGMENTS][SEGMENTS]);
void signalsToArray(char* (*tokens)[NUM_TOKENS], int (*signals) [NUM_TOKENS][SEGMENTS] );
void rosetta(int signalIndex, int nominalIndex, const int (*nominals) [DIGITS][SEGMENTS], int (*signals) [NUM_TOKENS][SEGMENTS], int (*possibleConnections) [SEGMENTS][SEGMENTS]);

int main(int argc, char** argv) {

    FILE* input = fopen("day8test.txt","r");
    char line[MAX_LINE_LENGTH] = {0};
    char* tokens[NUM_TOKENS];
    int uniques=0;

    //index 0 = a, 6 = g
    const int nominals[DIGITS][SEGMENTS] = {
                                            {1,1,1,0,1,1,1}, //0 = abcefg
                                            {0,0,1,0,0,1,0}, //1 = cf
                                            {1,0,1,1,1,0,1}, //2 = acdeg
                                            {1,0,1,1,0,1,1}, //3 = acdfg
                                            {0,1,1,1,0,1,0}, //4 = bcdf
                                            {1,1,0,1,0,1,1}, //5 = abdfg
                                            {1,1,0,1,1,1,1}, //6 = abdefg
                                            {1,0,1,0,0,0,1}, //7 = acf
                                            {1,1,1,1,1,1,1}, //8 = abcdefg
                                            {1,1,1,1,0,1,1}  //9 = abcdfg
                                           };

    
    //rows are the segments. columns are possible connections
    int possibleConnections [SEGMENTS][SEGMENTS];

    //start out with all connections being possible (=1)
    for (int i = 0; i < SEGMENTS; i++){
        for (int j = 0; j < SEGMENTS; j++) {
            possibleConnections[i][j] = 1;
        }
    }

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

    */

    int signals[NUM_TOKENS][SEGMENTS] = {0};

    signalsToArray(&tokens,&signals);

    int oneIndex, fourIndex, sevenIndex;
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
        }

    }

    rosetta(fourIndex,4,&nominals,&signals, &possibleConnections);
    rosetta(sevenIndex,7,&nominals,&signals, &possibleConnections);
    rosetta(oneIndex,1,&nominals,&signals, &possibleConnections);


    printSegments("a possible connections: ",0,&possibleConnections);
    printSegments("b possible connections: ",1,&possibleConnections);
    printSegments("c possible connections: ",2,&possibleConnections);  
    printSegments("d possible connections: ",3,&possibleConnections);  
    printSegments("e possible connections: ",4,&possibleConnections);
    printSegments("f possible connections: ",5,&possibleConnections);
    printSegments("g possible connections: ",6,&possibleConnections);    

        for (int i = 0; i < NUM_TOKENS; i++){
            tokens[tokenIndex] = NULL;
        }

   } // END while (fgets(line, MAX_LINE_LENGTH, input))

    fclose(input);
    printf("Part 1, num uniques: %d\n", uniques);

   return 0;
} //main

void rosetta(int signalIndex, int nominalIndex, const int (*nominals) [DIGITS][SEGMENTS], int (*signals) [NUM_TOKENS][SEGMENTS], int (*possibleConnections) [SEGMENTS][SEGMENTS]){

    //call rosetta for digits 1, 4, 7.  remove possible connections based on that.
    //iterate over signal.  where the value in the signal matches the nominal, that is a possible connection.

    //iterate over signal
    for (int i = 0; i < SEGMENTS; i++){

        int signalValue = (*signals)[signalIndex][i];

        //iterate over nominals and zero out impossible connections
        for (int j = 0; j < SEGMENTS; j++){

            int nominalValue = (*nominals)[nominalIndex][j];

            if (signalValue != nominalValue){
                (*possibleConnections)[i][j] = 0;
            }

        }

        //Where a segment only has 1 connection possible, zero out the corresponding connection.
        //ex. b can only be connected to c, but c can be connected to b or d.  remove the d connection.

    }

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



