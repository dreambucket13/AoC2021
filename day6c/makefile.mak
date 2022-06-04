# the compiler: gcc for C program, define as g++ for C++
CC = gcc

# compiler flags:
#  -g    adds debugging information to the executable file
#  -Wall turns on most, but not all, compiler warnings
CFLAGS  = -Wall -g

  # the build target executable:
TARGET = day6
TEST = day6test
DIR := ${CURDIR}

#syntax is, in order to this: I need these up to date
#command is on next line with a tab (not 5 spaces!!)

all: $(TARGET)

$(TARGET): $(TARGET).c 
	$(CC) -o $(TARGET) $(TARGET).c $(CFLAGS)

test: $(TEST)

$(TEST): $(TEST).c 
	$(CC) -o $(TEST) $(TEST).c $(CFLAGS)

#windows clean
clean:
	rm $(DIR)/*.exe


