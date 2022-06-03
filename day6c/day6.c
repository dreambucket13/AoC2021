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

int main() {

    int initialtimer = 0;

    int days = 18;

    unsigned long long sixes = 1;

    unsigned long long eights = 1;

    unsigned long long spawn[] = {0,0,0,0,0,0};

    int startingfish[] = {3,4,3,1,2};

    unsigned long long grandtotal = 0;

    //printf("%llu\n",sizeof(unsigned long long));

    

    for (initialtimer = 0; initialtimer < 6; initialtimer++){

    
        sixes = 1;
        eights = 1;

        int sixestimer = 0;
        int eightstimer = 0;

       
        for (int day = initialtimer; day < days; day++){

            if (sixestimer == 6) {
                eights = 2*sixes;
                eightstimer++;
                sixestimer = 0;
            } else if (eightstimer == 8) {
                sixes = eights;
                eightstimer = 0;
                sixestimer++;
            } else {
                sixestimer++;
                eightstimer++;
            }

        }

        spawn[initialtimer] = sixes+eights;

    }

   

    for (int i = 0; i < sizeof(startingfish)/sizeof(int); i++){

        grandtotal = grandtotal + spawn[startingfish[i]];

    }

    printf("Grand total: %llu\n", grandtotal);

    return 0;

}