from get_input import get
p_input = get(18).split('\n')

def ev(term):
    term = term.replace('(', "").replace(')', "")
    term = term.split(' ')
    acc = int(term[0])
    i = 1
    while i < len(term):
        t = term[i]
        if t == '+':
            acc += int(term[i+1])
            i += 2
        if t == '*':
            acc *= int(term[i+1])
            i += 2
    return str(acc)

def calc(terms):
    terms2 = terms.copy()
    rem = []
    for t in terms[:-1]:
        res = ev(t)
        if any([t in ts for ts in terms if t != ts]):
            terms[i] = ts.replace(t, res)
            rem.append(t)

    for r in rem:
        terms2.remove(rem)
    print(terms2)
    return terms2
                

vals = []
for p in p_input[-1:]:
    rights = []
    lefts = []
    par = []
    for (i, c) in enumerate(p):
        if c == '(':
            rights.append(i)
        elif c == ')':
            lefts.append(i)
            r = rights[-1]
            l = lefts[-1]
            rights.remove(r)
            lefts.remove(l)
            par.append(p[r:l+1])
    calc(par)
    # print(par)
    # print(calc(par))
    # print(p)
    if not par:
        val = int(ev(p))
    else:
        ps = par[-1]
        pars = calc(par)
        q = p.replace(ps, calc(par))
        val = int(ev(q))

    vals.append(val)        
print("Day eighteen part one: {}".format(sum(vals)))