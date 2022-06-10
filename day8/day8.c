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

//int (*pointer)[][] is a pointer to an array of ints. int *pointer[][] is an array of pointers to ints.
void printNominals(char* desc, int digit, int (*nominals) [DIGITS][SEGMENTS]);
void rosetta(int digit, int (*nominals) [DIGITS][SEGMENTS], char** tokens[NUM_TOKENS], int (*possibleConnections) [SEGMENTS][SEGMENTS]);

int main() {

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

    //start out with all possible connections
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




        

        for (int i = 0; i < NUM_TOKENS; i++){
            tokens[tokenIndex] = NULL;
        }

   } //while

    fclose(input);
    printf("Part 1, num uniques: %d\n", uniques);

                            
    printNominals("nominal",4,&nominals);

   return 0;
} //main

void rosetta(int digit, int (*nominals) [DIGITS][SEGMENTS], char** tokens[NUM_TOKENS], int (*possibleConnections) [SEGMENTS][SEGMENTS]){

    //find the token that matches the length of the nominal digit, then remove possible connections based on that
    return;
}

void printNominals(char* desc, int digit, int (*nominals) [DIGITS][SEGMENTS]) {

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





