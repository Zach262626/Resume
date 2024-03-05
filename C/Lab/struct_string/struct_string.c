#include "struct_string.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


// Exercise 1
/* Print fraction pointed to by pf in the form a/b. */
void print_fraction(const fraction_t* pf)
{
	printf("%d/%d", pf->num, pf->den);
}

/* Return the greatest common divisor of integers a and b; 
   i.e., return the largest positive integer that evenly divides 
   both values.  Copy this function from Lab 4.  No changes are needed.
*/
int gcd(int a, int b)
{
   int q = a;
   int p = b;
   if (q<0) {
      q = q * -1;
   }
   if (p<0) {
      p = p * -1;
   }
   int r = q % p;
   while (r != 0) {
      q = p;
      p = r;
      r = q % p;
   }

	return p;
}

/* Updates the fraction pointed to by pf to its reduced form.

   This means that:
   (1) if the numerator is equal to 0 the denominator is always 1.
   (2) if the numerator is not equal to 0 the denominator is always
       positive, and the numerator and denominator have no common
       divisors other than 1.

   In other words,
   (1) if the numerator is 0 the denominator is always changed to 1.
   (2) if the numerator and denominator are both negative, both values
       are made positive, or if the numerator is positive and the 
       denominator is negative, the numerator is made negative and the 
       denominator is made positive.
   (3) the numerator and denominator are both divided by their greatest
       common divisor. 
*/
void reduce(fraction_t* pf)
{
   int num = pf->num;
   int den = pf->den;
   if (num == 0) {
      num = 0;
      den = 1;
   }else if (den < 0) {
      num = num * -1;
      den = den * -1;
   }
   int common = gcd(num, den);
   num = num / common;
   den = den / common;
   pf->num = num;
   pf->den = den;
}

/* Returns a pointer to a fraction (by reference) with numerator a and denominator b.
   Print an error message and terminate the calling program via exit()
   if the denominator is 0.
   The fraction returned by this function is always in reduced form.
*/
void make_fraction(int a, int b, fraction_t* pf)
{
   int p = a;
   int q = b;
   if (q == 0) {
      printf("Error denominator is 0");
      exit(1);
   }
   fraction_t fr = {p, q};
   reduce(&fr);
   (*pf) = fr;
}

/* Returns by reference a pointer to the sum of fractions pointed to by pf1 and pf2.
   The fraction returned by this function is always in reduced form
 */
void add_fractions(const fraction_t* pf1, const fraction_t* pf2, fraction_t* psum)
{
   int f1_num = (*pf1).num;
   int f1_den = (*pf1).den;
   int f2_num = (*pf2).num;
   int f2_den = (*pf2).den;
   int f_num = (f1_num * f2_den) + (f2_num * f1_den);
   int f_den = f1_den * f2_den;
   psum->num = f_num;
   psum->den = f_den;
}

/* Returns by reference a pointer to the product of fractions pointed to by pf1 and pf2.
   The fraction returned by this function is always in reduced form
 */
void multiply_fractions(const fraction_t* pf1, const fraction_t* pf2, fraction_t* pprod)
{
   int f1_num = (*pf1).num;
   int f1_den = (*pf1).den;
   int f2_num = (*pf2).num;
   int f2_den = (*pf2).den;
   int f_num = f1_num * f2_num;
   int f_den = f1_den * f2_den;
   pprod->num = f_num;
   pprod->den = f_den;
   reduce(pprod);
}

// Exercise 2

/* Update the GPA of the student pointed to by studentp, assuming the student has numGrades.    Note that update_gpa must call calc_gpa for each student. */
void update_gpa(student_t *studentp, int numGrades) {
   studentp->gpa = calc_gpa(studentp->grades, numGrades); 
}

/* Calculate the GPA associated with the numGrades pointed to by gradesp.
   The implementation must use a walking pointer. 
   If the total weight of all courses is 0, a GPA of 0 is returned. */
float calc_gpa(const grade_t *gradesp, int numGrades) {
    float gp = 0;
    float weight = 0;
    for (int i = 0; i < numGrades; ++i) {
        float gp_loc = gradesp->gp;
        float weight_loc = gradesp->weight;
        gp += gp_loc; 
        weight += weight_loc; 
        gradesp += 1;
    }

    return gp / weight;
}

// Exercise 3

/* Calculate how many time the target charcter appears in string my_str
For example:
count_char("Hello world ",'l') return 3
count_char("Hello world ",'a') return 0
Your implementation must use array-indexing. 
*/

int count_char(const char * my_str, char target){
   size_t len = strlen(my_str);
   int app = 0;
   for (int i=0; i<len; ++i){
      if (my_str[i]==target){
         app += 1;
      }
   }
   return app;
}

// Exercise 3

/* Calculate how many time the target charcter appears in string my_str
For example:
count_char("Hello world ",'l') return 3
count_char("Hello world ",'a') return 0
Your implementation must use walking pointer.
*/


int count_char_wp(const char * my_str, char target){
   size_t len = strlen(my_str);
   int app = 0;
   for (int i=0; i<len; ++i){
      if (*my_str == target){
         app += 1;
      }
      my_str += 1;
   }
   return app;
}