from get_input import get

input = get(3)

houses = set()
#x,y
(x,y) = (0,0)

for dir in input:
    houses.add((x,y))
    if dir == '^':
        y -= 1
    elif dir == 'v':
        y += 1
    elif dir == '>':
        x += 1
    else: # dir == '<'
        x -= 1

print(len(houses))

(santa_x, santa_y, robo_x, robo_y) = (0,0,0,0)

houses = set()


for i in range(0,len(input), 2):
    houses.add((santa_x,santa_y))
    houses.add((robo_x,robo_y))
    if input[i] == '^':
        santa_y -= 1
    elif input[i] == 'v':
        santa_y += 1
    elif input[i] == '>':
        santa_x += 1
    else: # input[i] == '<'
        santa_x -= 1
    
    if input[i+1] == '^':
        robo_y -= 1
    elif input[i+1] == 'v':
        robo_y += 1
    elif input[i+1] == '>':
        robo_x += 1
    else: # input[i+1] == '<'
        robo_x -= 1

print(len(houses))