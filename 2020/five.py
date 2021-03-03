from get_input import get
p_input = get(5).split('\n')
ids = []

for l in p_input:
    row = ''.join(['1' if k == 'B' else '0' for k in l[:-3]])
    col = ''.join(['1' if k == 'R' else '0' for k in l[-3:]])
    id = int(row, 2) * 8 + int(col, 2)
    ids.append(id)

highest = max(ids)
print("Day five part one: {}".format(highest))
nums = list(range(min(ids), highest))
diff = set(nums).symmetric_difference(set(ids)).pop()
print("Day five part two: {}".format(diff))





    