/* main.c - SYSC 2006 Lab 7 
 *
 * Do not modify any of the code in this file.
 */
#include <stdio.h>
#include <stdlib.h>  // exit

#include "struct_string.h"

int main(void)
{
    // Exercise 1

	// Now we are going to test the updated fraction functions
	fraction_t f1 = {6,8};
	fraction_t f2 = {4,2};
	fraction_t result;

	// Reduce the fractions
	print_fraction(&f1);
	printf(" reduces to ");
	reduce(&f1);
	print_fraction(&f1);
	printf("\n\n");

	print_fraction(&f2);
	printf(" reduces to ");
	reduce(&f2);
	print_fraction(&f2);
	printf("\n\n");
	
	// add the fractions
	add_fractions(&f1,&f2,&result);
	printf("Adding ");
	print_fraction(&f1);
	printf(" and ");
	print_fraction(&f2);
	printf(" gives: ");
	print_fraction(&result);
	printf("\n\n");

	// multiply the fractions
	multiply_fractions(&f1,&f2,&result);
	printf("Multiplying ");
	print_fraction(&f1);
	printf(" and ");
	print_fraction(&f2);
	printf(" gives: ");
	print_fraction(&result);
	printf("\n\n");

    // Exercise 2

	// set up an array of student data
	const int STSIZE = 10;
	const int GRSIZE = 6;
	student_t students[STSIZE];
	for (int i=0; i<STSIZE; i++) {
		students[i].id = i*10+7;
		students[i].gpa = 0;
		for (int j=0; j<GRSIZE; j++) {
			students[i].grades[j].code = (i+j)*5+2;
			students[i].grades[j].weight = (j%2+1)*0.5;
			students[i].grades[j].gp = (i+j)%13;
		}
	}
	
	// print the student data before calculating GPAs
	for (int i=0; i<STSIZE; i++) {
		printf("Student Index: %d  Student ID: %d  Student GPA: %.1f\n", i, students[i].id, students[i].gpa);
		for (int j=0; j<GRSIZE; j++) {
			printf("\t Grade Index: %d  Grade Code: %d  Grade Weight: %.1f  Grade Point: %d\n", j, students[i].grades[j].code, students[i].grades[j].weight, students[i].grades[j].gp);
		}
	}
	printf("\n"); 
	
	// update the students' GPAs
	for (int i=0; i<STSIZE; i++){
		update_gpa(&students[i],GRSIZE);
	}
	
	// print the student (updated) GPAs
	for (int i=0; i<STSIZE; i++){
		printf("Student Index: %d  Student ID: %d  Student GPA: %.1f\n", i, students[i].id, students[i].gpa);
	}
	printf("\n");
	
	// Exrecise 3
	char test1[] = "Hello world ";
	char target1 = 'l';
	char target2 = 'a';
	char target3 = 'L';
	char target4 = 'H';
	char target5 = 'o';
	printf("The charcter %c appears %d time/s in test string %s \n", target1, count_char(test1,target1), test1);
	printf("The charcter %c appears %d time/s in test string %s \n", target2, count_char(test1,target2), test1);
	printf("The charcter %c appears %d time/s in test string %s \n", target3, count_char(test1,target3), test1);
	printf("The charcter %c appears %d time/s in test string %s \n", target4, count_char(test1,target4), test1);
	printf("The charcter %c appears %d time/s in test string %s \n", target5, count_char(test1,target5), test1);
	printf("\n\n");
// Exrecise 4
	
	printf("The charcter %c appears %d time/s in test string %s \n", target1, count_char_wp(test1,target1), test1);
	printf("The charcter %c appears %d time/s in test string %s \n", target2, count_char_wp(test1,target2), test1);
	printf("The charcter %c appears %d time/s in test string %s \n", target3, count_char_wp(test1,target3), test1);
	printf("The charcter %c appears %d time/s in test string %s \n", target4, count_char_wp(test1,target4), test1);
	printf("The charcter %c appears %d time/s in test string %s \n", target5, count_char_wp(test1,target5), test1);

}
