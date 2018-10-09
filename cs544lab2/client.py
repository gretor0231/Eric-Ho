import socket
import random
import sys

tie = False

grid = ['-', '-', '-', '-', '-', '-', '-', '-', '-']

def get_board():
    global grid
    board = "%c%c%c|%c%c%c|%c%c%c"%(grid[0],grid[1],grid[2],grid[3],grid[4],grid[5],grid[6],grid[7],grid[8])
    return board


#generate random position by client
def get_randomposition():
    while True:
        m = random.randint(1,9)
        if m not in [3,6,8]:
            break
    return m

x = get_randomposition()
grid[x-1] = 'X'
print("Client: "+get_board())

def get_nextposition(tempgrid):
    if tempgrid[4] == '-':
        return 5
    elif tempgrid[1] == '-':
        return 2
    elif tempgrid[3] == '-':
        return 4
    elif tempgrid[5] == '-':
        return 6
    elif tempgrid[7] == '-':
        return 8
    elif tempgrid[0] == '-':
        return 1
    elif tempgrid[2] == '-':
        return 3
    elif tempgrid[6] == '-':
        return 7
    elif tempgrid[8] == '-':
        return 9
    else:
        return 0

def gametie(tempgrid):
    for ch in tempgrid:
        if ch == '-':
            return False
    return True


clientsocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

host = sys.argv[1]
port = 707

clientsocket.connect((host,port))

clientmsg = str(x)
clientsocket.send((clientmsg+get_board()+"\n").encode("ascii"))

while True:
    servermsg = clientsocket.recv(1024).decode('ascii')
    pos = int(servermsg[0])
    grid[pos - 1] = 'O'
    print("Server: "+get_board())
    x = get_nextposition(grid)
    grid[x-1] = 'X'
    print("Client: "+get_board())
    clientmsg = x
    clientsocket.send((str(clientmsg)+get_board()+"\n").encode('ascii'))
    if gametie(grid):
        clientsocket.send(("tie\n").encode('ascii'))
        break

print("tie")
clientsocket.close()
