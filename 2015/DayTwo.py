from get_input import get

input = get(2).split('\n')

#2*l*w + 2*w*h + 2*h*l

paper = 0
ribbon = 0

for dims in input:
    [l,w,h] = [int(i) for i in dims.split('x')]
    paper += 2*l*w + 2*w*h + 2*h*l + min(l*w, w*h, h*l)
    ribbon += l*w*h + 2*(l+w+h) - 2 * max(l,w,h)

print(paper)
print(ribbon)