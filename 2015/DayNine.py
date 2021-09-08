from get_input import get
from itertools import permutations

input = get(9).split('\n')

locations = set()

for line in input:
    line = line.split(' ')
    locations.add(line[0])
    locations.add(line[2])

locations = list(locations)

distance_matrix = []
for i in range(len(locations)):
    distance_matrix.append([0]*len(locations))

for line in input:
    line = line.split(' ')
    orig = locations.index(line[0])
    dest = locations.index(line[2])
    dist = line[4]
    distance_matrix[orig][dest] = int(dist)
    distance_matrix[dest][orig] = int(dist)

perms = list(permutations(range(len(locations))))

shortest = 1000000000
longest = 0

for p in perms:
    dist = 0
    for i in range(len(p)-1):
        dist += distance_matrix[p[i]][p[i+1]]
    shortest = min(shortest,dist)
    longest = max(longest, dist)


print(shortest)
print(longest)

