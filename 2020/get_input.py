import requests
import os.path

def get(day):
    if os.path.isfile('day{}.txt'.format(day)):
        f = open("day{}.txt".format(day), "r")
        puzzle_input = f.read()
    else: 
        url = "https://adventofcode.com/2020/day/{}/input".format(day)
        cookie = {"session": "53616c7465645f5fe5878536e3f7aa3f8d8ef3f237367b95207400e05b7a525e0b847e98faf6d35d84b28c365679545b"}
        res = requests.get(url, cookies=cookie)
        puzzle_input = res.text
        f = open("day{}.txt".format(day), "w")
        f.write(puzzle_input)
    f.close()

    return puzzle_input[:-1]