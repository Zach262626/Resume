/********* definitions.c ********
    Student Name 	=Zachary Gallant
    Student Number	=101272210
*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "a2_nodes.h"
#include "a2_functions.h"

// Your solution goes here

/*
   Function that creates a new user and adds it to a sorted (ascending order) linked list at
   the proper sorted location. Return the head of the list.
*/
user_t *add_user(user_t *users, const char *username, const char *password)
{
      //create new user (with username and password)
   user_t *new_user = malloc(sizeof(user_t));
   strcpy(new_user->username, username);
   strcpy(new_user->password, password); 
   new_user->next = NULL;
   new_user->friends = NULL;
   new_user->posts = NULL;


   user_t *prev = NULL;
   user_t *curr = users;
   //finds the index location
   while(curr != NULL && strcmp(username,curr->username)>=0) {
      prev = curr;
      curr = curr->next;
   }
   if(curr == NULL && prev == NULL){
      users = new_user;
   }else if (curr == NULL && prev!=NULL) {
      prev->next = new_user;
   }else if(prev == NULL && curr!=NULL){
      new_user->next = curr;
      users = new_user;
   }else{
      prev->next = new_user;
      new_user->next = curr;
   }
   return users;
}
/*
   Function that searches if the user is available in the database
   Return a pointer to the user if found and NULL if not found.
*/
user_t *find_user(user_t *users, const char *username)
{
   user_t *curr = users;

   while(curr!=NULL) {
      if (strcmp(curr->username,username)==0) {
         return curr;
      }
      curr = curr->next;
   }
   return NULL;
}
/*
   Function that creates a new friend's node.
   Return the newly created node.
*/
friend_t *create_friend(const char *username)
{
   friend_t *new_friend = malloc(sizeof(friend_t));
   strcpy(new_friend->username, username);
   new_friend->next = NULL;  
   return new_friend;
}

/*
   Function that links a friend to a user. The friend's name should be added into
   a sorted (ascending order) linked list.
*/
void add_friend(user_t *user, const char *friend)
{
   friend_t *new_friend = create_friend(friend);
   friend_t *curr = user->friends;
   friend_t *prev = NULL;
   if (curr == NULL) {
      user->friends = new_friend;
   }
   while(curr!=NULL && strcmp(curr->username, friend) <= 0) {
      prev = curr;
      curr = curr->next;
   }
   if (curr!=NULL && prev==NULL){
      new_friend->next = curr;
      user->friends = new_friend;
   }else if(prev!=NULL && curr!=NULL) {
      prev->next = new_friend;
      new_friend->next = curr;
   }else if (curr == NULL && prev!=NULL) {
      prev->next = new_friend;
   }else{
      new_friend->next = curr;
      curr = new_friend;
   }

   


}

/*
   Function that removes a friend from a user's friend list.
   Return true of the friend was deleted and false otherwise.
*/
_Bool delete_friend(user_t *user, char *friend_name)
{
   friend_t *prev = NULL;
   friend_t *curr = user->friends;
   if (curr == NULL){
      return 0;
   }
   while(curr!=NULL && strcmp(curr->username, friend_name) != 0) {
      prev = curr;
      curr = curr->next;
   }
   if (prev==NULL){
      user->friends = curr->next;
      return 1;
   }else if(curr == NULL){
      return 0;
   }else{
      prev->next = curr->next;
      free(curr);
      return 1;
   }
}
/*
   Function that creates a new user's post.
   Return the newly created post.
*/
post_t *create_post(const char *text)
{
   post_t *new_post = malloc(sizeof(post_t));
   strcpy(new_post->content,text);
   new_post->next = NULL;

   return new_post;
}

/*
   Function that adds a post to a user's timeline. New posts should be added following
   the stack convention (LIFO) (i.e., to the beginning of the Posts linked list).
*/
void add_post(user_t *user, const char *text)
{
   post_t *new_post = create_post(text);
   if (user->posts == NULL){
      user->posts = new_post;
   }else {
      new_post->next = user->posts;
      user->posts = new_post;
   }
}

/*
   Function that removes a post from a user's list of posts.
   Return true if the post was deleted and false otherwise.
*/
_Bool delete_post(user_t *user, int number)
{
   post_t *curr = user->posts;
   post_t *prev;
   unsigned short int i = number;
   while(curr!=NULL && i > 1){
      prev = curr;
      curr = curr->next;
      i = i-1;
   }
   if (curr!=NULL && number == 1){
      user->posts = user->posts->next;
      free(curr);
      return 1;     
   }else if(curr!=NULL){
      prev->next = curr->next;
      free(curr);
      return 1;
   }else {
      return 0;
   }


}

/*
   Function that  displays a specific user's posts
*/
void display_user_posts(user_t *user)
{
   printf("-----------------------------------------------\n");
   printf("            %s posts\n",user->username);
   unsigned short int i= 0;
   if (user!=NULL){
      post_t *curr = user->posts;
      if (curr == NULL){
         printf("No posts available for %s.\n", user->username);
      }
      while (curr!=NULL) {
      i = i + 1;
      printf("%d- ", i);
      printf("%s:", user->username);
      printf("%s\n", curr->content);
      curr = curr->next;
      }
   }else{
      printf("             User not found.\n");
   }
   printf("-----------------------------------------------\n\n");



}

/*
   Function that displays a specific user's friends
*/
void display_user_friends(user_t *user)
{
   friend_t *curr = user->friends;
   unsigned short int count = 1;
   if (curr==NULL){
      printf("No friends available for %s.\n", user->username);
   }
   while (curr!=NULL){
      printf("%hu", count);
      printf("- %s\n", curr->username);
      count+=1;
      curr = curr->next;
   }  
}
/*
   Function that displays all the posts of 2 users at a time from the database.
   After displaying 2 users' posts, it prompts if you want to display
   posts of the next 2 users.
   If there are no more post or the user types “n” or “N”, the function returns.
*/
void display_all_posts(user_t* users) {
   user_t *curr_user = users;
   char nory[] = "";
   unsigned short int end_two = 0;

   while (curr_user!=NULL) {
      post_t *curr_post = curr_user->posts;
      unsigned short int i= 1;
      if (curr_post == NULL) {
         printf("No posts available for %s.\n", curr_user->username);
      }

      while (curr_post!=NULL) {
         printf("%hu- %s: %s\n", i, curr_user->username, curr_post->content);
         curr_post = curr_post->next;
         i = i+1;
      }
      curr_user = curr_user->next;
      end_two = end_two + 1;
      if(end_two == 2 && curr_user!=NULL) {
         while(strcmp(nory, "y") != 0 && strcmp(nory, "Y") != 0){
               printf("\n\nDo you want to display next 2 users posts? (Y/N): ");
               scanf("%s", &nory);
               if (strcmp(nory, "n") == 0 || strcmp(nory, "N") == 0) {
                  printf("\n");
                  return;
               }else if(strcmp(nory, "y") != 0 && strcmp(nory, "Y") != 0) {
                  printf("Invalid input!\n\n");
               }else {
                  end_two = end_two - 2;
                  printf("\n");
               }
         }
      }
   }
   printf("\nall posts have been displayed\n\n");
}

/*
   Fucntion that free all users from the database before quitting the application.
*/
void teardown(user_t *users)
{
   user_t *delete_user;
   user_t *curr = users;
   while (curr!= NULL) {
      //delete user's Friends
      friend_t *delete_friend;
      friend_t *curr_friend = curr->friends;
      while (curr_friend != NULL){
         delete_friend = curr_friend;
         curr_friend = curr_friend->next;
         free(delete_friend);
      }
      
      //Delete user's post
      post_t *delete_post;
      post_t *curr_post = curr->posts;
      while (curr_post!=NULL){
         delete_post = curr_post;
         curr_post = curr_post->next;
         free(delete_post);
      } 

      //Delete current user
      delete_user=curr;
      curr = curr->next;
      free(delete_user);
   }
}

/*
   Function that prints the main menu with a list of options for the user to choose from
*/
void print_menu()
{
   printf("***********************************************\n");
   printf("             MAIN MENU:                        \n");
   printf("***********************************************\n");
   printf("1. Register a new User\n");
   printf("2. Manage a user's profile (change password)\n");
   printf("3. Manage a user's posts (display/add/remove)\n");
   printf("4. Manage a user's friends (display/add/remove)\n");
   printf("5. Display All Posts\n");
   printf("6. Exit\n\n");


}
/*
   Function that prints the friend menu with a list of options for the user to choose from
*/

void print_friend_menu(user_t *user) {
   printf("-----------------------------------------------\n");
   printf("             %s's friends\n", user->username);
   printf("-----------------------------------------------\n\n");
   printf("1. Display all user's friends\n");
   printf("2. Add a new friend\n");
   printf("3. Delete a friend\n");
   printf("4. Return to main menu\n");
}

/*
   ******** DONT MODIFY THIS FUNCTION ********
   Function that reads users from the text file.
   IMPORTANT: This function shouldn't be modified and used as is
   ******** DONT MODIFY THIS FUNCTION ********
*/
user_t *read_CSV_and_create_users(FILE *file, int num_users)
{
    user_t *users = NULL;
    char buffer[500];
    fgets(buffer, sizeof(buffer), file); // Read and discard the header line
    int count = 0;
    for (int i = 0; i < num_users; i++)
    {
        fgets(buffer, sizeof(buffer), file);
        buffer[strcspn(buffer, "\r\n")] = 0; // Remove newline characters

        char *token = strtok(buffer, ",");
        char *token2 = strtok(NULL, ",");
        users = add_user(users, token, token2);
        char *username = token;

        token = strtok(NULL, ",");

        user_t *current_user = users;
        for (; current_user != NULL && strcmp(current_user->username, username) != 0; current_user = current_user->next)
            ;

        while (token != NULL && strcmp(token, ",") != 0 && count < 3)
        {
            if (strcmp(token, " ") != 0)
            {
                add_friend(current_user, token);
            }
            token = strtok(NULL, ",");
            count++;
        }
        count = 0;

        // token = strtok(NULL, ",");
        while (token != NULL && strcmp(token, ",") != 0)
        {
            add_post(current_user, token);
            token = strtok(NULL, ",");
        }
    }
    return users;
}
