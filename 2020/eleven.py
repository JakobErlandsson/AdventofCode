from get_input import get
import numpy as np
p_input = list(get(11).replace('\n', ''))

width = len(get(11).split('\n')[0])
height = len(get(11).split('\n'))

def valid(index, d, step):
    (x, y) = d
    i = index // width
    j = index % width
    return i + x*step >= 0 and i +x*step < height and j + y*step >= 0 and j + y*step < width


def neighbours(index, first=True):
    i = index // width
    j = index % width
    ns = [(i-1, j-1), (i, j-1), (i+1, j-1), (i-1, j), (i+1, j), (i-1, j+1), (i, j+1), (i+1, j+1)]
    dirs = [(-1,-1), (0, -1), (1, -1), (-1, 0), (1, 0), (-1, 1), (0, 1), (1, 1)]
    if i == 0:
        ns = [el for el in ns if el not in {(i-1, j-1), (i-1, j), (i-1, j+1)}]
        dirs = [el for el in dirs if el not in {(-1, -1), (-1, 0), (-1, 1)}]
    if j == 0:
        ns = [el for el in ns if el not in {(i-1, j-1), (i, j-1), (i+1, j-1)}]
        dirs = [el for el in dirs if el not in {(-1, -1), (0, -1), (1, -1)}]
    if i == height-1:
        ns = [el for el in ns if el not in {(i+1, j-1), (i+1, j), (i+1, j+1)}]
        dirs = [el for el in dirs if el not in {(1, -1), (1, 0), (1, 1)}]
    if j == width-1:
        ns = [el for el in ns if el not in {(i-1, j+1), (i, j+1), (i+1, j+1)}]
        dirs = [el for el in dirs if el not in {(-1, 1), (0, 1), (1, 1)}]
    
    if first:
        return ns

    ds = []
    step = 1
    grid = np.array_split(p_input, height)
    found = [False]*len(dirs)
    while True:
        if all(not valid(index, d, step) for d in dirs) or all(found):
            break
        # print([(i+x*step, j+y*step) for (x,y) in dirs])
        for (k, d) in enumerate(dirs):
            (x, y) = d
            if not found[k]:
                if not valid(index, d, step):
                    found[k] = True
                elif grid[i+x*step][j+y*step] == 'L':
                    ds.append((i+x*step, j+y*step))
                    found[k] = True
        step += 1

    return ds
        
def pretty(grid):
    grid = np.array_split(grid, height)
    for l in grid:
        print("".join(l))

ns = []
for (k, _) in enumerate(p_input):
    ns.append(neighbours(k))

def run(rule, grid, num):
    changed = 1
    while changed != 0:
        next_state = grid.copy()
        changed = 0
        for (k, s) in enumerate(grid):
            n = sum([1 for (i, j) in rule[k] if grid[i*width+j] == '#'])
            if s == 'L' and n == 0:
                next_state[k] = '#'
                changed += 1
            elif s == '#' and n >= num:
                next_state[k] = 'L'
                changed += 1
        grid = next_state.copy()

    return sum([1 for s in grid if s == '#'])

print("Day eleven part one: {}".format(run(ns, p_input, 4)))

ds = []
for (k, _) in enumerate(p_input):
    ds.append(neighbours(k, False))


    


print("Day eleven part two: {}".format(run(ds, p_input, 5)))









