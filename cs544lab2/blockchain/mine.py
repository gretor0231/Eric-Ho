from Crypto.Hash import SHA256
import ast
import os
import argparse
import sys
from Crypto.Util.number import *

parser = argparse.ArgumentParser()
parser.add_argument("-m", "--message")
parser.add_argument("-z", "--numzeros")
args = parser.parse_args()

digest = SHA256.new()
digest.update(args.message)
while True:
    i = bytes_to_long(os.urandom(16))
    newdigest = digest
    newdigest.update(str(i))
    #print "i is " + str(i)
    #print newdigest.hexdigest()
    z = int(args.numzeros)
    zeros = "0" * z
    if zeros == newdigest.hexdigest()[:z]:
        print "Did it"
        print "The hash of..."
        print args.message + str(i)
        print "...is..."
        print newdigest.hexdigest()
        break;
