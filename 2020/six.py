from get_input import get
p_input = get(6).split('\n')

group = set()
people = []

sum1 = 0
sum2 = 0
for l in p_input:
    if not l:
        sum1 += len(group)
        sum2 += len(set.intersection(*people))
        group = set()
        people = []
    else:
        group.update(list(l))
        people.append(set(list(l)))

print("Day six part one: {}".format(sum1))
print("Day six part two: {}".format(sum2))