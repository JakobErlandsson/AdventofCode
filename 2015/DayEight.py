from get_input import get
import re

input = get(8).split('\n')

sum_code = 0
sum_char = 0
sum_char2 = 0
ascii_regex = r'\\x[0-9a-f]{2}'
other_regex = r'\\"|\\\\'


for line in input:
    sum_code += len(line)
    formatted_line = re.sub(other_regex, '$', line)
    formatted_line = re.sub(ascii_regex, '$', formatted_line)
    sum_char += len(formatted_line)-2

    elongated_line = '\"' + line.replace('\\', '\\\\').replace('\"', '\\\"') + '\"'
    sum_char2 += len(elongated_line)


print(sum_code-sum_char)
print(sum_char2-sum_code)