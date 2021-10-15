from get_input import get
from itertools import combinations

containers = sorted([int(c) for c in get(17).split('\n')])
amount = 150

for i in range(1,len(containers)):
    if sum(containers[:-i]) < amount:
        longest = len(containers)-i
        break
    
for i in range(1, len(containers)):
    if sum(containers[-i:]) > amount:
        shortest = i
        break

n_matches = 0
for size in range(shortest, longest+1):
    n_matches += sum([sum(c) == amount for c in combinations(containers, size)])
    if size == shortest:
        second_answer = n_matches

print(n_matches)
print(second_answer)