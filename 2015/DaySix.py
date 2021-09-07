from get_input import get

grid = []
for i in range(1000):
    grid.append([0]*1000)

input = get(6).split('\n')

for line in input:
    line = line.split(' ')
    end = [int(i) for i in line[-1].split(',')]
    start = [int(i) for i in line[-3].split(',')]
    for i in range(start[0], end[0]+1):
        for j in range(start[1], end[1]+1):
            if line[0] == 'turn':
                if line[1] == 'on':
                    grid[i][j] = 1
                else: # line[1] == 'off'
                    grid[i][j] = 0
            else: #line[0] == 'toggle'
                grid[i][j] = 0 if grid[i][j] else 1

print(sum([sum(line) for line in grid]))

grid = []
for i in range(1000):
    grid.append([0]*1000)

for (k,line) in enumerate(input):
    line = line.split(' ')
    end = [int(i) for i in line[-1].split(',')]
    start = [int(i) for i in line[-3].split(',')]
    for i in range(start[0], end[0]+1):
        for j in range(start[1], end[1]+1):
            if line[0] == 'turn':
                if line[1] == 'on':
                    grid[i][j] += 1
                else: # line[1] == 'off'
                    grid[i][j] = max(0, grid[i][j]-1)
            else: #line[0] == 'toggle'
                grid[i][j] += 2

print(sum([sum(line) for line in grid]))