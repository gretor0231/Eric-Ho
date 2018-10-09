
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
#include <error.h>

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

/** hasWinner: check if it has winner then change value*/

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

/** Function: check board empty */
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
//    int k = 0;

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
        {choice =10 - choice;}

        goto LOOP;
    }



    printBoard(board);

    return choice+1 ;


}


int main(int argc,char *argv[])

{

    int sockfd;
    int putIn,putOut;
    char sendline[100];
    char recvline[100];
    int i;
    int j = 0;

    struct sockaddr_in servaddr;


    /** set up socket */

    /* Make sure host is specified. */
    if (argc < 2) {
        fprintf(stderr,"usage %s hostname\n", argv[0]);
        exit(0);
    }

    sockfd=socket(AF_INET,SOCK_STREAM,0);
    bzero(&servaddr,sizeof servaddr);

    servaddr.sin_family=AF_INET;
    servaddr.sin_port=htons(707);
    inet_pton(AF_INET,argv[1],&(servaddr.sin_addr));
    connect(sockfd,(struct sockaddr *)&servaddr,sizeof(servaddr));




    bzero(sendline, 100);
    bzero(recvline, 100);

    printf("Start playing the game, Client go first\n");
    printf("Create a random number 1-9 go first move\n");
    srand(time(NULL));
    putIn=(rand() % 9) + 1;//1~9
    while(putIn==3||putIn==6||putIn==8){
        putIn=(rand() % 9) + 1;//1~9
    }
    putOut = boardGame(putIn,j);
//    printf("put out is %d\n",putOut);

    //send the random number first time
    sendline[0]=itoc(putOut);
        sendline[1]=board[0][0];
        sendline[2]=board[0][1];
        sendline[3]=board[0][2];
	sendline[4]='|';        
	sendline[5]=board[1][0];
        sendline[6]=board[1][1];
        sendline[7]=board[1][2];
	sendline[8]='|';
        sendline[9]=board[2][0];
        sendline[10]=board[2][1];
        sendline[11]=board[2][2];
	sendline[12]='\n';
        printf("send the message is %c\n",sendline[0]);
//    printf("send the message1 is %c\n",sendline[1]);
//    printf("send the message2 is %c\n",sendline[2]);		
    write(sockfd, sendline, strlen(sendline) + 1);

    /** connection test */
    for(i=0;i<5;i++){

        //read message
        j++;
        read(sockfd, recvline, 100);
        printf("Client got : - %s\n", recvline);
        putIn=ctoi(recvline[0]);
        boardGame(putIn,j);
        j++;

        putOut=boardGame(putIn,j);

        printf("Send the message %d\n",putOut);

        //send message
        sendline[0]=itoc(putOut);
        sendline[1]=board[0][0];
        sendline[2]=board[0][1];
        sendline[3]=board[0][2];
	sendline[4]='|';        
	sendline[5]=board[1][0];
        sendline[6]=board[1][1];
        sendline[7]=board[1][2];
	sendline[8]='|';
        sendline[9]=board[2][0];
        sendline[10]=board[2][1];
        sendline[11]=board[2][2];
	sendline[12]='\n';
        write(sockfd, sendline, strlen(sendline) + 1);



    }
    close(sockfd);


}


