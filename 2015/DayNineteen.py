from get_input import get
import re

input = get(19).split('\n')

molecule = input[-1]
recipes = input[:-2]

substitutions = {}
new_molecules = set()
upper = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'

for line in recipes:
    line = line.split(' ')
    orig = line[0]
    substitution = line[2]
    if orig not in substitutions.keys():
        substitutions[orig] = [substitution]
    else:
        substitutions[orig].append(substitution)
    if orig not in molecule:
        continue
    l = len(orig)
    substrings = [molecule[i:i+l] for i in range(len(molecule)-l+1)]
    appearances = [i for i in range(len(substrings)) if substrings[i] == orig]
    for index in appearances:
        prefix = molecule[:index]
        suffix = molecule[index+l:]
        new_string = prefix + substitution + suffix
        new_molecules.add(new_string)

print(len(new_molecules))


def into_elements(string):
    elements = []
    i = 0
    while i < len(string):
        if i+1 == len(string):
            elements.append(string[i])
        elif string[i] in upper and string[i+1] not in upper:
            elements.append(string[i] + string[i+1])
            i += 1
        else: 
            elements.append(string[i])
        i += 1
    return elements

elements = into_elements(molecule)
n_Rn_Ar = len([e for e in elements if e in ['Rn', 'Ar']])
n_Y = len([e for e in elements if e == 'Y'])
print(len(elements) - n_Rn_Ar - 2*n_Y - 1)
                
