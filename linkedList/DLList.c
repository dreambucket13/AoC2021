//Chris Carmine
//Double Linked list for Aoc2021

#include <stdio.h> 
#include <stdlib.h>
#include "DLList.h" 

node* getPrev(node* n){
    return n->prev;
}

void setPrev(node* n, node* newPrevious){
    n->prev=newPrevious;
    return;
}

node* getNext(node* n){
    return n->next;
}

void setNext(node* n, node* newNext){
    n->next=newNext;
    return;
}

void setData(node*n, struct data* d){
    n->data = d;
    return;
}

struct data* getData(node*n){
    return n->data;
}

void setID(node*n, int i){
    n->id = i;
    return;
}

int getID(node*n){
    return n->id;
}

//insert a node at the end
 int insertNodeAtTail(struct data* d){

    struct node * newNode = malloc (sizeof (node) ) ; 

    //initialize newNode

    setData(newNode,d);
    setNext(newNode,NULL);
    setPrev(newNode,NULL);

    //if head is null we know it is the first element
    if (head_p==NULL){
        setID(newNode,0);
        head_p = newNode;
        tail_p = newNode;
        return(0);
    } 

    setPrev(newNode,tail_p);
    setNext(tail_p,newNode);
    setID(newNode,getID(getPrev(newNode))+1);
    tail_p = newNode;

    return(0);   
}

/*
//remove a node
int removeNode(int v){

struct node * n = tail_p;
    
    //iterate through list to find element
    while (n != NULL){

        if (n->data == v){

            if (n==tail_p && n==head_p){
                tail_p = NULL;
                head_p = NULL;
            }
            else if (n==tail_p){
                tail_p = n->prev;
                n->prev->next = NULL;            
            } else if (n==head_p){
                head_p = n->next; 
                n->next->prev = NULL;
            } else {
                n->prev->next = n->next;
                n->next->prev = n->prev;
            }

            free(n);//free the memory at n
            return(1); //return 1 if n is found
        }
        n=n->prev; //iterate through list
    }
    return(0); //return 0 if element not found
}
*/











