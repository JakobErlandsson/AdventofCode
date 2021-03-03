def preprocess(code):
    tmp = []
    for (pc, c) in enumerate(code):
        op = c.split(' ')[0]
        nb = int(c.split(' ')[1])
        tmp.append((op, nb))
    return tmp

def run(code, visited, pc):
    acc = 0
    while pc != len(code):
        (op, nb) = code[pc]

        if pc in visited:
            return acc, visited, pc
        visited.add(pc)

        if op == 'acc':
            acc += nb 
            pc += 1
        elif op == 'jmp':
            pc += nb
        elif op == 'nop':
            pc += 1

    return acc, visited, pc