from get_input import get

p_word = get(11)

alphabet = 'abcdefghijklmnopqrstuvwxyz'
forbidden = 'iol'

def increment(string):
    s = list(string)
    for i in range(len(s)-1, -1, -1):
        letter = s[i]
        letter_index = alphabet.index(letter)
        new_index = (letter_index + 1) % len(alphabet)
        new_letter = alphabet[new_index]
        if new_letter in forbidden:
            new_index += 1
            new_letter = alphabet[new_index]

        s[i] = new_letter
        if new_letter != 'a':
            return ''.join(s)

def increasing(string):
    [i, j, k] = [alphabet.index(string[0]), alphabet.index(string[1]), alphabet.index(string[2])]
    return j == i+1 and k == j+1


def valid(string):
    trips = [string[i] + string[i+1] + string[i+2] for i in range(len(string)-2)]
    if not any([increasing(t) for t in trips]):
        return False
    pairs = set([string[i] + string[i+1] for i in range(len(string)-1) if string[i] == string[i+1]])
    if len(pairs) <= 1:
        return False
    return True

def find_new(password):
    while not valid(password):
        password = increment(password)
    return password

first_password = find_new(p_word)
print(first_password)

second_password = find_new(increment(first_password))
print(second_password)