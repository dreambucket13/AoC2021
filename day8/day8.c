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

int main() {

    FILE* input = fopen("day8test.txt","r");
    char line[MAX_LINE_LENGTH] = {0};
    unsigned int lineNum = 0;
    char* tokens[NUM_TOKENS];
    int uniques=0;


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
        
        for (int i = 11; i < NUM_TOKENS; i++){

            int length = strlen(tokens[i]);

            if (length == 2 || length == 3 || length == 4 || length == 7){
                uniques++;
                //printf("%s, length %d\n",tokens[i],length);
            }

        }

    /*part 2
    for each unique character, create array of possible connections and then remove impossible ones
    when comparing to the nominal connections for thay digit
    start with length == 4 i.e. the number 4.  8 doesn't give any information, since all segments are lit anyway.

    tokens 0 though 9 are inputs

    */
        // 0 = a, 6 = g
        int oneNominal[7] = {0,0,1,0,0,1,0}; //cf
        int fourNominal[7] = {0,1,1,1,0,1,0}; //bcdf
        //an so on...

        for (int i = 0; i < NUM_TOKENS; i++){
            tokens[tokenIndex] = NULL;
        }

   }

    fclose(input);
    printf("Part 1, num uniques: %d", uniques);



   return 0;
} //main





