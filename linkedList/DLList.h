#ifndef __DLList_H__
#define __DLList_H__

//setting up basic structure of a node with a pointer to next and previous nodes
typedef struct 
 {  
    int id;
    struct data* data;
    node* next;
    node* prev;
 } node;

// head and tail pointers
    node* head_p;
    node* tail_p;

int insertNodeAtTail(struct data* d);
struct data* getData(node*n);

#endif /* __DLList_H__ */
