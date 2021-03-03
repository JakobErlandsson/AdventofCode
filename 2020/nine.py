from get_input import get
p_input = [int(i) for i in get(9).split('\n')]

# inp = "35 20 15 25 47 40 62 55 65 95 102 117 150 182 127 219 299 277 309 576"
# p_input = [int(i) for i in inp.split(' ')]

p = 25 
i = p

while i < len(p_input):
    nums = p_input[i-p:i]
    sums = [nums[a] + nums[b] for a in range(0,p) for b in range(a,p) if a !=b]
    
    if p_input[i] not in sums:
        n = p_input[i]
        break
    i += 1

print("Day nine part one: {}".format(n))

def find_fault():
    for i in range(len(p_input)):
        s = []
        for j in range(i, len(p_input)):
            s.append(p_input[j])
            if sum(s) == n:
                return min(s) + max(s)
            if sum(s) > n:
                break

print("Day nine part two: {}".format(find_fault()))

    