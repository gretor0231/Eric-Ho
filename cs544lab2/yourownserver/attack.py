import argparse
import socket
import sys
import os
import binascii
import time
from Crypto.Util.number import *
from aes import *
from Crypto.PublicKey import RSA

serveranser = "52305b8f0cdb805b5e1598e846d2a908"

chiptext = "1622c9ae6005fff3b8450381f1adcef004b07ee3152eb6656c20244191d98a005eb17d85d3576e1585e3f12e1e660288c0d2d2de6d2e3f2dd7213f50dd21412e74594679d72fae6803a7d35ae56bac1f1cae0f5e770e82c11b4a6ae8fed5696ca04fff11f998e9d5d8b7d90b86cfcf99423e41ace46307e844c028b8dc99a4679183e483837cb9426eaff9fe184f537a"

# Using AES key db:de:2c:b8:f1:8b:65:ec:f1:de:fc:95:fe:cb:21:c9
# Sending: "Hihihihi~baby!"
# HIHIHIHI~BABY!
#
#
# 00000000  16 22 c9 ae 60 05 ff f3  b8 45 03 81 f1 ad ce f0   ."..`... .E......
# 00000010  04 b0 7e e3 15 2e b6 65  6c 20 24 41 91 d9 8a 00   ..~....e l $A....
# 00000020  5e b1 7d 85 d3 57 6e 15  85 e3 f1 2e 1e 66 02 88   ^.}..Wn. .....f..
# 00000030  c0 d2 d2 de 6d 2e 3f 2d  d7 21 3f 50 dd 21 41 2e   ....m.?- .!?P.!A.
# 00000040  74 59 46 79 d7 2f ae 68  03 a7 d3 5a e5 6b ac 1f   tYFy./.h ...Z.k..
# 00000050  1c ae 0f 5e 77 0e 82 c1  1b 4a 6a e8 fe d5 69 6c   ...^w... .Jj...il
# 00000060  a0 4f ff 11 f9 98 e9 d5  d8 b7 d9 0b 86 cf cf 99   .O...... ........
# 00000070  42 3e 41 ac e4 63 07 e8  44 c0 28 b8 dc 99 a4 67   B>A..c.. D.(....g
# 00000080  91 83 e4 83 83 7c b9 42  6e af f9 fe 18 4f 53 7a   .....|.B n....OSz
#     00000000  52 30 5b 8f 0c db 80 5b  5e 15 98 e8 46 d2 a9 08   R0[....[ ^...F...

servertext = "52305b8f0cdb805b5e1598e846d2a908"
FinalAES = ""


# To get first 128 bits, (2**64)*16B = 256exa bytes of data
AESkeyIn = chiptext[:256]
# change hex to int number, it use int function
c = int(AESkeyIn,16);

# load server's public key
serverPublicKeyFileName = "serverPublicKey"
f = open(serverPublicKeyFileName,'r')
key = RSA.importKey(f.read())
n, e = key.n, key.e
MESSAGE_LENGTH = 15

# multiplied by 2^{be} mod n where b is 127
for b in range(127, -1, -1):

    #b = 127

    #change long int to hex number,c1 bytes as AES key
    c1 = (c * (2 ** (b * e)) % n)
    # print(len(c1))
    c1bytes = long_to_bytes(c1)
    # print("hihibaby")
    # print(len(c1bytes))

    AESKeyStr = "1" + FinalAES + "0" * b

    AESKeyInt = int(AESKeyStr, 2)
    AESKey = long_to_bytes(AESKeyInt)
    print(AESKey)

    # if AESkey less then 16 bytes, add hex b'\x00' * less bytes numbers
    if len(AESKey) < 16:
        AESKey = AESKey + b'\x00' * (16 - len(AESKey) % 16)





        # Create a TCP/IP socket
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        # Connect the socket to the port where the server is listening
    server_address = ("127.0.0.1", 1000)
    sock.connect(server_address)

    msg =""
    msg+= c1bytes

    aes = AESCipher(AESKey)
    try:
        # Send dat
        message = "test"
        msg += aes.encrypt(message)
        # msg: AES key encrypted by the public key of RSA  + message encrypted by the AES key
        sock.sendall(msg)

        # Look for the response
        amount_received = 0
        amount_expected = len(message)

        if amount_expected % 16 != 0:
            amount_expected += (16 - (len(message) % 16))

        answer = ""

        if amount_expected > amount_received:
            print("waiting for server")
            while amount_received < amount_expected:
                data = sock.recv(MESSAGE_LENGTH)
                amount_received += len(data)
                answer += data
            # print("answer is :"+ answer)
            try:
                print aes.decrypt(answer)
                if message.upper() == aes.decrypt(answer).strip():
                    FinalAES = "1" + FinalAES
                else:
                    FinalAES = "0" + FinalAES
                print(FinalAES)
                print(len(FinalAES))

            except ValueError:
                print("Oops!  That was connection Error.  Try again...")


    finally:
        sock.close()
        time.sleep(2)



#
#
#
#
AESKeyInt2 = int(FinalAES[:128], 2)
AESKey2 = long_to_bytes(AESKeyInt2)
print(AESKey2)
aes2 = AESCipher(AESKey2)
print aes2.decrypt(servertext)