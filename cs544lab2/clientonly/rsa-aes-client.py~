import argparse
import socket
import sys
import os
from aes import *
from Crypto.PublicKey import RSA

# Handle command-line arguments
parser = argparse.ArgumentParser()
parser.add_argument("-ip", "--ipaddress", help='ip address where the server is running', required=True)
parser.add_argument("-p", "--port", help='port where the server is listening on', required=True)
parser.add_argument("-m", "--message", help='message to send to the server', required=True)

#parser.add_argument("-b", "--block", help='the 32-byte block sent to the server', required=True)
#parser.add_argument("-id", "--keyid", help='unique key id', required=True)
args = parser.parse_args()


# Create a TCP/IP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Connect the socket to the port where the server is listening
server_address = (args.ipaddress, int(args.port))
sock.connect(server_address)

AESKey = os.urandom(16)
print "Using AES key " + ':'.join(x.encode('hex') for x in AESKey)

# load server's public key
serverPublicKeyFileName = "serverPublicKey"
f = open(serverPublicKeyFileName,'r')
key = RSA.importKey(f.read())
n, e = key.n, key.e
MESSAGE_LENGTH = 15

msg = ""
encryptedKey = str(key.encrypt(AESKey, 16)[0])
msg += encryptedKey

aes = AESCipher(AESKey)
try:
  # Send data
  message = str(args.message)
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

    print aes.decrypt(answer)

finally:
  sock.close()




