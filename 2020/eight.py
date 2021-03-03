from get_input import get
from interpreter import run, preprocess
p_input = preprocess(get(8).split('\n'))

acc, visited, pc = run(p_input, set(), 0)

print("Day eight part one: {}".format(acc))

nops = [i for (i, (op,_)) in enumerate(p_input) if op == 'nop']
jmps = [i for (i, (op,_)) in enumerate(p_input) if op == 'jmp']


def brute(op):
    if op == 'nop':
        ops = nops = [i for (i, (op,_)) in enumerate(p_input) if op == 'nop']
    else:
        ops = jmps = [i for (i, (op,_)) in enumerate(p_input) if op == 'jmp']
    for o in ops:
        p_copy = p_input.copy()
        p_copy[o] = ('jmp' if op == 'nop' else 'nop', p_copy[o][1])
        acc, visited, pc = run(p_copy, set(), 0)
        if pc == len(p_copy):
            return acc
    
acc = brute('nop')
if not acc:
    acc = brute('jmp')

print("Day eight part two: {}".format(acc))



 




            