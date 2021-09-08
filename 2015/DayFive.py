from get_input import get

input = get(5).split('\n')


def nice(string):
    forbidden = ['ab', 'cd', 'pq', 'xy']
    vowels = ['a', 'e', 'i', 'o', 'u']
    if any([f in string for f in forbidden]):
        return False
    if sum([1 for s in string if s in vowels]) < 3:
        return False
    if all([string[i] != string[i+1] for i in range(len(string)-1)]):
        return False
    return True

def nicev2(string):
    if all([string[i] != string[i+2] for i in range(len(string)-2)]):
        return False
    pairs = [string[i] + string[i+1] for i in range(len(string)-1)]
    n_pairs = {}
    for (i,p) in enumerate(pairs):
        if p in n_pairs:
            n_pairs[p].append(i)
        else: 
            n_pairs[p] = [i]
    for k in n_pairs:
        v = n_pairs[k]
        if len(v) > 1 and v[0] != v[1]-1:
            return True
    return False


print(sum([1 for s in input if nice(s)]))
print(sum([1 for s in input if nicev2(s)]))

# print(nicev2('xxyxx')) # true
# print(nicev2('xxxyxz')) # false