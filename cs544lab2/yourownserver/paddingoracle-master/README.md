# paddingoracle
Python Code to Implement Padding Oracle Attack

Uses pyaes (https://github.com/ricmoo/pyaes) to show how a padding oracle attack works. I've added padding and a "padding oracle" to return whether the padding is correct for any ciphertext.

Usage:

```python
from my_oracle import solve_complete, encrypt_with_pad
plaintext = 'I love to crack cbc mode encryption'
ctext = encrypt_with_pad(plaintext)

solve_complete(ctext)
```