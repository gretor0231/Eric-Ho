######for loop##################################################################################
##from Crypto.Util.number import long_to_bytes
from Crypto.Util.number import *
import argparse
import socket
import sys
import os
from aes import *
from Crypto.PublicKey import RSA
import time


# Handle command-line arguments
parser = argparse.ArgumentParser()
parser.add_argument("-ip", "--ipaddress", help='ip address where the server is running', required=True)
parser.add_argument("-p", "--port", help='port where the server is listening on', required=True)
#parser.add_argument("-m", "--message", help='message to send to the server', required=True)

#parser.add_argument("-b", "--block", help='the 32-byte block sent to the server', required=True)
#parser.add_argument("-id", "--keyid", help='unique key id', required=True)
args = parser.parse_args()

################################################################################################
##my pcap
################################################################################################
#00000000  b9 17 68 27 5b d4 0d 82  f5 8e 29 52 4d a4 14 9c   ..h'[... ..)RM...
#00000010  e8 07 dd 16 03 7f 16 32  d2 26 ee f5 c3 f7 c4 a3   .......2 .&......
#00000020  52 3d a7 d5 40 4e 41 60  c2 09 18 6b 64 50 b2 4a   R=..@NA` ...kdP.J
#00000030  54 23 c4 30 62 f7 b4 b0  a1 36 be 0d 79 e3 75 2b   T#.0b... .6..y.u+
#00000040  dc c4 e3 22 19 77 c7 1a  5c 00 11 d7 84 5b 74 67   ...".w.. \....[tg
#00000050  63 d8 4d 85 1a 5e 1f ee  c8 a2 0d ea 00 3b 8e ec   c.M..^.. .....;..
#00000060  1d 82 36 07 cc b0 db 63  9e 5e 9c 26 0e 16 91 17   ..6....c .^.&....
#00000070  bd 4b 53 83 16 47 1d 78  f4 65 04 92 15 9c b3 da   .KS..G.x .e......
#00000080  e2 b9 a5 18 60 2e 5b c8  23 72 de 80 1a c7 0c 70   ....`.[. #r.....p
#00000090  4f cf e5 96 ac 5f 72 ec  e0 48 74 26 47 93 ad f2   O...._r. .Ht&G...
#000000A0  32 48 b5 a4 b4 11 4e 9b  f7 ad 7a f2 5d b8 22 d7   2H....N. ..z.].".
#000000B0  9e bd df bd 24 7c 7b 1e  12 47 27 37 a8 d7 60 f6   ....$|{. .G'7..`.

#00000000  53 45 9c b2 44 10 eb 3a  12 7f 2a e5 1e de 16 bc   SE..D..: ..*.....
#00000010  3d 45 41 55 3f d5 95 cc  f0 07 94 73 f0 bb fb 32   =EAU?... ...s...2
#00000020  c1 5e 1e 9b 7d 4b 30 dd  ef 53 e8 62 44 0c 6e 4e   .^..}K0. .S.bD.nN
#00000030  75 58 87 ed cd 03 4b c8  d8 12 fa e4 e6 04 d0 89   uX....K. ........

###############################################################################################
##local pcap
###############################################################################################
#00000000  14 2e e0 c1 46 31 f8 29  b2 2a 2d c7 f5 eb 1f bf   ....F1.) .*-.....
#00000010  a2 2d cc 8e 89 45 cd 6f  20 68 da 66 67 e7 3f 86   .-...E.o  h.fg.?.
#00000020  ef f9 e4 c6 c0 df 25 66  f0 81 cc a1 58 c0 f8 4f   ......%f ....X..O
#00000030  21 51 88 c6 3c 3c b8 e0  a1 1f 08 56 2a 32 c0 4c   !Q..<<.. ...V*2.L
#00000040  ec b0 e1 f2 7c 99 2f f7  9c a7 87 47 2f d4 b0 c0   ....|./. ...G/...
#00000050  ce bf 08 cd 68 cc be dc  49 cf 7e 87 ac 6a 61 94   ....h... I.~..ja.
#00000060  26 ba 5d c6 4d d2 1e 39  7c 76 ec 9e e2 e1 b5 b1   &.].M..9 |v......
#00000070  35 5e 19 b3 af a6 0f e2  e9 8e dd 1d 9f eb 74 af   5^...... ......t.
#00000080  31 b1 57 94 00 57 5a a4  d0 58 6b 5b cf e0 95 f7   1.W..WZ. .Xk[....
#00000090  d3 55 8c 80 e3 2a 59 c4  8b 6c dc 02 ad 83 3e ff   .U...*Y. .l....>.

#00000000  ee ff bc 5b f5 94 37 a2  69 da f3 87 d7 a5 61 ed   ...[..7. i.....a.
#00000010  73 d3 ef 00 5f ad a6 97  1f 1f 60 05 e7 f1 71 a8   s..._... ..`...q.


#AESKey = os.urandom(16)
#print "Using AES key " + ':'.join(x.encode('hex') for x in AESKey)

# load server's public key
serverPublicKeyFileName = "serverPublicKey"
f = open(serverPublicKeyFileName,'r')
key = RSA.importKey(f.read())
n, e = key.n, key.e
MESSAGE_LENGTH = 15


##rsaCipherclinet="068cbe8e5e1b9dfc5e3e78ea94c8c6b3b51129688973c9670a926b7079bbfaf3b4ac76a6c58ea62565920024e8b6ac74cb0ad5150df09807aa5f2e1350049d6872ef5befb3877248ba5d11060cdd5435034515570778c29359f972bae369745d377de1c647f4fa86b0e97ee12857796a7a855b89bb5b2aa93948e899410aad2ac970534ee6c1e9e0638da20189dcd6d31715e5d427e997b668408f953d7be3362185f2ba2c2b1f0ed2abdc1352978bd4"
rsaCict="b91768275bd40d82f58e29524da4149ce807dd16037f1632d226eef5c3f7c4a3523da7d5404e4160c209186b6450b24a5423c43062f7b4b0a136be0d79e3752bdcc4e3221977c71a5c0011d7845b746763d84d851a5e1feec8a20dea003b8eec1d823607ccb0db639e5e9c260e169117bd4b538316471d78f4650492159cb3dae2b9a518602e5bc82372de801ac70c704fcfe596ac5f72ece04874264793adf23248b5a4b4114e9bf7ad7af25db822d79ebddfbd247c7b1e12472737a8d760f6"
##BWserver="d1f484f3c976b1f26576a58e381eed90ae808c7b3fc205a2e2f968c6c527ccfcb28fbfb50ad89caf89a5a9d19421e82f"
BWserver="53459cb24410eb3a127f2ae51ede16bc3d4541553fd595ccf0079473f0bbfb32c15e1e9b7d4b30ddef53e862440c6e4e755887edcd034bc8d812fae4e604d089"

##rsaCict="142ee0c14631f829b22a2dc7f5eb1fbfa22dcc8e8945cd6f2068da6667e73f86eff9e4c6c0df2566f081cca158c0f84f215188c63c3cb8e0a11f08562a32c04cecb0e1f27c992ff79ca787472fd4b0c0cebf08cd68ccbedc49cf7e87ac6a619426ba5dc64dd21e397c76ec9ee2e1b5b1355e19b3afa60fe2e98edd1d9feb74af31b1579400575aa4d0586b5bcfe095f7d3558c80e32a59c48b6cdc02ad833eff"
##Bser="eeffbc5bf59437a269daf387d7a561ed73d3ef005fada6971f1f6005e7f171a8"

rsaCC=rsaCict[:256]
rsaCipher=int(rsaCC,16)
#remn="100" 
#remn="0001"
###remn="1110110111110101010000110111100100101011100001"
remn=""

for b in range(127,-1,-1):

    ###b=81
    # Create a TCP/IP socket
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # Connect the socket to the port where the server is listening
    server_address = (args.ipaddress, int(args.port))
    sock.connect(server_address)

    #b=127
    rsaCipherbs=(rsaCipher*(2**(b*e)))%n
    rsaCipherbs2=long_to_bytes(rsaCipherbs) #10 to 16

    msg = ""

    msg += rsaCipherbs2


    ak= "1" + remn +"0" *b # binary
    ###ak= "1" + remn +"0" *b # binary
    akten=int(ak,2)
    akst=long_to_bytes(akten)

    # if len(akst)<16:
    #     akst=akst +b'\x00'*(16- len(akst) %16)

    ##print(akst)

    ##msg = ""
    ##msg += rsaCipherbs2

    #encryptedKey = str(key.encrypt(AESKey, 16)[0])
    #msg += encryptedKey


    ##aes = AESCipher(aeskey)

    aes = AESCipher(akst)

    try:
      # Send data
      #message = str(args.message)
      message = "joseph"
      msg += aes.encrypt(message)
      print 'Sending: "%s"' % message
      # msg: AES key encrypted by the public key of RSA  + message encrypted by the AES key
      sock.sendall(msg)

      # Look for the response
      amount_received = 0
      amount_expected = len(message)
      
      if amount_expected % 16 != 0:
        amount_expected += (16 - (len(message) % 16))

      answer = ""

      if amount_expected > amount_received:
        while amount_received < amount_expected:
          data = sock.recv(MESSAGE_LENGTH)
          amount_received += len(data)
          answer += data
        #rint(answer)
        # try:
        print aes.decrypt(answer)
        if aes.decrypt(answer).strip() == "JOSEPH":
            print("1")
            remn="1"+remn
            #print("remn")

        else:
            print("Server send back junk")
            remn="0"+remn
                #print("0")
        # except ValueError:
        #     print("oh no!")

        print(remn)
        print(len(remn))

    finally:
      sock.close()
      time.sleep(2)


JfinalInt = int(remn[:128], 2)
JfinalByte = long_to_bytes(JfinalInt)
aesJ = AESCipher(JfinalByte)

MessageInt = int(rsaCict[128:],16)
MessageByte = long_to_bytes(MessageInt)
print aesJ.decrypt(MessageByte)


