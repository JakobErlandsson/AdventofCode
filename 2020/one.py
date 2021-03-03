from get_input import get
p_input = [int(i) for i in get(1).split('\n')]
p_input.sort()

target = 2020

def p1():
    i = 0
    j = len(p_input) - 1
    while i != j:
        s = p_input[i] + p_input[j]
        if s == target:
            return p_input[i] * p_input[j]
        elif s > target:
            j -= 1
        else: # s < target
            i += 1

def p2():
    for (i,_) in enumerate(p_input):
        j = i+1
        k = len(p_input) - 1
        while j != k:
            s = p_input[i] + p_input[j] + p_input[k]
            if s == target:
                return p_input[i] * p_input[j] * p_input[k]
            elif s > target:
                k -= 1
            else: 
                j += 1                

print("Day one part one: {}".format(p1()))
print("Day one part two: {}".format(p2()))