from get_input import get
from math import prod

input = get(15).split('\n')
teaspoons = 100
properties = []
for i in input:
    i = i.split(' ')
    prop = [int(i[2][:-1]), int(i[4][:-1]), int(i[6][:-1]), int(i[8][:-1]), int(i[10])]
    properties.append(prop)

def get_score(amounts):
    tot_cap = max(0, sum([val*amount for (val, amount) in zip([p[0] for p in properties], amounts)]))
    tot_dur = max(0, sum([val*amount for (val, amount) in zip([p[1] for p in properties], amounts)]))
    tot_fla = max(0, sum([val*amount for (val, amount) in zip([p[2] for p in properties], amounts)]))
    tot_tex = max(0, sum([val*amount for (val, amount) in zip([p[3] for p in properties], amounts)]))
    tot_cal = max(0, sum([val*amount for (val, amount) in zip([p[4] for p in properties], amounts)]))
    # return prod([tot_cap, tot_dur, tot_fla, tot_tex])
    return prod([tot_cap, tot_dur, tot_fla, tot_tex]) if tot_cal == 500 else 0

highest_score = 0
for i in range(101):
    for j in range(101):
        if i+j > 100:
            break
        for k in range(101):
            if i+j+k > 100:
                break
            l = 100 - (i+j+k)
            score = get_score([i,j,k,l])
            print("{}, {}, {}, {} -> {}".format(i,j,k,l, score))
            highest_score = max(score, highest_score)


print(highest_score)