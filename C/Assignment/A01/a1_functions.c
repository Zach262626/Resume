/********* definitions.c ********
	
	Student Name 	= Zachary Gallant
	Student Number	= 101272210
*/
#include <stdlib.h>  
#include <stdio.h> 
#include <string.h>
#include <stdbool.h>
#include <math.h>
#include "a1_functions.h"
#include "a1_data_structures.h"

/*
   Get user float input. The value must be positive.
   If negative value entered ask user to re-enter a positive value
*/

float get_input_f() {
	float input;
	scanf("%f", &input);
	while (input < 0) {
		printf("\n--------------------------------------------\n");
		printf("The value you entered is wrong\n");
		printf("Enter a positive value: ");
		scanf("%f", &input);
	}
	return input;
}

/* 
   Get unsigned short integer user input. The value must be positive.
   If negative value entered ask user to re-enter a positive value
*/

unsigned short int get_input_usi(void){
	unsigned short int input;
	scanf("%hu", &input);
	while (input < 0) {
		printf("\n--------------------------------------------\n");
		printf("\nThe value you entered is wrong\n");
		printf("Enter a positive value: ");
		scanf("%hu", &input);
	}
	return input;
}


/*
   Calculate number of employees needed for the project based on cost and time
*/

void number_of_employees(milestone_t *project_details) {
	float budget = (*project_details).cost;
	float salary = (*project_details).time * 40 * 25;
	unsigned short int numemployees = roundf(budget / salary);
	(*project_details).num_employees = numemployees;
}

/* 
   Initialize milestones with names and planned budget
*/

milestone_t init_milestone(char stage_name[], float assigned_budget) {
	milestone_t mile;
	strcpy (mile.name, stage_name);
	mile.cost = assigned_budget/5;
	mile.num_employees = 0;
	mile.time= 0;
	mile.completed = 0;
	return mile;
}

/*
   Print out current stats of each milestone/project
   (i.e, print all members of the milestone_t struct)
*/
void print_stats(milestone_t details) {
	printf("\n------------ Milestone Stats ------------");
	printf("\nBelow is \"%s\" current stats:", details.name);
	printf("\n------------------------------------------\n\n");

	printf("Actual time: %.2f weeks\n", details.time);
	printf("Actual number of employees: %hu\n", details.num_employees);
	printf("Actual cost: %.2f$\n", details.cost);
	if (details.completed == 1) {
		printf("Completed: Yes\n");
	}else if (details.completed == 0) {
		printf("Completed: No\n");
	}
}
/*
   Update the stats of the milestones (not the project).
   (i.e., Asks the user to update time, number of employees and completed.
    Cost is calculated based on the input data)
*/
void update_stats(milestone_t milestone_array[], int milestone_num) {
	if (milestone_array[milestone_num].completed == 0) {
		char input_Y_N;
		printf("\n-------------- Update Stats --------------\n\n");
		printf("Enter The milestone's actual time: ");
		milestone_array[milestone_num].time = get_input_f();
		printf("Enter The milestone's actual number of employees: ");
		milestone_array[milestone_num].num_employees = get_input_usi();
		milestone_array[milestone_num].cost = milestone_array[milestone_num].num_employees * milestone_array[milestone_num].time * 40 * 25;
		printf("Is this milestone complete?: (Y/N)");
		scanf(" %c", &input_Y_N);
		if ((input_Y_N == 'Y') || (input_Y_N == 'y')) {
			milestone_array[milestone_num].completed = 1;
		} 
		printf("Stats Updated!\n");
		print_stats(milestone_array[milestone_num]);}	
	else {
		printf("\n--------------------------------------------\n");
		printf("%s milestone is completed\n        cannot be updated", milestone_array[milestone_num].name);
		printf("\n--------------------------------------------\n");
	}
}

/*
   Check if all milestones are completed or not. If completed, updated the project's details to completed.
*/
void check_project_completion(milestone_t milestone_array[], int num_milestones) {
	int int_completed = 0;
	float total_cost = 0;
	char input_Y_N;
	for (int i=1; i<=num_milestones; ++i) {
		if (milestone_array[i].completed == 1) {
			++int_completed;
		}
	}
	if (int_completed == 5) {
		milestone_array[0].completed = 1;
		print_stats(milestone_array[0]);
	}else {
		print_stats(milestone_array[0]);
		printf("Do you want to finish the remaining milestones? (Y/N)");
		scanf(" %c", &input_Y_N);
		if ((input_Y_N == 'N') || (input_Y_N == 'n')) {
			milestone_array[0].completed = 1;
		} 

	}
}
/* 
   Print menu with a list of milestones/options for the user to choose from
*/
void print_menu(void) {
	printf("\nWhich milestone you want to update? (0 to exit):\n");
  	printf("1. Technical requirement\n");
	printf("2. System Design\n");
	printf("3. Software Development\n");
   	printf("4. Testing\n");
   	printf("5. Product release\n"); 
}


/*
   Calculate the final project's performance en prints the end of the program
*/

void final_project_performance(milestone_t milestone_array[], int num_milestones) {
	printf("\n--------------------------------------------\n");
	printf("------ Final  Project's Performance -------");
	printf("\n--------------------------------------------\n");
	float total_cost = 0;
	for (int i=1; i<=num_milestones; ++i) {
		total_cost = total_cost + milestone_array[i].cost;
	}

	if (total_cost <= milestone_array[0].cost) {
		printf("\nThe project's cost is bellow budget!\n");
	}else  {
		printf("\nThe project's cost is above budget!\n");
	}
	printf("Planned budget: %.2f\n", milestone_array[0].cost);
	printf("Actual Cost: %.2f\n", total_cost);

	printf("\n--------------------------------------------\n");
	printf("--- The program will quit now! Goodbye! ---");
	printf("\n--------------------------------------------\n");
}