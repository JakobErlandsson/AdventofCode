from get_input import get
p_input = get(16).split('\n')

my_ticket = [int(i) for i in p_input[22].split(',')]

valids = set()
fields = []

for (i, l) in enumerate(p_input):
    if not l:
        next_start = i
        break
    words = l.split(' ')
    first = words[-3].split('-')
    first = (int(first[0]), int(first[1]))
    second = words[-1].split('-')
    second = (int(second[0]), int(second[1]))
    numbers = []
    for i in range(first[0], first[1]+1):
        valids.add(i)
        numbers.append(i)
    for i in range(second[0], second[1]+1):
        valids.add(i)  
        numbers.append(i)
    fields.append(numbers)

invalids = []
badidx = []



for (i, l) in enumerate(p_input[next_start+5:]):
    ticket = [int(j) for j in l.split(',')]
    bad = [j for j in ticket if j not in valids]
    if bad:
        invalids.extend(bad)
        badidx.append(i)

print("Day sixteen part two: {}".format(sum(invalids)))

# Remove all invalid tickets
p_input = p_input[next_start+5:]
p_input = [[int(j) for j in p_input[i].split(',')] for (i, _) in enumerate(p_input) if i not in badidx]


# For every index of ticket, get all values for that index.
vals = []
for i in range(len(my_ticket)):
    vals.append([t[i] for t in p_input])

# For every index of ticket, which fields are possible
canbe = []
for (i,vs) in enumerate(vals):
    can = []
    for j in range(len(my_ticket)):
        if all([v in fields[j] for v in vs]):
            can.append(j)
    canbe.append(can)

# Will contain which field corresponds to which index 
lang = [-1]*len(my_ticket)

# Start by finding which index only has one possibility, add it to lang, 
# remove that from all others and continue
while any([i == -1 for i in lang]):
    done = [(k, c[0]) for (k, c) in enumerate(canbe) if len(c) == 1][0]
    (i, n) = done
    lang[i] = n
    for c in canbe:
        if not c:
            continue
        c.remove(n)

ans = 1
for i in range(6):
    for (j, k) in enumerate(lang):
        if k == i:
            ans *= my_ticket[j]

print("Day sixteen part two: {}".format(ans))

    
        






        







