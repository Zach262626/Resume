/********* main.c ********
    Student Name 	=Zachary Gallant
    Student Number	=101272210
*/

// Includes go here
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include "a2_nodes.h"
#include "a2_functions.h"

int main()
{
    /******** DONT MODIFY THIS PART OF THE CODE ********/
    /* THIS CODE WILL LOAD THE DATABASE OF USERS FROM THE FILE 
       AND GENERATE THE STARTING LINKED LIST.
    */
    FILE *csv_file = fopen("user_details.csv", "r");
    if (csv_file == NULL)
    {
        perror("Error opening the CSV file");
        return 1;
    }
    // Parse CSV data and create users
    user_t *users = read_CSV_and_create_users(csv_file, 50);

    fclose(csv_file);
    /******** DONT MODIFY THIS PART OF THE CODE ********/
    
    /* IMPORTANT: You must use the users linked list created in the code above. 
                  Any new users should be added to that linked list.
    */
   
    // Your solution goes here
    printf("***********************************************\n");
    printf("      Welcome to Text-Based Facebook\n");
    printf("***********************************************\n\n");
    unsigned short int choice = 0;
    unsigned short int end = 0;
    while (end == 0){
        print_menu();
        printf("Enter your choice: ");
        scanf("%d", &choice);
        while (choice <= 0 || 6 < choice) {
            printf("Invalide choice. Please try again.\n\n");
            print_menu();
            printf("Enter your choice: ");
            scanf("%d", &choice);
        }

        //Exit Statement
        if (choice == 6) {
            printf("***********************************************\n");
            printf("  Thank you for using Text-Based Facebook\n");
            printf("              GoodBye!\n");
            printf("***********************************************\n\n");
            end = 1;
        }

        if(choice == 1){
            char username1[30];
            char password1[15];
            printf("Enter a username: ");
            scanf("%s", &username1);
            printf("Enter an up to 15 characters: ");
            scanf("%s", &password1);
            users = add_user(users,username1,password1);

            printf("\n**** User Added! ****\n\n");
        }

        if(choice == 2) {
            char username2[30];
            char password2[15];
            printf("Enter username to update their password: ");
            scanf("%s", &username2);
            user_t *user2 = users;
            user2 = find_user(users, username2);
            if (user2 == NULL){
                printf("\n-----------------------------------------------\n");
                printf("             User not found.\n");
                printf("-----------------------------------------------\n\n");
            }else{
                printf("Enter a new password that is upto 15 characters: ");
                scanf("%s", &password2);
                strcpy(user2->password, password2);
                printf("\n**** Password changed! ****\n\n");

            }
        }


        if (choice == 3) {//Do function
            char username3[30];
            char password3[15];
            char new_content[250];
            unsigned short int post_choice = 1;

            //Find user and go back to menu if user doesnt exit
            printf("Enter username to manage their posts: ");
            scanf("%s", &username3);
            user_t *user3 = users;
            user3 = find_user(users, username3);
            if (user3 == NULL){
                post_choice = 3;
                printf("\n-----------------------------------------------\n");
                printf("             User not found.\n");
                printf("-----------------------------------------------\n\n");

            }


            while (post_choice != 3){
                display_user_posts(user3);   
                printf("1. Add a new user post\n");
                printf("2. Remove a users post\n");
                printf("3. Return to main menu\n\n");
                printf("Your choice: ");
                scanf("%d", &post_choice);

                //Add a Post
                if (post_choice == 1) {
                    printf("Enter your post content: ");
                    scanf(" %[^\n]s", &new_content);
                    add_post(user3, new_content);
                    printf("Post added to your profile.\n");
                }else if(post_choice == 2) { //Delete Post
                    int delete_choice = 1;
                    printf("Which post you want to delete? ");
                    scanf("%d", &delete_choice);
                    
                    _Bool deleted = delete_post(user3, delete_choice);
                    if (deleted == 0) {
                        printf("Invalid post's number\n");
                    }else{
                        printf("Post %d was deleted successfully!\n", delete_choice);
                    }
                }else if(post_choice != 3){
                    printf("Invalid choice. Please try again.\n");
                } else{
                    printf("\n");
                }
            }
                


        }


        if (choice == 4) {
            unsigned short int friend_choice = 1;
            char username4[30];
            char friend_name[30];
            //find user
            printf("Enter username to manage their friends: ");
            scanf("%s", &username4);
            user_t *user4 = users;
            user4 = find_user(users,username4);
            if (user4 == NULL){
                printf("-----------------------------------------------\n");
                printf("             User not found.\n");
                printf("-----------------------------------------------\n\n");
                friend_choice = 4;
            }
            //user friends 
            while(friend_choice != 4) {
                print_friend_menu(user4);
                printf("\nYour choice: ");
                scanf("%hu", &friend_choice);
                printf("\n");

                if (friend_choice == 1) {
                    printf("List of %s's friends:\n", user4->username);
                    display_user_friends(user4);
                }else if (friend_choice == 2){
                    printf("Enter a new friend's name: ");
                    scanf("%s", &friend_name);
                    add_friend(user4, friend_name);
                    printf("Friend added to the list.\n");
                }else if (friend_choice == 3) {
                    printf("List of %s's friends:\n", user4->username);
                    display_user_friends(user4);
                    printf("\nEnter a friend's name to delete: ");
                    scanf("%s", &friend_name);
                    _Bool delete = delete_friend(user4, friend_name);
                    if (delete == false){
                        printf("Invalid friend's name\n\n");
                    }
                    display_user_friends(user4);
                }else if(friend_choice!=4){
                    printf("Invalid choice. Please try again.\n");
                }
            }
        }

        if (choice == 5) {
            display_all_posts(users);
        }
    }

    teardown(users);

}