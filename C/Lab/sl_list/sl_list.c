/* SYSC 2006 Lab 11  */

#include <assert.h>             // assert
#include <stdlib.h>             // malloc, free
#include <stdbool.h>
#include <stdio.h>              // printf

#include "sl_list.h"

/*************************************************************
 * Functions presented in lectures.
 *************************************************************/

/* Return a pointer to a new intnode_t (a node in a singly-linked list).

   Parameter value: the integer to be stored in the node.
   Parameter next: a pointer to the node that the new node should point to.
   This pointer should be NULL if the new node is the last node in the 
   linked list.
   Terminate (via assert) if memory for the node cannot be allocated.
 */
intnode_t *intnode_construct(int value, intnode_t *next)
{
    intnode_t *p = malloc(sizeof(intnode_t));
    assert(p != NULL);
    p->value = value;
    p->next = next;
    return p;
}
/* Parameter head points to the first node in a linked list, or is NULL
   if the linked list is empty. Insert a node containing the specified 
   integer value at the front of the linked list, and return a pointer to 
   the first node in the modified list.
 */
intnode_t *push(intnode_t *head, int value)
{
    return intnode_construct(value, head);
}

/* Parameter head points to the first node in the list.
   Return the length of a linked list (0 if the linked list is empty). 
 */
int length(intnode_t *head)
{
    int count = 0;
    for (intnode_t * current = head; current != NULL; current = current->next) {
        count += 1;
    }
    return count;
}

/* Parameter head points to the first node in a linked list.
   Print the linked list, using the format:
       value1 -> value2 -> value3 -> ... -> last_value
 */
void print_list(intnode_t *head)
{
    if (head == NULL) {
        printf("empty list");
        return;
    }

    intnode_t *current;

    /* Print every node in the linked list except the last one.

       Notice that the loop condition is:
       current->next != NULL
       and not:
       current != NULL

       During the last iteration, the value in the second-last node is
       printed, then current is updated to point to the last node. The
       condition current->next != NULL now evaluates to false, so the 
       loop exits, with current pointing to the last node (which has  
       not yet been printed.) 
     */
    for (current = head; current->next != NULL; current = current->next) {
        printf("%d -> ", current->value);
    }

    /* Print the last node. */
    printf("%d", current->value);
}


/*****************************************************************
 * Functions for Lab 11
 *****************************************************************/

// Exercise 1

intnode_t *add(intnode_t *head, int elem, int index)
{
    assert(index>=0||index<=length(head));
    //new node
    intnode_t *new_node = malloc(sizeof(intnode_t));
    assert(new_node!=NULL);
    new_node->value = elem;
    new_node->next = NULL;


    //push function*
    if (index == 0) {
        new_node->next = head;
        head = new_node;
        return head;
    }
    //add element anywhere except
    int i = 0;
    intnode_t *curr = head;
    while (index-1 != i) {
        curr=curr->next;
        i+=1;
    }
    new_node->next=curr->next;
    curr->next = new_node;
    return head;
}

// Exercise 2

void every_other(intnode_t *head)
{
    if (head == NULL){
        return;
    }
    intnode_t *curr = head;
    while(curr->next!=NULL)  {
        if (curr->next->next == NULL){
            intnode_t *delete = curr->next;
            curr->next = NULL;
            free(delete);
        }else if(curr->next !=NULL){
            intnode_t *delete = curr->next;
            curr->next = curr->next->next;
            free(delete);
            curr = curr->next;
        }
    }  
}

// Exercise 3

/* Return the count of the number of times target occurs in the 
 * linked list pointed to by head. 
 */
int count_in_sll(intnode_t *head, int target)
{   
    if (head == NULL){
        return 0;
    }else if (head->value == target) {
        return 1 + count_in_sll(head->next, target);
    }else{
        return count_in_sll(head->next, target);
    }
}

// Exercise 4

/* Return the last element in the linked list pointed to by head.
 * Terminate (via assert) if the list is empty.
 */
int last_in_sll(intnode_t *head)
{
    assert(head!=NULL);
    if (head->next == NULL){
        return head->value;
    }else{
        return last_in_sll(head->next);
    }
}
