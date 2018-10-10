#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int lastFour = 0;

/* check the input is correct */

int checkError(int argc, char *str2, char *str3){

  char *word2 = "2";
  char *wordA = "a";
  if(argc == 2){
    return 1;
   }else if(strcmp("-d2b", str2) && strcmp("-b2d", str2) ){
    return 1;
   }else if(strlen(str3) > 16){
    return 1;
   }else if(!strcmp("-b2d", str2) && (strstr(str3, word2) != NULL)){
    return 1;
   }else if(!strcmp("-d2b", str2) && (strstr(str3, wordA) != NULL)){
    return 1;
   }
    return 0;
}

/* bin to dec */

long int b2d (long int num){

  if (!(num / 10)) {
    return num;
  }else{
    return (num % 10 + b2d(num / 10) * 2); 
  }
}

/* dec to bin */

long int d2b (long int num){
    if (num == 0){
      return 0;
    }else{
      return (num % 2 + 10 * d2b(num / 2));
    }
}

/* print the error message */

void printError(void){
      printf("ERROR\n");
      printf("usage:\n");
      printf("binary OPTION NUMBER\n");
      printf("  OPTION:\n");
      printf("    -b2d  NUMBER is binary and output will be in decimal.\n");
      printf("    -d2b  NUMBER is decimal and output will be in binary.\n\n");
      printf("  NUMBER:\n");
      printf("    number to be converted.\n\n");
}

/* add stupid zeros with space */

void addSpace(long int binSpa){
  if((binSpa / 10000 == 0) && (lastFour == 0)){

    if((binSpa/(10)) == 0 ){
      printf("000%lu", binSpa);
    }else if((binSpa/(100)) == 0 ){
      printf("00%lu", binSpa);
    }else if((binSpa/(1000)) == 0 ){
      printf("0%lu", binSpa);
    }else{
      printf("%lu", binSpa);
    }
  }else if(binSpa / 10000 == 0){
    if((binSpa/(10)) == 0 ){
      printf("000%lu ", binSpa);
    }else if((binSpa/(100)) == 0 ){
      printf("00%lu ", binSpa);
    }else if((binSpa/(1000)) == 0 ){
      printf("0%lu ", binSpa);
    }else{
      printf("%lu ", binSpa);
    }
  }else{
    lastFour++;
    addSpace(binSpa / 10000);
    lastFour--;
    addSpace(binSpa % 10000);

  }
}

/* add stupid Comma */

void addComma(long int decCom){

  if ((decCom/(1000)) != 0){
    printf("%lu,%lu\n", decCom / (1000), decCom % (1000));
  }else{
    printf("%lu\n", decCom);
  }
}

/* This program has b2d and d2b functions. */

int main(int argc, char *argv[])

{

  long int input;
    /* int i; */
    /*   printf ("argc = %d\n",argc); */
    /* for(i = 0; i < argc; i++){ */
    /*   printf("argv[%d] = %s\n", i, argv[i]); */
    /*   } */
  
    if(checkError(argc, argv[1], argv[2])){
      printError();

      }else if (!strcmp("-d2b", argv[1])){

      input = atol(argv[2]);
      /* printf("input is %lu\n",input); */
      addSpace(d2b(input));
      printf("\n");
      }else if(!strcmp("-b2d", argv[1])){

      input = atol(argv[2]);
      /* printf("input is %lu\n",input); */
      addComma(b2d(input));
      }else{
      printf("something else Error\n");
    }
   return 0;
}
