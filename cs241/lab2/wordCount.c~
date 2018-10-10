#include <stdio.h>

#define IN 1 /* inside a word */
#define OUT 0 /* outside a word */

    /* this program count each line of words,chars and total of words, chars, and lines.
    */


    int main(void)
    {
   /* printf("*******************\n");
    printf("*Chegn En Ho      *\n");
    printf("*2018 spring      *\n");
    printf("*CS241            *\n");
    printf("*******************\n");
    */
    /* set up int c to catch chars and int number line, number word and number chars .
    */


    int c, nl, nw, nc, state,nw1,nc1,nw2,nc2,nl1,nl2;
           state = OUT;
        nl = nw = nc = 0;
        nw1 =nc1=0;
        nw2 =nc2=0;
        nl1 =nl2 =0;

    /* use getchar function to get all the chars if it is not end of file.
    */

    while ((c = getchar()) != EOF) {
            ++nc;
            ++nc1;
            if (c == '\n') {
                ++nl;
                --nc;
                --nc1;
            }

        /* if char is space or change line, change the state to OUT.
        */


        if (c == ' '|| c == '\n' || c == '\t')
                state = OUT;
            else if (state == OUT) {
                state = IN;
                ++nw;
                ++nw1;
            }
        /* for first nine number, print space plus number.If number great than ten, just print the number.
        */

            if( nc1 == 1){
		        if(nl<=8){
                    printf(" %d. ", nl+1);
		        }
		        else if(nl>8){
		            printf("%d. ",nl+1);
		        }
            }
        /* if lines do not have any chars, then just print the number and change line.
        */
            if(nc1 == 0 && c =='\n'){
                if(nl<=9){
                    printf(" %d. ", nl);
                }
                else if(nl>9){
                    printf("%d. ",nl);
                }

            }

        /* for each line in the end, print the words and chars of that line.
        */

	     if (c == '\n'){

                printf(" [%d,%d]", nw1, nc1);


                if(nw2>=nw1){
                    nw2=nw1;
                    nl1=nl;
                }
                if(nc2>=nc1){
                    nc2=nc1;
                    nl2=nl;
                }
                nc1=nw1=0;

            }
        /* use putchar function to print out chars.
        */

            putchar(c);
        }

        /* print out the total words, lines, and chars. And print the fewest of words line and most of chars line.
        */

        printf("%d lines, %d words, %d characters\n",nl,nw,nc);
        printf("Line %d has the fewest words with %d\n",nl1,nw2);
        printf("Line %d has the fewest characters with %d\n",nl2,nc2);

	return 0;
}
