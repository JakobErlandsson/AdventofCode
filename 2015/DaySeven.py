from get_input import get
from ctypes import c_uint16

input = get(7).split('\n')

# Maps destination wires to the gates that produce them.
wires = {}
reverse_index = {}

def reset():
    for line in input:
        line = line.split(' ')
        dest = line[-1]
        wires[dest] = [int(i) if i.isnumeric() else i for i in line[:-2]]
    for w1 in wires:
        reverse_index[w1] = []
        for w2 in wires:
            if w1 in wires[w2]:
                reverse_index[w1].append(w2) 




def run(start):
    value = wires[start]
    for dest in reverse_index[start]:
        new_gate = [value if w == start else w for w in wires[dest]]
        if len(new_gate) == 2:
            wires[dest] = ~new_gate[1]
            run(dest)
        elif len(new_gate) == 3:
            [w1, op, w2] = new_gate
            if isinstance(w1, int) and isinstance(w2, int):
                if op == 'AND':
                    wires[dest] = w1 & w2
                elif op == 'OR':
                    wires[dest] = w1 | w2
                elif op == 'LSHIFT':
                    wires[dest] = w1 << w2
                else: # op == 'RSHIFT':
                    wires[dest] = w1 >> w2
                run(dest)
            else:
                wires[dest] = new_gate
                
def find(start):
    if isinstance(wires[start], int):
        return wires[start]
    else: 
        find(wires[start][0])
    

reset()
wires['b'] = wires['b'][0]
wires['c'] = wires['c'][0]
run('b')
run('c')

result = c_uint16(wires[wires['a'][0]]).value
print(result)

reset()
wires['b'] = result
wires['c'] = wires['c'][0]
run('b')
run('c')

result = c_uint16(wires[wires['a'][0]]).value
print(result)