from get_input import get
import re
import itertools
p_input = get(14).split('\n')

memory = {}

for l in p_input:
    l = l.replace(" ", "").split('=')
    if l[0] == 'mask':
        mask = l[1]
        and_mask = int(mask.replace('X', '1'), 2)
        or_mask = int(mask.replace('X', '0'), 2)
    else:
        addr = re.findall('\d+', l[0])[0]
        val = and_mask & int(l[1]) | or_mask
        memory[addr] = int(val)

ans = sum(memory.values())

print("Day fourteen part one: {}".format(ans))

memory = {}

for l in p_input:
    l = l.replace(" ", "").split('=')
    if l[0] == 'mask':
        mask = l[1]
        or_mask = int(mask.replace('X', '0'), 2)
    else:
        val = int(l[1])
        addr = int(re.findall('\d+', l[0])[0])
        addr = addr | or_mask
        floats = [i for (i,j) in enumerate(mask) if j == 'X']
        addr = "".join(['0']*(len(mask)-len(format(addr, 'b')))) + format(addr, 'b')
        bits = list(itertools.product([0,1], repeat=len(floats)))
        addrs = []
        for b in bits:
            a = list(addr)
            for (i, f) in enumerate(floats):
                a[f] = str(b[i])
            a = int("".join(a), 2)
            memory[a] = val



ans = sum(memory.values())
print("Day fourteen part two: {}".format(ans))