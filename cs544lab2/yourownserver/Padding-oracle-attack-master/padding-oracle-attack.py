# Padding Oracle Attack against PKCS7
# From https://github.com/mpgn/Padding-oracle-attack
# martial puygrenier

####################################
# CUSTOM YOUR RESPONSE ORACLE HERE #
####################################
''' the function you want change to adapte the result to your problem '''
def test_validity(response):

    # oracle repsonse with data in the DOM
    data = response
    if data.find("Padding Error") == -1:
        return 1
    return 0

################################
# CUSTOM YOUR ORACLE HTTP HERE #
################################
import socket
HOST = 'host.com'
PORT =  50100
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))
def call_oracle(up_cipher, s):
    s.send(up_cipher)
    data = s.recv(1024)
    return 1, data
    
# comment the lines
connection.close()
print "[+] HTTP ", response.status, response.reason