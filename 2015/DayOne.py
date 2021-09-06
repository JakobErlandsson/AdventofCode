from get_input import get

input = get(1)
print(sum([-1 if i == ')' else 1 for i in input]))

floor = 0

for (x,i) in enumerate(input):
    floor += 1 if i == '(' else -1
    if (floor < 0):
        print(x+1)
        break 

