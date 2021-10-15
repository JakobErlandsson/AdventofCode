from get_input import get

input = get(18).split('\n')
size = len(input)

def get_neighbours(x, y):
    all = [(-1, -1), (-1, 0), (-1,1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]
    if x == 0:
        all = [n for n in all if n[0] != -1]
    if x == size-1:
        all = [n for n in all if n[0] != 1]
    if y == 0:
        all = [n for n in all if n[1] != -1]
    if y == size-1:
        all = [n for n in all if n[1] != 1]
    return [(x+n[0], y+n[1]) for n in all]

def paint(grid):
    for line in grid:
        print(''.join(['#' if c else '.' for c in line]))
    print()

def animate(iterations, grid, second=False):
    for _ in range(iterations):
        # Becasue python lists are immutable
        new_grid = []
        for line in grid:
            new_grid.append(line.copy())

        for y in range(size):
            for x in range(size):
                if second and (x,y) in [(0,0), (0, size-1), (size-1, 0), (size-1, size-1)]:
                    continue
                ns = neighbours[(x,y)]
                neighbours_on = sum([grid[n[0]][n[1]] for n in ns])
                if grid[x][y]:
                    if neighbours_on not in [2,3]:
                        new_grid[x][y] = False
                else: 
                    if neighbours_on == 3:
                        new_grid[x][y] = True

        grid = new_grid
    return grid


neighbours = {}

for y in range(size):
    for x in range(size):
        neighbours[(x,y)] = get_neighbours(x,y)

grid = []
for line in input:
    grid.append([True if c == '#' else False for c in line])

result = animate(100, grid, False)
print(sum([sum(line) for line in result]))

grid = []
for line in input:
    grid.append([True if c == '#' else False for c in line])

grid[0][0] = True
grid[size-1][0] = True
grid[0][size-1] = True
grid[size-1][size-1] = True

result = animate(100, grid, True)
print(sum([sum(line) for line in result]))