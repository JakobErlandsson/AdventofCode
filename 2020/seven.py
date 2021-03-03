from get_input import get
import re

p_input = get(7).split('\n')

contains = {}
n_bags = {}

for l in p_input:
    colors = [s.replace(" ", "").replace(",","").replace("contain","") for s in l.split('bags')][:-1]
    bags = []
    nums = []
    for c in colors[1:]:
        if c != 'noother':
            num = int(c[0])
            col = c[1:]
            bags.append(col)
            nums.append((col, num))
    n_bags[colors[0]] = nums
    contains[colors[0]] = bags

def find(color):
    arr = set()
    for key in contains:
        if color in contains[key]:
            arr.add(key)
    return arr

def find_num(color):
    if not n_bags[color]:
        return 0
    else:
        return sum([y + y * find_num(c) for (c,y) in n_bags[color]])
    
a = set()
a.update(find('shinygold'))
l = len(a)
while True:
    tmp = set()
    for item in a:
        tmp.update(find(item))
    a.update(tmp)
    if len(a) == l:
        break
    else:
        l = len(a)

print("Day seven part one: {}".format(len(a)))
print("Day seven part two: {}".format(find_num('shinygold')))