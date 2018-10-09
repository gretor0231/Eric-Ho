import socket
import random
import sys

grid = ['-', '-', '-', '-', '-', '-', '-', '-', '-']

def get_board():
    global grid
    board = "%c%c%c|%c%c%c|%c%c%c"%(grid[0],grid[1],grid[2],grid[3],grid[4],grid[5],grid[6],grid[7],grid[8])
    return board

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

def gametie(tempgrid):
    for ch in tempgrid:
        if ch == '-':
            return False
    return True


serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

host = sys.argv[1]
port = 707

serversocket.bind((host,port))

serversocket.listen(100)

#connection from client socket
c, addr = serversocket.accept()

while True:
    clientmsg = c.recv(1024).decode('ascii')
    grid[int(clientmsg[0]) - 1] = 'X'
    print("Client: "+get_board())
    if gametie(grid):
        print("tie")
        c.send(("tie").encode('ascii'))
        break
    x = get_nextposition(grid)
    grid[x-1] = 'O'
    print("Server: "+get_board())
    c.send((str(x)+get_board()+"\n").encode('ascii'))

c.close()
serversocket.close()
