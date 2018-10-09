import Crypto
import ast
import os
import argparse
import sys
from rsa import *
from Crypto.Util.number import *
from base64 import b64encode

parser = argparse.ArgumentParser()
parser.add_argument("-m", "--message")
args = parser.parse_args()

rsa = RSACipher()
print rsa.sign(args.message)


