# generate random string of n chars

import random

def randomFileName(n):
    return ''.join(random.choices('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', k=n))

if __name__ == '__main__':
    print(randomFileName(10))