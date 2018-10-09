import argparse
import socket
import sys
import os
import binascii
import re
from Crypto.Util.number import *
from aes import *
from Crypto.PublicKey import RSA
#
# 00000000  a5 3d 31 fe 2c 49 27 f3  5b 78 b0 14 d0 cd 69 a8   .=1.,I'. [x....i.
# 00000010  97 4a 0e b8 56 b2 56 b1  58 b6 e4 a3 41 80 59 dc   .J..V.V. X...A.Y.
# 00000020  91 84 d7 9a c1 0e 1b b7  97 83 a1 8b b0 ec e5 11   ........ ........
# 00000030  41 be bb eb b0 2b 4a 04  74 ce 20 4b 73 c8 e4 e5   A....+J. t. Ks...
# 00000040  12 b5 fa 0b 19 6f 6b 78  e5 25 79 a0 22 19 76 c8   .....okx .%y.".v.
# 00000050  a3 99 35 c7 e2 b0 c6 59  27 e9 ae 3d b7 63 3c 0c   ..5....Y '..=.c<.
# 00000060  b5 0a b6 59 91 c1 7e c5  2e c0 7b 5a 4e 95 fd 13   ...Y..~. ..{ZN...
# 00000070  88 e9 98 83 fb eb f3 5e  7a 36 0e 62 0c a1 66 ad   .......^ z6.b..f.
# 00000080  df 7a 29 4d e5 34 de 56  61 a4 3c bd eb 23 f8 fd   .z)M.4.V a.<..#..
# 00000090  b4 a7 03 5d da ca 07 aa  c6 39 71 eb 6f 32 2e 4b   ...].... .9q.o2.K
# 000000A0  44 97 20 7c d4 45 a7 0b  54 8d d4 a6 cc fc f2 3b   D. |.E.. T......;
# 000000B0  fa 14 70 47 b2 2f 53 72  3f 52 b9 47 87 8e 8c 77   ..pG./Sr ?R.G...w
#     00000000  cd a1 5a e6 a0 42 31 1c  b0 35 e4 7e 55 36 0e c5   ..Z..B1. .5.~U6..
#     00000010  a7 1b 5d 37 c9 1c 7e fc  58 61 51 1c c2 06 87 cc   ..]7..~. XaQ.....
#     00000020  53 11 10 db ca 69 67 68  da d3 24 c6 80 2a 79 69   S....igh ..$..*yi
#     00000030  97 b7 75 19 f2 77 62 29  a0 f9 31 d6 ef 42 0a ec   ..u..wb) ..1..B..

chiptext = "a53d31fe2c4927f35b78b014d0cd69a8974a0eb856b256b158b6e4a3418059dc9184d79ac10e1bb79783a18bb0ece51141bebbebb02b4a0474ce204b73c8e4e512b5fa0b196f6b78e52579a0221976c8a39935c7e2b0c65927e9ae3db7633c0cb50ab65991c17ec52ec07b5a4e95fd1388e99883fbebf35e7a360e620ca166addf7a294de534de5661a43cbdeb23f8fdb4a7035ddaca07aac63971eb6f322e4b4497207cd445a70b548dd4a6ccfcf23bfa147047b22f53723f52b947878e8c77"

servertext = "cda15ae6a042311cb035e47e55360ec5a71b5d37c91c7efc5861511cc20687cc531110dbca696768dad324c6802a796997b77519f2776229a0f931d6ef420aec"



FinalAES = "11000011111111001011011110111000010011101110100001011001011011100011010001000110100001000010010001111100110111010110101101111010"



AESKeyInt2 = int(FinalAES[:128], 2)
AESKey2 = long_to_bytes(AESKeyInt2)
aes2 = AESCipher(AESKey2)
print(FinalAES)
print(AESKeyInt2)
print(AESKey2)
print(aes2)
print(len(FinalAES))
serverInt = int(chiptext[128:],16)
serverBytes = long_to_bytes(serverInt)
print(serverBytes)
print aes2.decrypt(servertext[:256])
print aes2.decrypt(serverBytes)
