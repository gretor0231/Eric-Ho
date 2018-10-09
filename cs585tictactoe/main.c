
/** CS585 LAB3 part2
 * Cheng En Ho
 *
 * Nov.2 2017
 * version 2.0
 * */


#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdio.h>
#include<string.h>
#include <zconf.h>
#include <stdlib.h>
#include <time.h>

#define ROWS 3
#define COLS 3
typedef char Board[ROWS][COLS];

/** set the board */

Board board = {
        {'1','2','3'},
        {'4','5','6'},
        {'7','8','9'}
};


/** ctoi: char to int */

int ctoi(char i){
    return i-'0';
}
/** itoc: int to char */

char itoc(int i){
    return i+'0';
}

/** printBoard: print out the board */

void printBoard(Board board) {
    printf("\n\n");
    printf(" %c | %c | %c\n", board[0][0], board[0][1], board[0][2]);
    printf("---|---|---\n");
    printf(" %c | %c | %c\n", board[1][0], board[1][1], board[1][2]);
    printf("---|---|---\n");
    printf(" %c | %c | %c\n", board[2][0], board[2][1], board[2][2]);
}

/** hasWinner: check if it has winner then end the game*/

int hasWinner(Board board) {
    if((board[0][0]==board[1][1] && board[0][0]==board[2][2]) ||
       (board[0][2]==board[1][1] && board[0][2]==board[2][0])) { return 1; }
    for(int line = 0; line <=2; line++) {
        if((board[line][0]==board[line][1] && board[line][0]==board[line][2])||
           (board[0][line]==board[1][line] && board[0][line]==board[2][line])) {
            return 1;
        }
    }
    return 0;
}

/** Algorithm function check board empty */
int checkEmpty(int i){
    int j=0;
    if((i==1)&&(board[0][0]!='X')&&(board[0][0]!='O')){j=1;}
    else if((i==2)&&(board[0][1]!='X')&&(board[0][1]!='O')){j=1;}
    else if((i==3)&&(board[0][2]!='X')&&(board[0][2]!='O')){j=1;}
    else if((i==4)&&(board[1][0]!='X')&&(board[1][0]!='O')){j=1;}
    else if((i==5)&&(board[1][1]!='X')&&(board[1][1]!='O')){j=1;}
    else if((i==6)&&(board[1][2]!='X')&&(board[1][2]!='O')){j=1;}
    else if((i==7)&&(board[2][0]!='X')&&(board[2][0]!='O')){j=1;}
    else if((i==8)&&(board[2][1]!='X')&&(board[2][1]!='O')){j=1;}
    else if((i==9)&&(board[2][2]!='X')&&(board[2][2]!='O')){j=1;}
    return j;

}


/** Algorithm of boardGame is go 5,then 2468,then 1379
 * if find got any winner then change the value*/


int boardGame(int input,int player){
    int choice = 0;
    int row = 0;
    int column = 0;

    /** start board game */
    /**Client=X, Server=O*/
    /**Alg choice*/
    printf("the player is %d",player);

    if(player==0){
        choice = input;
    }else if (checkEmpty(5) == 1) { choice = 5; }
    else if (checkEmpty(2) == 1) { choice = 2; }
    else if (checkEmpty(4) == 1) { choice = 4; }
    else if (checkEmpty(6) == 1) { choice = 6; }
    else if (checkEmpty(8) == 1) { choice = 8; }
    else if (checkEmpty(1) == 1) { choice = 1; }
    else if (checkEmpty(3) == 1) { choice = 3; }
    else if (checkEmpty(7) == 1) { choice = 7; }
    else if (checkEmpty(9) == 1) { choice = 9; }
    //else{choice =(rand() % 9) + 1;}

    LOOP:
    if(     (checkEmpty(1) == 0)&&
            (checkEmpty(2) == 0)&&
            (checkEmpty(3) == 0)&&
            (checkEmpty(4) == 0)&&
            (checkEmpty(5) == 0)&&
            (checkEmpty(6) == 0)&&
            (checkEmpty(7) == 0)&&
            (checkEmpty(8) == 0)&&
            (checkEmpty(9) == 0)){

        printf("Tie!!\n");
        return 0;
    }

//    printf("the choice is %d",choice);
    row = --choice/3;
    column = choice%3;

    /**white the input to the board*/
    board[row][column] =(player%2==0)?'X':'O';

    if(hasWinner(board)){
        board[row][column] =choice +'1';
        {choice =10-choice;}

        goto LOOP;
    }



    printBoard(board);

    return choice+1 ;


}



int main()
{

    char sendline[10];
    char recvline[10];

    int listen_fd, comm_fd;
    int putIn,putOut;
    int i = -1;
    int j;
    struct sockaddr_in servaddr;

    listen_fd = socket(AF_INET, SOCK_STREAM, 0);

    bzero(&servaddr, sizeof(servaddr));

    servaddr.sin_family = AF_INET;
    servaddr.sin_addr.s_addr = htons(INADDR_ANY);
    servaddr.sin_port = htons(707);

    bind(listen_fd, (struct sockaddr *) &servaddr, sizeof(servaddr));

    listen(listen_fd, 10);

    /*---- Listen on the socket, with 10 max connection requests queued ----*/
    if (listen(listen_fd, 10) == 0)
        printf("Listening\n");


    comm_fd = accept(listen_fd, (struct sockaddr *) NULL, NULL);

    bzero(sendline, 10);
    bzero(recvline, 10);

    for (j=0;j<5;j++) {

        //read message
        i++;
        read(comm_fd, recvline, 10);
        printf("Server got: - %s\n", recvline);
        putIn=ctoi(recvline[0]);
        boardGame(putIn,i);
        i++;



        putOut=boardGame(putIn,i);

        printf("send the message %d\n",putOut);

        //send the message
        sendline[0]=itoc(putOut);
        write(comm_fd, sendline, strlen(sendline) + 1);


    }

}
