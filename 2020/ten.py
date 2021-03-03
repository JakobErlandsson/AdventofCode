from get_input import get
p_input = sorted([int(i) for i in get(10).split('\n')])


ones = 0
threes = 0

outlet = 0
device = p_input[-1] + 3
p_input.append(device)
p_input = [outlet] + p_input
i = 1

while i < len(p_input):
    diff = p_input[i] - p_input[i-1]
    if diff == 1:
        ones += 1
    elif diff == 3:
        threes +=1
    else:
        print("broken")
        break
    i+= 1

print("Day ten part one: {}".format(ones*threes))

ways = [0]*max(p_input)
ways[-1] = 1

i = len(p_input)-2

for i in reversed(p_input[:-1]):
    ways[i-1] = sum(ways[i:i+3])

print("Day ten part two: {}".format(sum(ways[:3])))
