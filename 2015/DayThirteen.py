from get_input import get
from itertools import permutations

input = get(13).split('\n')

def find_highest(include_me=False):
    guests = []

    for line in input:
        line = line.split(' ')
        guests.extend([line[0], line[-1][:-1]])

    guests = sorted(list(set(guests)))
    if include_me:
        guests.append('Me')

    happiness_matrix = []
    for _ in range(len(guests)):
        happiness_matrix.append([0]*len(guests))

    for line in input:
        line = line.split(' ')
        val = int(line[3])
        if line[2] == 'lose':
            val *= -1
        i1 = guests.index(line[0])
        i2 = guests.index(line[-1][:-1])
        happiness_matrix[i1][i2] = val

    perms = [list(p) for p in permutations(range(len(guests)))]


    highest = -1000000000
    for p in perms:
        p.append(p[0])
        happ = [happiness_matrix[p[i]][p[i+1]] for i in range(len(p)-1)]
        p = list(reversed(p))
        happ_rev = [happiness_matrix[p[i]][p[i+1]] for i in range(len(p)-1)]
        highest = max(highest, sum(happ) + sum(happ_rev))
    return highest
    

print(find_highest())
print(find_highest(True))

