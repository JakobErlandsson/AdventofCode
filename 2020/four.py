from get_input import get
import re

p_input = get(4).split("\n")

people1 = []
people2 = []
person = {}
keys = ['byr', 'iyr', 'eyr', 'hgt', 'hcl', 'ecl', 'pid']

def check1(person):
    return all([k in person for k in keys])

def check2(person):
    return all([valid(k, person[k]) for k in keys])

def valid(key, value):
    if key == 'byr':
        return int(value) >= 1920 and int(value) <= 2002
    elif key == 'iyr':
        return int(value) >= 2010 and int(value) <= 2020
    elif key == 'eyr':
        return int(value) >= 2020 and int(value) <= 2030
    elif key == 'hgt':
        if value[-2:] == 'cm':
            return int(value[:-2]) >= 150 and int(value[:-2]) <= 193
        elif value[-2:] == 'in':
            return int(value[:-2]) >= 59 and int(value[:-2]) <= 76
        else:
            return False
    elif key == 'hcl':
        p = re.compile("^#[a-f0-9]{6}$")
        return p.search(value)
    elif key == 'ecl':
        return value in ['amb', 'blu', 'brn', 'gry', 'grn', 'hzl', 'oth']
    elif key == 'pid':
        p = re.compile("^[0-9]{9}$")
        return p.search(value)
    elif key == 'cid':
        return True

for l in p_input:
    if not l:
        if check1(person):
            people1.append(person)
            if check2(person):
                people2.append(person)
        person = {}
    else:
        for attr in l.split(' '):
            kv = attr.split(':')
            person[kv[0]] = kv[1]
           
print("Day four part one: {}".format(len(people1)))
print("Day four part two: {}".format(len(people2)))

