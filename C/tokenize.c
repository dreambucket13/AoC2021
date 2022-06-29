
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include "tokenize.h"

void linesToArray(char* filePath, char** lines) {

    FILE* input = fopen(filePath,"r");
    char line[MAX_LINE_LENGTH] = {'\0'};

    int lineCount = 0;

        while (fgets(line, MAX_LINE_LENGTH, input)) {   
            line[strcspn(line, "\n")] = 0; //gets rid of trailing newlines
            ++lineCount;

        }

        rewind(input);

        for (int i = 0; i < lineCount; ++i){
          while (fgets(line, MAX_LINE_LENGTH, input)) {   
                lines[i] = line;
            }          
        }

    fclose(input);
    return;

}

void lineToTokens(char** delimiter, char** line, char** tokens){

    int tokenIndex = 0;

    tokens[tokenIndex] = strtok(*line,*delimiter);

    while (tokens[tokenIndex] != NULL){
        ++tokenIndex;
        tokens[tokenIndex] = strtok(NULL,*delimiter);
    }

    return;

}