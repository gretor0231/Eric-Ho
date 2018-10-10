
#include <stdio.h>

#define N 9
#define UNASSIGNED 0


/** this program trying to solve the Sudoku */
 

/** Find the number exist in row */


int is_exist_row(int grid[N][N], int row, int num){
  int col;
    for ( col = 0; col < 9; col++) {
        if (grid[row][col] == num) {
            return 1;
        }
    }
    return 0;
}

/** Find the number exist in col */

int is_exist_col(int grid[N][N], int col, int num) {
  int row;
    for ( row = 0; row < 9; row++) {
        if (grid[row][col] == num) {
            return 1;
        }
    }
    return 0;
}

/** Find the number exist is 3x3 box */

int is_exist_box(int grid[N][N], int startRow, int startCol, int num) {
  int row,col;
  for ( row = 0; row < 3; row++) {
        for ( col = 0; col < 3; col++) {
            if (grid[row + startRow][col + startCol] == num) {
                return 1;
		
            }
        }
    }
    return 0;
}

/** check three function: row,col,and box*/

int is_safe_num(int grid[N][N], int row, int col, int num) {
  if(is_exist_row(grid, row, num)){
    return 0;
  }
    else if(is_exist_col(grid, col, num)){
      return 0;
    }
    else if(is_exist_box(grid, row - (row % 3), col - (col %3), num)){
      return 0;
    }
    else{
      return 1;
    }
    
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

/** solve the array, it use recursion. It use point to find unassigned, if find it,
put a number and check is it safe or not. If it is safe, go to fing next unassigned.
if it it not safe, go back to unassigned and use next number to find it.  */

int solve(int grid[N][N]) {

    int row;
    int col;
    int num;
    if (!find_unassigned(grid, &row, &col)){
        return 1;
    }

    for ( num = 1; num <= N; num++ ) {

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
  int row,col;

  printf("solution:\n");
    for ( row = 0; row < N; row++) {
      	if(row == 3 || row == 6){
	  printf("------+-------+------\n");
	}

      for ( col = 0; col < N; col++) {
	  if(col == 3 || col == 6 ){
	    printf(" |%2d",grid[row][col]);
	  }else if(col == 0){
	    printf("%d", grid[row][col]);
	  }else{ 
            printf("%2d", grid[row][col]);
	  }
	}
        printf("\n");
    }
}

/** char to int */

int ctoint(char c){
    return c-'0';
    }


/** if it has char a~z, then return error */


int checkChar(char orgin[83]){
  int i;
  for(i = 0; i < 81; i++){
    if(orgin[i] >= 97 && orgin[i] <= 122){
      return 1;
    }
  }
  return 0;
}

/** find the duplicate number in row, col or 3x3 box. if find it then return error */

int intMacthing(int grid[N][N]){


  int row,col,num;
  num = 0;
  for(row = 0; row < N; row++){
    for(col = 0; col < N; col++){
      if(grid[row][col] != 0){
	num = grid[row][col];
	grid[row][col] = 0;
	if (!is_safe_num(grid, row, col, num)) {
	  return 1;
	 }
      }
    }
  }
  return 0;
}


int main() {
    int col = 0;
    int row = 0;
    int index = 0;
    char c;
    int grid[N][N];
    int grid2[N][N];
    char orgin[83];
/** put input into array */


    while ((c = getchar()) != EOF) {

        orgin[index]= c;
	index++;
	if(c =='.'){
	  c ='0';
	}
        grid[row][col] = ctoint(c);
        col++;
        if(col == 9){
            col = 0;
            row++;
        }
/** check the input is correct condition, and then print the input array. */
	if(c == '\n'){


	  for(row = 0; row < N; row++){
	    for(col = 0; col < N; col++){
	      grid2[row][col] = grid[row][col];
	    }
	  }
 	  if(checkChar(orgin) || index != 82 || intMacthing(grid2)){
	    orgin[index]='\0';
	    printf("%s", orgin);  
	    printf("Error\n\n");
	      goto Next;
	  }else{
	  orgin[81]='\0';
	  printf("%s\n", orgin);  
	 
/** solve the problem. */

	  if (solve(grid)) {
   	    print_grid(grid);

	  } else {
	    printf("No solution\n\n");
	  }
	Next:
	
	  index = 0;
	  col = 0;
	  row = 0;  
	  } 
	}
    }

    return 0;

}
