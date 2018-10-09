import pyaes

def solve_block(iv, c1, iv_o, padding):
    results = []
    p = padding
    # for example, from 10 to 16
    for i in range(padding + 1, len(iv)+1):
        shift = p^p+1 # so chained, 7^8^9^10, etc
        # shift all IV from 16 to current padding (16->10)
        for j in range(1,i):
            iv[-j] = iv[-j]^shift
        # for current test position, try all possible values
        for j in range(0,256):
            iv[-i] = j
            if oracle(ints_to_bytes(iv+c1)):
                # the plaintext is our solution value, xor'd with
                # original iv, xor'd with current padding
                results.insert(0, j^iv_o[-i]^i)
                break
        p += 1
    return ''.join([chr(r) for r in results])
                            
def bytes_to_ints(ctext): return [ord(c) for c in ctext]

def ints_to_bytes(l): return ''.join([chr(l1) for l1 in l])

# the oracle is a black back here; all it's returning is a boolean
# saying whether the padding values are correct
def oracle(ctext): return check_padding(bytes_to_ints(decrypt(ctext)))

def check_padding(p):
    return p[-p[-1]:] == [p[-1] for _ in range(p[-1])]

# straightforward AES decrypt, including padding
def decrypt(ctext):
    iv = ''.join([chr(0x00) for _ in range(16)])
    key = 'abcdefghijklmnop'
    aes = pyaes.AESModeOfOperationCBC(key, iv = iv)
    plaintext = ''
    for i in range(0,len(ctext),16):
        plaintext += aes.decrypt(ctext[i:i+16])
    return plaintext

# add PKCS#5 padding, then encrypt
def encrypt_with_pad(message):
    def mod(s): return 16 - (len(s) % 16)
    def pad(s): return s + [mod(s) for _ in range(mod(s))]

    iv = ''.join([chr(0x00) for _ in range(16)])
    key = 'abcdefghijklmnop'
    aes = pyaes.AESModeOfOperationCBC(key, iv = iv)
    ctext = ''
    m = ints_to_bytes(pad(bytes_to_ints(message)))
    for i in range(0,len(m),16):
        ctext += aes.encrypt(m[i:i+16])
    return iv + ctext

# iterate through all blocks, finding padding if any, and solving each
def solve_complete(ctext):
    sol = ''
    n = bytes_to_ints(ctext)
    def find_padding(iv, c1):
        i = 0
        while oracle(ints_to_bytes(iv+c1)):
            i += 1
            iv[i] += 1
        return 16-i
    p = find_padding(n[-32:-16], n[-16:])
    for i in range(len(n), 0,-16):
        sol = solve_block(n[i-32:i-16], n[i-16:i], n[i-32:i-16], p) + sol
        p = 0
    return sol

