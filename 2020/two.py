from get_input import get
p_input = get(2).split("\n")

n_okay1 = 0
n_okay2 = 0

for line in p_input:
    l = line.split(" ")
    numbers = [int(i) for i in l[0].split("-")]
    letter  = l[1][0]
    pword   = l[2]
    count = pword.count(letter)
    if count >= numbers[0] and count <= numbers[1]:
        n_okay1 += 1

    i1 = numbers[0] - 1
    i2 = numbers[1] - 1
    if (pword[i1] == letter) ^ (pword[i2] == letter):
       n_okay2 += 1 

print("Day two part one: {}".format(n_okay1))
print("Day two part two: {}".format(n_okay2))