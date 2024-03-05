//SYSC 2006 - Lab 2 Solution template

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h> // Include the necessary header for _Bool

/* Replace these lines with your information */ 
  const char* author = "Zachary Gallant"; 
  const char* student_number = "101272210"; 

// function headers for lab2
int alarm_clock(int,_Bool); 
void count_replace(int); 
_Bool is_prime(int); 
int sumEven(int); 


int main(void)
{
  // Test exercise 1
  int day;
  _Bool vacation;

  day = 0; //day of the week (0=Sun, 1=Mon, ..., 6=Sat)
  vacation = 0; // for No, 1 for Yes 
  
  int alarm_time = alarm_clock(day, vacation);

  if (alarm_time == -1) {
        printf("The alarm is off.\n");
    } else {
        printf("The alarm will ring at %d:00 AM.\n", alarm_time);
    }


// Test exercise 2
count_replace(16);
count_replace(15);

// Test exercise 3
_Bool prime = is_prime(17); /// should return 30
printf("%d\n", prime);



// Test exercise 4 
int result = sumEven(10); /// should return 30
printf("%d\n", result);
printf("All done\n");
return EXIT_SUCCESS;
}

// Type your solution to exercise 1 after this line
int alarm_clock(int day, _Bool vacation) {
  ///This function takes 2 parameters, the first one takes the day
  ///off the week in number : 0=Sun, 1=Mon, 2=Tue, ..., 6=Sat and the 
  ///second parameter takes if the user is in vacation ore not and the 
  ///function returns at what time the user will wake up
  int return_value;
  if ((day < 6 && day > 0) && (vacation == 0)){
    return_value = 7;
  } else if (((day < 6 && day > 0)&& vacation == 1) || ((day == 6||day == 0) && vacation == 0)) {
    return_value = 10;
  } else {
    return_value = -1;
  }
  return return_value;

}
// Type your solution to exercise 2 after this line
void count_replace(int numbers) {
  //"""Takes a positive integer to print a series
  //of numbers but replaces the multiples of 3 with "Fizz" and the 
  //multiples of 5 with "Buzz"
  int multiple_5 = 5;
  int multiple_3 = 3;
  int i;
  for (i = 1; i <= numbers; ++i) {
    if ((i == multiple_5) && (i == multiple_3)) {
      printf("FizzBuzz ");
      multiple_5 = multiple_5 + 5;
      multiple_3 = multiple_3 + 3;
    } else if (i == multiple_3) {
      printf("Fizz ");
      multiple_3 = multiple_3 + 3;
    } else if (i == multiple_5) {
      printf("Buzz ");
      multiple_5 = multiple_5 + 5;
    } else {
      printf("%d ", i);
    }
  }
  printf("\n");
}
 
// Type your solution to exercise 3 after this line
_Bool is_prime(int number_input) {
  //Takes a positive integer and return a boolean. if the return
  //is True then the number is prime
  int i = 2;
  float changing_var = 0;
  _Bool anw = 1;
  if (number_input == 1) {
    anw = 0;
  } else {
    while (i < number_input) {
      changing_var = number_input % i;
      if (changing_var == 0) {
        anw = 0;
      }
      i += 1;
    }
  }
  return anw;
} 
// Type your solution to exercise 4 after this line
int sumEven(int range_number) {
  ///returns all even number from the integer given and add them all together
  int i = 2;
  int final_number = 0;
  while (i <= range_number) {
    final_number = final_number + i;
    i += 2;
  }
  return final_number;
} 