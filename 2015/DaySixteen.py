from get_input import get

sues = get(16).split('\n')

correct_sue = {
    'children': 3,
    'cats': 7,
    'samoyeds': 2,
    'pomeranians': 3,
    'akitas': 0,
    'vizslas': 0,
    'goldfish': 5,
    'trees': 3,
    'cars': 2,
    'perfumes': 1
}

def correct(attr, val, part1=True):
    if part1:
        return correct_sue[attr] == int(val)
    if attr in ['cats', 'trees']:
        return int(val) > correct_sue[attr]
    elif attr in ['pomeranians', 'goldfish']: 
        return int(val) < correct_sue[attr]
    else:
        return correct_sue[attr] == int(val)

for sue in sues:
    attributes = sue.replace(',', '').replace(':', '').split(' ')
    attr = [attributes[2], attributes[4], attributes[6]]
    vals = [attributes[3], attributes[5], attributes[7]]

    if all([correct(a,v) for (a,v) in zip(attr, vals)]):
        print('Part 1: {}'.format(attributes[1]))
    if all([correct(a,v,False) for (a,v) in zip(attr, vals)]):
        print('Part 2: {}'.format(attributes[1]))