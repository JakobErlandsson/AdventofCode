from get_input import get
from math import sqrt, prod, ceil
from itertools import islice
from time import time_ns

input = int(get(20))

def divisors(x):
    n = 1
    while n <= x:
        if x % n == 0:
            yield n 
        n += 1   

def divisors_to(x, n):
    l = set()
    for i in range(1,n+1):
        if x % i == 0:
            l.add(x//i)

    return list(l)

    
    


def prime_factors(x):
    if x == 1:
        yield 1
    while x % 2 == 0:
        x /= 2
        yield 2
    for i in range(3, int(sqrt(x))+1, 2):
        while x % i == 0:
            x /= i
            yield i

    if x > 2:
        yield int(x)
    

# house_nr = 0
# n_presents = 0
# while n_presents < input:
#     house_nr += 1
#     if house_nr == 1:
#         print('House 1 got 10 presents.')
#         continue
#     factors = [n for n in prime_factors(house_nr)]
#     factors = list(set([(n, factors.count(n)) for n in factors]))
#     n_presents = prod([int((pow(p,k+1)-1)/(p-1)) for (p,k) in factors]) * 10
#     print('House {} got {} presents.'.format(house_nr, n_presents))

house_nr = 1
n_presents = 0
while n_presents < input:
    divs = divisors_to(house_nr, 50)
    n_presents = sum([i for i in divs]) * 11
    print('House {} got {} presents.'.format(house_nr, n_presents))
    house_nr += 1
