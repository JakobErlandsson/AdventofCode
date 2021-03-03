from get_input import get
p_input = [int(i) for i in get(15).split(',')]

spoken = {}
for (i, p) in enumerate(p_input[:-1]):
    spoken[p] = i + 1

last  = p_input[-1]
for i in range(len(spoken) + 2, 30000001):
    if i == 2021:
        print("Day fifteen part one: {}".format(last))
    if last in spoken:
        sp = i-1 - spoken[last]
    else:
        sp = 0
    spoken[last] = i-1
    last = sp

print("Day fifteen part one: {}".format(last))