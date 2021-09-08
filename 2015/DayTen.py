from get_input import get

input = get(10)

seq = input

for i in range(50):
    group = []
    group.append([seq[0]])
    for n in seq[1:]:
        if n == group[-1][0]:
            group[-1].append(n)
        else: 
            group.append([n])

    seq = ''
    for g in group:
        seq += str(len(g)) + g[0]

    if i == 39:
        print(len(seq))
    if i == 49:
        print(len(seq))

