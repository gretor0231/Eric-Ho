from Crypto.PublicKey import RSA
from Crypto.Signature import PKCS1_v1_5 
import hashlib
from Crypto.Hash import SHA256 
from base64 import b64decode
from base64 import b64encode

class RSACipher():

    def __init__(self):
        """
        Generate a RSA key pair for server
        """
        publicKeyFileName = "serverPublicKey"
        privateKeyFileName = "serverPrivateKey.pem"
        try:
            f = open(privateKeyFileName, 'rb')
            self.keys = RSA.importKey(f.read())
        except:
            self.keys = RSA.generate(1024)
            self.publickey = self.keys.publickey()
            # export public and private keys
            privHandle = open(privateKeyFileName, 'wb')
            privHandle.write(self.keys.exportKey('PEM'))
            privHandle.close()
            
            pubHandle = open(publicKeyFileName, 'wb')
            pubHandle.write(self.keys.publickey().exportKey())
            pubHandle.close()
        self.publickey = self.keys.publickey()
        
    def sign(self, message):
        """-
        Decrypt a ciphertext
        """
        digest = SHA256.new() 
        signer = PKCS1_v1_5.new(self.keys)
        digest.update(message) 
        sign = signer.sign(digest) 
        return b64encode(sign)

    def check(self, message, signature, public_key_loc):
        """
        Encrypt a message
        """
        pub_key = open(public_key_loc, "r").read()
        rsakey = RSA.importKey(pub_key) 
        signer = PKCS1_v1_5.new(rsakey) 
        digest = SHA256.new() 
        digest.update(message) 
        if signer.verify(digest, b64decode(signature)):
            return True
        return False
