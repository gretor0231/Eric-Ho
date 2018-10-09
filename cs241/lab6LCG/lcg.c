
#include "lcg.h"

/** Get the next random number value from the previous random number. */


unsigned long getNextRandomValue(struct LinearCongruentialGenerator *lcg){

    unsigned long nextValue = lcg -> x;

    lcg -> x = (lcg -> a  *  lcg -> x  +  lcg -> c) % lcg -> m;

    return nextValue;
}


/** Returns a LCG with m, c, a, x */

struct LinearCongruentialGenerator makeLCG(unsigned long m, unsigned long c){

    struct LinearCongruentialGenerator lcg;
    unsigned long productOfAllFactors;
    unsigned long a;
    unsigned long div;

    productOfAllFactors = 1;
    div = 2;

    lcg.m = m;
    lcg.c = c;

    /** Generate a using a = 1 + 2p (if 4 is a factor of m), otherwise
       a = 1 + p
       p = (product of m's unique prime all factors) */



    /** Calculating p(productOfAllFactors) */
    while(m >= 2){

        if((m % div) == 0){

            productOfAllFactors *= div;

            /** While m is divisible by the div, eliminate that factor from m */
            while ((m % div) == 0){
                m = m / div;
            }
        }
        div++;
    }

    /** Getting a, m is divisible by 4 or not*/
    if((lcg.m % 4) == 0){

        a = (1 + (2 * productOfAllFactors));
    }
    else{

        a = (1 + productOfAllFactors);
    }

    lcg.a = a;
    lcg.x = c;

    return lcg;
}

