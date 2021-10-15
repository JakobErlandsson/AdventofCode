from get_input import get
import re
import json

input = get(12)

number_regex = r'[-0-9]+'

print(sum([int(m) for m in re.findall(number_regex, input)]))


json_input = json.loads(input)

def count_recursive(root):
    if isinstance(root, int):
        return root
    elif isinstance(root, str):
        return 0
    elif isinstance(root, list):
        return sum([count_recursive(i) for i in root])
    else: #isinstance(root, dict):
        if 'red' in [root[k] for k in root]:
            return 0
        else: 
            return sum([count_recursive(root[k]) for k in root])

print(count_recursive(json_input))


    