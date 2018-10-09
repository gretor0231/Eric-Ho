
#include <stdio.h>
#include <stdlib.h>

#define TRUE 1
#define FALSE 0

/** CS241 LAB3
 * Cheng En Ho
 * Sep.8 2017*/


/** getbits: get n bits from position p */

unsigned int getbits(unsigned x, int p, int n){

    return (x >> (p+1-n)) & ~(~0 << n);
}


/** pgetbits: print ERROR or getbits */

void pgetbits(unsigned long x, int p, int n){

    int state;

        state = TRUE;
        if(n - p > 1){
            printf("Error: too many bits requested from position\n");
            state = FALSE;
        }else if(p >= 32){
            printf("Error: position out of range\n");
            state = FALSE;
        }else if(n >= 32){
            printf("Error: number of bits out of range\n");
            state = FALSE;
        }else if(x > 4294967295 ){// The unsigned int max range is 4294967295.
            printf("Error: value out of range\n");
            state = FALSE;
        }

        if(state){
            printf("getbits(x=%u, p=%d, n=%d) = %u\n",x,p,n,getbits(x, p, n));
    }
}







/** This program use the getbits function, if the input number over the range,
 * then print Error.
 * */

int main() {
    int i=0; //array [i]
    int nc=0;//temp number
    int k,l; // k,l,j position ,number and unsigned int
    unsigned long j;
    char c;  //getchar(c)
    char temp[] = {'0','0','0','0','0','0','0','0','0','0','0'};//temp array




    while ((c = getchar()) != EOF) {

        /** if x number great than 10 bits, stop assigning to the char array*/
        if(i > 10){
            goto NEXTLINE;
        }
        temp[i] = c;
        i++;

        /** After goto, keep x unmber until next line*/
        NEXTLINE:

        /** Allocate three numbers between ';' and '\n' */
        if(c =='\n' || c ==';') {
            nc++;

            /** catch three number and change char to int by using atol, and atoi*/
            if(nc % 3 == 1){
                 j = atol(temp);
            }else if(nc % 3 == 2){
                 k = atoi(temp);
            }else if(nc % 3 == 0){
                 l = atoi(temp);
            }

            /** print the result at the end of line*/
            if(c =='\n'){
                pgetbits(j, k, l);

                /** renew three temp numbers */
                j=k=l=0;
            }

            /** renew char array count number */
            i=0;

        }

    }

    return 0;
}
