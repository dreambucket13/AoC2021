# the compiler: gcc for C program, define as g++ for C++
CC = gcc

# compiler flags:
#  -g    adds debugging information to the executable file
#  -Wall turns on most, but not all, compiler warnings
CFLAGS  = -Wall -g

  # the build target executable:
TARGET = day9
INCLUDE = tokenize
DIR := ${CURDIR}

#syntax is, in order to this: I need these up to date
#command is on next line with a tab (not 5 spaces!!)

all: $(TARGET)

$(TARGET): $(TARGET).c $(INCLUDE).o
	$(CC) -o $(TARGET) $(TARGET).c $(INCLUDE).o $(CFLAGS)

$(INCLUDE).o: $(INCLUDE).c
	$(CC) -c $(INCLUDE).c

#windows clean
clean:
	rm $(DIR)/$(TARGET).exe


