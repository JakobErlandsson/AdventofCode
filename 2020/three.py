from get_input import get 
from numpy import prod
p_input = get(3).split("\n")

width = len(p_input[0])
height = len(p_input)

slopes = [(1,1), (3,1), (5,1), (7,1), (1,2)]

n_trees = []
for s in slopes:
    dx = s[0]
    dy = s[1]
    t = 0
    x = 0
    y = 0
    while y < height:
        if p_input[y][x%width] == '#':
            t += 1
        x += dx
        y += dy
    n_trees.append(t)

print("Day three part one: {}".format(n_trees[1]))
print("Day three part two: {}".format(prod(n_trees)))



