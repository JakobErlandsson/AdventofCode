from get_input import get
import numpy as np
p_input = get(13).split('\n')

timestamp = int(p_input[0])
buses = p_input[1].split(',')
min_diff = timestamp
min_idx = 0

for b in buses:
    if b == 'x':
        continue
    next = 0
    b = int(b)
    while next < timestamp:
        next += b
    diff = next - timestamp
    if diff < min_diff:
        min_diff = diff
        min_idx = b

ans = min_diff * min_idx
print("Day thirteen part one: {}".format(ans))

l = [(int(b) - i, int(b)) for (i, b) in enumerate(buses) if b != 'x']
mod, xs = map(list, zip(*l))

def eea(a, b):
    if b == 0:
        return (1, 0)
    (x, y) = eea(b, a % b)
    k = a // b
    return (y, x - k * y)

def crt(a, b, n1, n2):
    x, y = eea(n1, n2)
    m = n1 * n2
    n = b * x * n1 + a * y * n2
    return (n % m + m) % m


x = xs[0]
m = mod[0]
for i in range(len(xs)-1):
    x = crt(xs[i+1], x, mod[i+1], m)
    m = m*mod[i+1]

print("Day thirteen part two: {}".format(x))
