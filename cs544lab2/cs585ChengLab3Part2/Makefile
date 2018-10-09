all: client server

client: client.c
	gcc client.c -o testclient

server: server.c
	gcc -pthread server.c -o testserver

clean:
	rm -rf testclient testserver
