#include <stdio.h>
/* For memset */
#include <string.h>
#include <stdlib.h>
/* For LONG_MAX */
#include <limits.h>
#include "lcg.h"

int  i, lineNum, charNum, commasNum, isEncrypt;
char c;
char *ptr;
unsigned long long_lcg_m,long_lcg_c, randValue;
char outputChar;
char *lcg_m2;
struct LinearCongruentialGenerator lcg;

/* Char arrays to hold data from lines */
char lcg_m[20];
char lcg_c[20];
char data[400];
char line[500];

int deCount = 0;



void charcommasCount(void){

    /* Saving the whole line to be processed */
    while ((c = (char) getchar()) != '\n'){
        /* Counting commmas to validate that the line contains at least 2 commas */
        if(c == ','){
            commasNum++;
        }
        line[charNum] = c;
        charNum++;
    }

}

void initialLine(void){

    /* Printing line number in output */
    if(lineNum <= 9){
        printf("    %d) ", lineNum++);
    }else{
        printf("   %d) ", lineNum++);
    }

    /* Clearing the line character array for use in next getchar sequence */
    memset(&line[0], 0, sizeof(line));

    /* Capturing the first digit into the line character array */
    line[0] = c;

    /* Initializing variables to correct values to process another line */
    charNum = 1;
    commasNum = 0;
    isEncrypt = 0;

}

void encrypt(int outPut){

    /* if outputChar is:
      < 32 - Replace with '*' and '?' + outputChar
      = 127 - Replace with '*' and '!'
      = '*' - Replace with '*' and '*'
      target bytes that are outside the range of printable ASCII.  */
    if(outPut < 32){
        putchar('*');
        putchar('?' + outPut);
    }else if(outPut == 127){
        putchar('*');
        putchar('!');
    }else if(outPut == '*'){
        putchar('*');
        putchar('*');
    }else{
        putchar(outPut);

    }
}

char decrypt(char outPut){

    if(deCount == 2){
       deCount = 0;
       return '*';

    }else if(outPut == '!' && deCount == 1){
        deCount = 0;
        return 127;
    }else if(deCount == 1 && ((outPut -'?') < 32) ){
        deCount = 0;
        return (char) (outPut - '?');
    }else{
        deCount = 0;
        return outPut;
    }
}


void putData(int j){

    i = 0;
    while(data[i] != '\0'){
        /* Getting my next random value, "x" */
        randValue = getNextRandomValue(&lcg);

        /* Generating an output character and then outputting it to screen */

        if(j == 1){
            outputChar = (char) (data[i] ^ (randValue % 128));
            encrypt(outputChar);
        }else if(j == 0){
            NEXT:
            outputChar = data[i];
            if (outputChar == '*'){
                deCount++;
                i++;

                goto NEXT;

            }else{
                if(deCount == 2){i--;}
                outputChar = (char) (decrypt(outputChar) ^ (randValue % 128));
                if(outputChar < 32){
                    printf("Error");
                    return;
                }else{
                putchar(outputChar);
                }
            }

        }

        i++;
    }
}

void EnDecrypt(void){

    lcg = makeLCG(long_lcg_m, long_lcg_c);

    if(isEncrypt){

        /* Making the LCG struct */

        if(lcg.a > lcg.m){
            printf("Error");
        }else{
            putData(1);
        }
    }else{
        putData(0);
    }
}

void chechErrorLcgCM(void){
    /* Error conditions */
    if(long_lcg_c < 1 || long_lcg_m < 1 || strlen(lcg_m2) > 20){
        printf("Error");
    }else{
        EnDecrypt();
    }
}

void checkErrorED(void){
    /* Saving strings for use in algorithm */
    sscanf(line, "%[^,],%50[^,],%[^\n]", lcg_m, lcg_c, data);

    /* Finding out whether or not we're encrypting or decrypting */
    if(line[0] == 'e'){
        isEncrypt = 1;
    }
    long_lcg_c = (unsigned long) atol(lcg_c);

    /* Removing the first character of lcg_m string to remove the e or d */
    lcg_m2 = lcg_m;
    lcg_m2++;

    /* Converting lcg_m string to a long */
    long_lcg_m = strtoul(lcg_m2, &ptr, 10);

    chechErrorLcgCM();

}


int main(void){
  

  lineNum = 1;



  while((c = (char) getchar()) != EOF){

    initialLine();

    charcommasCount();

    /* Null terminating the line */
    line[charNum] = '\0';


    /* Verifying first two digits are valid input */
    if((line[0] == 'e' || line[0] == 'd') && line[1] != ',' && commasNum >= 2){

     checkErrorED();

    /* Line didn't start with e or d or there was a comma after e/d or there wasn't 2 commas */
    }else{
      printf("Error");
    }
    /* Putting back the newline into stdout */
    putchar('\n');
  }

	return 0;
}
