#ifndef __tokenize_H__
#define __tokenize_H__

//defines
#define MAX_LINE_LENGTH 100
#define DELIMITER_SPACE " "
#define DELIMITER_COMMA ","

//function prototypes
void linesToArray(char* filePath, char** lines);
void lineToTokens(char** delimiter, char** line, char** tokens);

#endif /* __tokenize_H__ */
