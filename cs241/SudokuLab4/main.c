#include <stdio.h>

#define N 9
#define UNASSIGNED 0


/** CS241 LAB4
 * Cheng En Ho
 * Feb.6 2018*/


/** this program trying to solve the Sudoku */




/** Find the number exist in row */

int is_exist_row(int grid[N][N], int row, int num){
    for (int col = 0; col < 9; col++) {
        if (grid[row][col] == num) {
            return 1;
        }
    }
    return 0;
}

/** Find the number exist in col */

int is_exist_col(int grid[N][N], int col, int num) {
    for (int row = 0; row < 9; row++) {
        if (grid[row][col] == num) {
            return 1;
        }
    }
    return 0;
}

/** Find the number exist is 3x3 box */

int is_exist_box(int grid[N][N], int startRow, int startCol, int num) {
    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            if (grid[row + startRow][col + startCol] == num) {
                return 1;
            }
        }
    }
    return 0;
}

/** check three function: row,col,and box*/

int is_safe_num(int grid[N][N], int row, int col, int num) {
    return !is_exist_row(grid, row, num)
           && !is_exist_col(grid, col, num)
           && !is_exist_box(grid, row - (row % 3), col - (col %3), num);
}

/** Find the number is unassigned 0 */


int find_unassigned(int grid[N][N], int *row, int *col) {
    for (*row = 0; *row < N; (*row)++) {
        for (*col = 0; *col < N; (*col)++) {
            if (grid[*row][*col] == 0) {
                return 1;
            }
        }
    }
    return 0;
}

/** solve the problem */

int solve(int grid[N][N]) {

    int row = 0;
    int col = 0;

    if (!find_unassigned(grid, &row, &col)){
        return 1;
    }

    for (int num = 1; num <= N; num++ ) {

        if (is_safe_num(grid, row, col, num)) {
            grid[row][col] = num;

            if (solve(grid)) {
                return 1;
            }
            grid[row][col] = UNASSIGNED;
        }
    }
    return 0;
}

/** print out the array */

void print_grid(int grid[N][N]) {
    for (int row = 0; row < N; row++) {
        for (int col = 0; col < N; col++) {
            printf("%2d", grid[row][col]);
        }
        printf("\n");
    }
}

/** char to int */

int ctoint(char c){
    return c-'0';
    }




int main() {
    int temp;
    int count=0;
    int count1=0;
    char c;
    int grid[N][N];

/** put input into array */


    while ((c = getchar()) != EOF) {
        if(c =='.'){
            c ='0';
        }
        if(c != '\n') {
            temp = ctoint(c);

        }
        grid[count1][count] = temp;
        count++;
        if(count == 9){
            count = 0;
            count1++;
        }
    }
/** solve the problem. */

    if (solve(grid)) {
        print_grid(grid);
    } else {
        printf("no solution");
    }

    return 0;
}