from get_input import get
import hashlib

input = get(4)

counter = 1
found = [False, False]
threshold = 5
while not all(found):
    string = input + str(counter)
    hex = hashlib.md5(string.encode()).hexdigest()
    if not found[0] and hex[:5] == '00000':
        print(counter, hex)
        found[0] = True

    if not found[1] and hex[:6] == '000000':
        print(counter, hex)
        found[1] = True

    counter += 1