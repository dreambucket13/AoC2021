/*
Chris Carmine
AoC 2021 Day 6
*/

#include <stdio.h>
#include <assert.h>

#define discreteStartingTimers 6

__UINT64_TYPE__ multiply(int timer,int daysLeft);

int main() {

    __UINT64_TYPE__ grandTotal = 0;
    __UINT64_TYPE__ subtotal = 0;
    __UINT64_TYPE__ spawn[discreteStartingTimers] = { 0 };
    int initialTimer = 0;
    int daysLeft = 256;
    int startingFish[] = {1,2,5,1,1,4,1,5,5,5,3,4,1,2,2,5,3,5,1,3,4,1,5,2,5,1,4,1,2,2,1,5,1,1,1,2,4,3,4,2,2,4,5,4,1,2,3,5,3,4,1,1,2,2,1,3,3,2,3,2,1,2,2,3,1,1,2,5,1,2,1,1,3,1,1,5,5,4,1,1,5,1,4,3,5,1,3,3,1,1,5,2,1,2,4,4,5,5,4,4,5,4,3,5,5,1,3,5,2,4,1,1,2,2,2,4,1,2,1,5,1,3,1,1,1,2,1,2,2,1,3,3,5,3,4,2,1,5,2,1,4,1,1,5,1,1,5,4,4,1,4,2,3,5,2,5,5,2,2,4,4,1,1,1,4,4,1,3,5,4,2,5,5,4,4,2,2,3,2,1,3,4,1,5,1,4,5,2,4,5,1,3,4,1,4,3,3,1,1,3,2,1,5,5,3,1,1,2,4,5,3,1,1,1,2,5,2,4,5,1,3,2,4,5,5,1,2,3,4,4,1,4,1,1,3,3,5,1,2,5,1,2,5,4,1,1,3,2,1,1,1,3,5,1,3,2,4,3,5,4,1,1,5,3,4,2,3,1,1,4,2,1,2,2,1,1,4,3,1,1,3,5,2,1,3,2,1,1,1,2,1,1,5,1,1,2,5,1,1,4};
    printf("Starting...\n");

    for (initialTimer = 0; initialTimer < discreteStartingTimers; initialTimer++){

        //timers are plus 1 since 0 is a valid timer value
        subtotal = 1 + multiply(initialTimer+1,daysLeft);
        spawn[initialTimer] = subtotal;
        printf("initial timer %d: %lld\n",initialTimer,subtotal);
    
    }

    for (int i = 0; i < sizeof(startingFish)/sizeof(startingFish[0]); i++){

        grandTotal += spawn[startingFish[i]];
    }

    printf("Grand total: %lld\n",grandTotal);
    assert(grandTotal == 1617359101538);

    return 0;

} //main

__UINT64_TYPE__ multiply (int timer, int daysLeft){

    //base case
    if (timer>daysLeft){
        return 0;
    } else {
    //recursive case
    daysLeft -= timer;
    //timers are plus 1 since 0 is a valid timer value, so 7 is 6+1 and 9 is 8+1
    return 1 + multiply(7,daysLeft) + multiply(9,daysLeft);
    
    }

    
}

