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

        for (int i = 0; i < NUM_TOKENS; i++){
            tokens[tokenIndex] = NULL;
        }

   }

    fclose(input);
    printf("Part 1, num uniques: %d", uniques);

    //part 2
    //for each character, create array of possible connections and then remove
    //start with length == 4 i.e. the number 8
    //as you progress, AND the results
    //tokens 0 though 9 are inputs

   return 0;
} //main





