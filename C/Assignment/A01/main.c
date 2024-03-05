/********* main.c ********
	
	Student Name 	= Zachary Gallant
	Student Number	= 101272210
*/

// Includes go here
#include <stdlib.h>  
#include <stdio.h> 
#include <string.h>
#include <stdbool.h>
#include "a1_functions.h"
#include "a1_data_structures.h"

int main()
{
	//variables
    milestone_t project_milestone[6];
	
	//Project budget and duration input here
	printf("Welcome to ABC Project Tracker\n");
	printf("Enter total project's budget: ");
	float project_budget = get_input_f();
	printf("Enter total project's duration in weeks: ");
	float project_duration = get_input_f();
	strcpy (project_milestone[0].name, "Planned Details");
	project_milestone[0].cost = project_budget;
	project_milestone[0].time = project_duration;
	number_of_employees(&project_milestone[0]);
	printf("The planned number of employees needed is: %d\n", project_milestone[0].num_employees);
	printf("\n-------------------------------------------\n");


	//initialize milestone
	project_milestone[1] = init_milestone("Technical requirements", project_budget);
	project_milestone[2] = init_milestone("System Design", project_budget);
	project_milestone[3] = init_milestone("Software Development", project_budget);
	project_milestone[4] = init_milestone("Testing", project_budget);
	project_milestone[5] = init_milestone("Product release", project_budget);

	//print milestones
	_Bool exit_name = 1;
	while (exit_name == 1) {
		print_menu();
		printf("\nYour choice is: ");
		unsigned short int mile_choice;
		scanf("%hu", &mile_choice);
		while (mile_choice < 0 || mile_choice > 5) {			
			printf("\n\n--------------------------------------------\n");
			printf("The value you entered is wrong\n");
			printf("Enter a value between 0 and 5: ");
			scanf("%hu", &mile_choice);
		}

		if (mile_choice > 0) {
			print_stats(project_milestone[mile_choice]);
			update_stats(project_milestone, mile_choice);
		}else {
			printf("\n--------------------------------------------\n");
			printf("---------- Project's Performance -----------");
			printf("\n--------------------------------------------\n");
			check_project_completion(project_milestone, 5);
		}

		//All milestone done or program done (Final code to run before program closes). 
		if (project_milestone[0].completed == 1) {
			final_project_performance(project_milestone, 5);
			exit_name = 0;
		}
	}
	





    
}