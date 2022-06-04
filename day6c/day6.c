#include<stdio.h>

/*
Fish with initial timer of 0 will spawn 7 fish
Fish with initial timer of 1 will spawn 7 fish
Fish with initial timer of 2 will spawn 5 fish
Fish with initial timer of 3 will spawn 5 fish
Fish with initial timer of 4 will spawn 4 fish
Fish with initial timer of 5 will spawn 4 fish
Grand total: 26
*/

unsigned long long multiply(int,int);

int main() {

    unsigned long long grandtotal = 0;
    unsigned long long subtotal = 0;
    unsigned long long spawn[] = {0,0,0,0,0,0};
    int initialtimer = 0;
    int daysleft = 256;
    int startingfish[] = {1,2,5,1,1,4,1,5,5,5,3,4,1,2,2,5,3,5,1,3,4,1,5,2,5,1,4,1,2,2,1,5,1,1,1,2,4,3,4,2,2,4,5,4,1,2,3,5,3,4,1,1,2,2,1,3,3,2,3,2,1,2,2,3,1,1,2,5,1,2,1,1,3,1,1,5,5,4,1,1,5,1,4,3,5,1,3,3,1,1,5,2,1,2,4,4,5,5,4,4,5,4,3,5,5,1,3,5,2,4,1,1,2,2,2,4,1,2,1,5,1,3,1,1,1,2,1,2,2,1,3,3,5,3,4,2,1,5,2,1,4,1,1,5,1,1,5,4,4,1,4,2,3,5,2,5,5,2,2,4,4,1,1,1,4,4,1,3,5,4,2,5,5,4,4,2,2,3,2,1,3,4,1,5,1,4,5,2,4,5,1,3,4,1,4,3,3,1,1,3,2,1,5,5,3,1,1,2,4,5,3,1,1,1,2,5,2,4,5,1,3,2,4,5,5,1,2,3,4,4,1,4,1,1,3,3,5,1,2,5,1,2,5,4,1,1,3,2,1,1,1,3,5,1,3,2,4,3,5,4,1,1,5,3,4,2,3,1,1,4,2,1,2,2,1,1,4,3,1,1,3,5,2,1,3,2,1,1,1,2,1,1,5,1,1,2,5,1,1,4};
    printf("Starting...\n");

    for (initialtimer = 0; initialtimer < 6; initialtimer++){

        subtotal = 0;
        subtotal = 1 + multiply(initialtimer+1,daysleft);

        spawn[initialtimer] = subtotal;
        printf("initial timer %d: %lld\n",initialtimer,subtotal);
    
    }

    for (int i = 0; i < sizeof(startingfish)/sizeof(int); i++){

        grandtotal = grandtotal + spawn[startingfish[i]];
    }

    printf("Grand total: %lld\n",grandtotal);

    return 0;

} //main

unsigned long long multiply (int timer, int daysleft){

    //base case
    if (timer>daysleft){
        return 0;
    } else {
    //recursive case
    daysleft = daysleft - timer;
    return 1 + multiply(7,daysleft) + multiply(9,daysleft);
    
    }

    
}

