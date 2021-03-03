from get_input import get
p_input = get(12).split('\n')

dir_map = {
    'E': 0,
    'S': 1,
    'W': 2,
    'N': 3,
    0: 'E',
    1: 'S',
    2: 'W',
    3: 'N'
}

directions = {
    'E': (1, 0),
    'S': (0, 1),
    'W': (-1, 0),
    'N': (0, -1)
}

def move(dir, dist):
    (x, y) = dir
    dx = x*dist
    dy = y*dist
    return (dx, dy)

def run(first=True):
    waypoint = (0,0) if first else (10, -1)
    ship = (0,0)
    facing = 'E'
    for l in p_input:
        direction = l[0]
        value = int(l[1:])
        rotations = 0
        if direction in ['E', 'S', 'W', 'N']:
            (x, y) = move(directions[direction], value)
            waypoint = (waypoint[0] + x, waypoint[1] + y)
        elif direction == 'L' and first:
            rotations = -(value // 90)
        elif direction == 'R' and first:
            rotations = value // 90
        elif direction == 'L' and not first:
            (x, y) = waypoint
            if value == 90:
                waypoint = (y, -x)
            elif value == 180:
                waypoint = (-x, -y)
            elif value == 270:
                waypoint = (-y, x)
        elif direction == 'R' and not first:
            (x, y) = waypoint
            if value == 90:
                waypoint = (-y, x)
            elif value == 180:
                waypoint = (-x, -y)
            elif value == 270:
                waypoint = (y, -x)
        elif direction == 'F' and first:
            (x, y) = move(directions[facing], value)
            waypoint = (waypoint[0] + x, waypoint[1] + y)
        elif direction == 'F' and not first:
            (x, y) = waypoint
            ship = (ship[0] + x*value, ship[1] + y*value)

        r = dir_map[facing]
        r = (r + rotations) % 4
        facing = dir_map[r]
    return waypoint, ship
    

pos, _ = run()
dist = abs(pos[0]) + abs(pos[1])
print("Day twelve part one: {}".format(dist))

_, pos = run(False)
dist = abs(pos[0]) + abs(pos[1])
print("Day twelve part two: {}".format(dist))

