from get_input import get
from itertools import combinations
from math import ceil

# Returns true if p1 wins, false otherwise
def fight(p1, p2):
    p1_actual_damage = max(1, p1['damage'] - p2['armor'])
    p2_actual_damage = max(1, p2['damage'] - p1['armor'])
    p1_rounds = ceil(p2['hp'] / p1_actual_damage)
    p2_rounds = ceil(p1['hp'] / p2_actual_damage)
    return p1_rounds <= p2_rounds

input = get(21).split('\n')
enemy = {
    "hp": int(input[0].split(' ')[-1]),
    "damage": int(input[1].split(' ')[-1]),
    "armor": int(input[2].split(' ')[-1]),
}

weapons = [
    {
        "name": "Dagger",
        "cost": 8,
        "damage": 4,
        "armor": 0
    },
    {
        "name": "Shortsword",
        "cost": 10,
        "damage": 5,
        "armor": 0
    },
    {
        "name": "Warhammer",
        "cost": 25,
        "damage": 6,
        "armor": 0
    },
    {
        "name": "Longsword",
        "cost": 40,
        "damage": 7,
        "armor": 0
    },
    {
        "name": "Greataxe",
        "cost": 74,
        "damage": 8,
        "armor": 0
    }
]
chests = [
    {
        "name": "None",
        "cost": 0,
        "damage": 0,
        "armor": 0
    },
    {
        "name": "Leather",
        "cost": 13,
        "damage": 0,
        "armor": 1
    },
    {
        "name": "Chainmail",
        "cost": 31,
        "damage": 0,
        "armor": 2
    },
    {
        "name": "Splintmail",
        "cost": 53,
        "damage": 0,
        "armor": 3
    },
    {
        "name": "Bandedmail",
        "cost": 75,
        "damage": 0,
        "armor": 4
    },
    {
        "name": "Platemail",
        "cost": 102,
        "damage": 0,
        "armor": 5
    }
]
rings = [
    {
        "name": "None",
        "cost": 0,
        "damage": 0,
        "armor": 0
    },
    {
        "name": "Damage+1",
        "cost": 25,
        "damage": 1,
        "armor": 0
    },
    {
        "name": "Damage+2",
        "cost": 50,
        "damage": 2,
        "armor": 0
    },
    {
        "name": "Damage+3",
        "cost": 100,
        "damage": 3,
        "armor": 0
    },
    {
        "name": "Armor+1",
        "cost": 20,
        "damage": 0,
        "armor": 1
    },
    {
        "name": "Armor+2",
        "cost": 40,
        "damage": 0,
        "armor": 2
    },
    {
        "name": "Armor+3",
        "cost": 80,
        "damage": 0,
        "armor": 3
    }
]

ring_combinations = list(combinations(range(len(rings)),2))
ring_combinations.append((0,0))
loadouts = []
for i in range(len(weapons)):
    for j in range(len(chests)):
        for (k,l) in ring_combinations:
            damage = weapons[i]["damage"] + chests[j]["damage"] + rings[k]["damage"] + rings[l]["damage"]
            armor =  weapons[i]["armor"] + chests[j]["armor"] + rings[k]["armor"] + rings[l]["armor"]
            cost =   weapons[i]["cost"] + chests[j]["cost"] + rings[k]["cost"] + rings[l]["cost"]
            loadouts.append({"damage": damage, "armor": armor, "cost": cost, "equipment": [weapons[i]['name'], chests[j]['name'], rings[k]['name'], rings[l]['name']]})

loadouts = sorted(loadouts, key=lambda l: l['cost'])

cost = 0
for loadout in loadouts:
    player = {
        "hp": 100,
        "armor": loadout['armor'],
        "damage": loadout['damage']
    }
    if fight(player, enemy):
        print(loadout)
        break
    
for loadout in reversed(loadouts):
    player = {
        "hp": 100,
        "armor": loadout['armor'],
        "damage": loadout['damage']
    }
    if not fight(player, enemy):
        print(loadout)
        break