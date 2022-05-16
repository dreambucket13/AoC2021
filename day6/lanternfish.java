//Chris Carmine
//Advent of code 2021 day 6

package day6;
public class lanternfish{


public int timer;

public lanternfish(){
    this.timer = 8;
}

public lanternfish(int t){
    this.timer = t;
}

public lanternfish nextDay(){

    timer = timer-1;

    if (timer < 0){
        timer = 6;
        return new lanternfish();
    } else {
        return null;
    }

}

}//class