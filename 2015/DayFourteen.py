from get_input import get

input = get(14).split('\n')
duration = 2503

distances = []
for reindeer in input:
    reindeer = reindeer.split(' ')
    name = reindeer[0]
    speed = int(reindeer[3])
    stamina = int(reindeer[6])
    rest = int(reindeer[-2])
    distance = 0
    while duration >= 0:
        distance += (speed * min(stamina, duration))
        duration -= (stamina + rest)
    distances.append(distance)

print(max(distances))

reindeers = []
for line in input:
    line = line.split(' ')
    name = line[0]
    speed = int(line[3])
    stamina = int(line[6])
    rest = int(line[-2])
    reindeer = {
        'name': name,
        'speed': speed,
        'max_stamina': stamina,
        'stamina': stamina,
        'resting_time': rest,
        'resting': rest,
    }
    reindeers.append(reindeer)
distances = [0 for i in distances]
scores = [0]*len(reindeers)
duration = 2503

for _ in range(duration):
    for (i,r) in enumerate(reindeers):
        if r['stamina'] != 0:
            distances[i] += r['speed']
            r['stamina'] -= 1
        else:
            if r['resting'] != 1:
                r['resting'] -= 1
            else:
                r['stamina'] = r['max_stamina']
                r['resting'] = r['resting_time']
    # print(reindeers[0], distances[0])
    max_dist = max(distances)
    for i in range(len(scores)):
        if distances[i] == max_dist:
            scores[i] += 1
    print(scores)
        
